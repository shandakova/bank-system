package com.shand.banksystem.services.impl;

import com.shand.banksystem.dto.CurrencyRateDto;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.model.CurrencyRate;
import com.shand.banksystem.repositories.CurrencyRateRepository;
import com.shand.banksystem.services.RussiaBankService;
import com.shand.banksystem.soap.russia.client.DailyInfoBankClient;
import com.shand.banksystem.soap.russia.client.gen.GetCursOnDateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class RussiaBankServiceImpl implements RussiaBankService {
    private final DailyInfoBankClient dailyInfoBankClient;

    private final CurrencyRateRepository rateRepository;

    private final List<String> ratesName = List.of("USD", "EUR");

    public RussiaBankServiceImpl(DailyInfoBankClient dailyInfoBankClient, CurrencyRateRepository rateRepository) {
        this.dailyInfoBankClient = dailyInfoBankClient;
        this.rateRepository = rateRepository;
    }

    @Transactional
    public void updateLocalCurrency() {
        log.info("Start update currency rate");
        HashMap<String, BigDecimal> currencyRateMapFromBank = getCurrencyRateMapFromBank();
        List<CurrencyRate> allRates = rateRepository.findAllByNameIn(ratesName);
        updateRatesList(currencyRateMapFromBank, allRates);
        rateRepository.saveAll(allRates);
    }

    @Override
    public BaseResponse<List<CurrencyRateDto>> getCurrencyRateList() {
        List<CurrencyRate> rates = rateRepository.findAll();
        var res = rates.stream().map(currencyRate ->
                new CurrencyRateDto(currencyRate.getName(), currencyRate.getRate())).toList();
        return BaseResponse.<List<CurrencyRateDto>>builder().success(true)
                .value(res).build();
    }

//    private HashMap<String, BigDecimal> buildMapFromCurrencyRateList(List<CurrencyRate> rates) {
//        HashMap<String, BigDecimal> currencyRateMapFromBank = new HashMap<>();
//        rates.forEach(currencyRate -> currencyRateMapFromBank.put(currencyRate.getName(), currencyRate.getRate()));
//        return currencyRateMapFromBank;
//    }

    private void updateRatesList(HashMap<String, BigDecimal> currencyRateMapFromBank, List<CurrencyRate> allRates) {
        ratesName.forEach(s -> {
            if (currencyRateMapFromBank.containsKey(s)) {
                BigDecimal rate = currencyRateMapFromBank.get(s);
                Optional<CurrencyRate> currRate = allRates.stream().filter(cR -> cR.getName().equals(s)).findAny();
                if (currRate.isPresent()) {
                    currRate.get().setRate(rate);
                } else {
                    allRates.add(new CurrencyRate(s, rate));
                }
            }
        });
    }

    private HashMap<String, BigDecimal> getCurrencyRateMapFromBank() {
        GetCursOnDateResponse resp = dailyInfoBankClient.getCursOnDate(ZonedDateTime.now());
        org.w3c.dom.Element e = (Element) resp.getGetCursOnDateResult().getAny();
        NodeList valuteCursOnDate = e.getElementsByTagName("ValuteCursOnDate");
        int length = valuteCursOnDate.getLength();
        HashMap<String, BigDecimal> currencyMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            Node item = valuteCursOnDate.item(i);
            NodeList childNodes = item.getChildNodes();
            String code = null;
            BigDecimal curs = null;
            for (int j = 0; j < childNodes.getLength(); j++) {
                String nodeName = childNodes.item(j).getNodeName();
                if (nodeName.equals("VchCode")) {
                    code = childNodes.item(j).getTextContent();
                } else if (nodeName.equals("Vcurs")) {
                    curs = new BigDecimal(childNodes.item(j).getTextContent());
                }
            }
            if (code != null && curs != null) {
                currencyMap.put(code, curs);
            }
        }
        return currencyMap;
    }

}
