package com.shand.banksystem.services.impl;

import com.shand.banksystem.dto.CurrencyRateDto;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.model.CurrencyRate;
import com.shand.banksystem.repositories.CurrencyRateRepository;
import com.shand.banksystem.services.RussiaBankService;
import com.shand.banksystem.soap.russia.client.DailyInfoBankClient;
import com.shand.banksystem.soap.russia.client.gen.GetCursDynamicResponse;
import com.shand.banksystem.soap.russia.client.gen.GetCursOnDateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;

import static java.util.Map.entry;

@Slf4j
@Service
public class RussiaBankServiceImpl implements RussiaBankService {
    private final DailyInfoBankClient dailyInfoBankClient;

    private final CurrencyRateRepository rateRepository;

    private final Map<String, String> rateWithCodes = Map.ofEntries(
            entry("USD", "R01235"),
            entry("EUR", "R01239")
    );

    public RussiaBankServiceImpl(DailyInfoBankClient dailyInfoBankClient, CurrencyRateRepository rateRepository) {
        this.dailyInfoBankClient = dailyInfoBankClient;
        this.rateRepository = rateRepository;
    }

    public Set<String> getAllCurrency() {
        Set<String> curr = new HashSet<>(rateWithCodes.keySet());
        curr.add("RUB");
        return curr;
    }

    @Override
    public CurrencyRate findRateByName(String currency) {
        return rateRepository.findFirstByName(currency);
    }

    @Transactional
    public void updateLocalCurrency() {
        log.info("Start update currency rate");
        HashMap<String, BigDecimal> currencyRateMapFromBank = getCurrencyRateMapFromBank();
        List<CurrencyRate> allRates = rateRepository.findAllByNameIn(rateWithCodes.keySet());
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
    @Override
    public Map<ZonedDateTime, BigDecimal> getCurseForPeriod(ZonedDateTime start, ZonedDateTime end, String currency) {
        new LinkedHashMap<>();
        String rateCode = rateWithCodes.getOrDefault(currency, null);
        if (rateCode == null) {
            return new HashMap<>();
        }
        GetCursDynamicResponse cursDynamic = dailyInfoBankClient.getCursDynamic(start, end, rateCode);
        return getRateMapForPeriod(cursDynamic);
    }

    private Map<ZonedDateTime, BigDecimal> getRateMapForPeriod(GetCursDynamicResponse cursDynamic) {
        LinkedHashMap<ZonedDateTime, BigDecimal> map = new LinkedHashMap<>();
        org.w3c.dom.Element e = (Element) cursDynamic.getGetCursDynamicResult().getAny();
        NodeList valuteCursDynamic = e.getElementsByTagName("ValuteCursDynamic");
        int length = valuteCursDynamic.getLength();
        for (int i = 0; i < length; i++) {
            Node item = valuteCursDynamic.item(i);
            NodeList childNodes = item.getChildNodes();
            ZonedDateTime time = null;
            BigDecimal curs = null;
            for (int j = 0; j < childNodes.getLength(); j++) {
                String nodeName = childNodes.item(j).getNodeName();
                if (nodeName.equals("CursDate")) {
                    time = ZonedDateTime.parse(childNodes.item(j).getTextContent());
                } else if (nodeName.equals("Vcurs")) {
                    curs = new BigDecimal(childNodes.item(j).getTextContent());
                }
            }
            if (time != null && curs != null) {
                map.put(time, curs);
            }
        }
        return map;
    }


    private void updateRatesList(HashMap<String, BigDecimal> currencyRateMapFromBank, List<CurrencyRate> allRates) {
        rateWithCodes.keySet().forEach(s -> {
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
