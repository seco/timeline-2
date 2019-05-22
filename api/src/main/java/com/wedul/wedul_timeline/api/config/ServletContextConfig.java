package com.wedul.wedul_timeline.api.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-22
 **/
@Configuration
@AllArgsConstructor
@EnableWebMvc // <annotation-driven /> // WebMvcConfiguration에서 구성한 스프링 MVC 구성을 불러올 수 있다.
@ComponentScan(basePackages = {"com.wedul.wedul_timeline.*"})
public class ServletContextConfig implements WebMvcConfigurer {

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(converter());
  }

  @Bean
  public MappingJackson2HttpMessageConverter converter() {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    converter.setObjectMapper(objectMapper);
    return converter;
  }
}
