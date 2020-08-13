package com.dungeongroupfinder.beans;

import com.dungeongroupfinder.objects.PendingList;
import org.springframework.context.annotation.Bean;

public class BeanFactory {

    @Bean
    public static PendingList getPendingListBean() {
        return new PendingList();
    }

}
