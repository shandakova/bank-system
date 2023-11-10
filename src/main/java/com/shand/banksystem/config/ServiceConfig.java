package com.shand.banksystem.config;

import com.shand.banksystem.soap.russia.client.DailyInfoBankClient;
import com.shand.banksystem.soap.russia.client.DailyInfoClientInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

@EnableScheduling
@EnableAsync
@Configuration
public class ServiceConfig {
    @Bean
    public Jaxb2Marshaller getMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.shand.banksystem.soap.russia.client.gen");
        return marshaller;
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Bean
    public DailyInfoBankClient getBankDailyInfoClient(Jaxb2Marshaller marshaller) {
        DailyInfoBankClient client = new DailyInfoBankClient();
        client.setUnmarshaller(marshaller);
        client.setMarshaller(marshaller);
        ClientInterceptor[] interceptors = new ClientInterceptor[1];
        interceptors[0] = new DailyInfoClientInterceptor();
        client.setInterceptors(interceptors);
        return client;
    }
}
