package com.shand.banksystem.soap.russia.client;

import com.shand.banksystem.soap.russia.client.gen.GetCursOnDate;
import com.shand.banksystem.soap.russia.client.gen.GetCursOnDateResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public class DailyInfoBankClient extends WebServiceGatewaySupport {
    private final String URI_CBR = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";

    public GetCursOnDateResponse getCursOnDate(ZonedDateTime date) {
        GetCursOnDate request = new GetCursOnDate();
        XMLGregorianCalendar calendar = convertXmlCalendarFromZonedDateTime(date);
        request.setOnDate(calendar);
        return (GetCursOnDateResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URI_CBR, request,
                        new SoapActionCallback("http://web.cbr.ru/GetCursOnDate"));
    }

    private XMLGregorianCalendar convertXmlCalendarFromZonedDateTime(ZonedDateTime date) {
        GregorianCalendar c = GregorianCalendar.from(ZonedDateTime.from(date));
        return DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(c);
    }
}
