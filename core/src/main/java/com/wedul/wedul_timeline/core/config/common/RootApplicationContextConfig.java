package com.wedul.wedul_timeline.core.config.common;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-25
 **/
@Configuration
@EnableTransactionManagement(proxyTargetClass = true) // • TransactionInterceptor가 @Transactional 어노테이션이 부여된 메소드에 트랜잭션 적용 xml의 <tx:annotation-driven />와 같다
@EnableAspectJAutoProxy(proxyTargetClass = true) // 자동으로 AspectJ 라이브러리를 이용해서 Proxy 객체를 생성해 내는 용도로 사용된다. xml의 <aop:aspectj-autoproxy></aop:aspectj-autoproxy> 설정과 같다
@ComponentScan(basePackages = {"com.wedul.wedul_timeline*"})
@EnableAutoConfiguration
@EnableKafka
@EnableJpaAuditing
public class RootApplicationContextConfig {
}
