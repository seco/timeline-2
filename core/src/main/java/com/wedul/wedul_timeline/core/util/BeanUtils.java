package com.wedul.wedul_timeline.core.util;

import com.wedul.wedul_timeline.core.config.common.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

/**
 * bean 가져오는 유틸
 *
 * @author wedul
 * @since 2019-06-10
 **/
public class BeanUtils {

    public static Object getBean(String beanId) {

        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        if (!Optional.of(applicationContext).isPresent()) {
            throw new NullPointerException("Failed Initialize applicationContext");
        }

        /*
        String[] names = applicationContext.getBeanDefinitionNames();
        for (int i=0; i<names.length; i++) {
            System.out.println(names[i]);
        }
        */

        return applicationContext.getBean(beanId);
    }
}