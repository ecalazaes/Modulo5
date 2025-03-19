package com.torreverde.ms_compra.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queuePedidoCriado() {
        return new Queue("pedido.criado", true);
    }

    @Bean
    public Queue queuePagamentoConcluido() {
        return new Queue("pagamento.concluido", true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
