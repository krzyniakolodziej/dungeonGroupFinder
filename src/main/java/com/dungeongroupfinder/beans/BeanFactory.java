package com.dungeongroupfinder.beans;

import com.dungeongroupfinder.objects.PendingList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {

    @Bean
    public static PendingList getPendingListBean() {
        return new PendingList();
    }

}
