package eu.onepay.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eu.onepay.payment.PaymentCredentialFactory;


@Configuration
public class AppConfiguration {

    @Bean
    public PaymentCredentialFactory paymentCredentialFactory() {
        return new PaymentCredentialFactory();
    }
}
