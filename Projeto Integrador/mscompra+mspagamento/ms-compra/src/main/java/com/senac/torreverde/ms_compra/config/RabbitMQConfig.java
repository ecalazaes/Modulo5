package com.senac.torreverde.ms_compra.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Fila para enviar o pedido criado para o ms-pagamento
    @Bean
    public Queue queuePedidoCriado() {
        return new Queue("pedido.criado", true);
    }

    // Fila para receber o processamento de pagamento do ms-pagamento
    @Bean
    public Queue queuePagamentoConcluido() {
        return new Queue("pagamento.concluido", true);
    }

    // Conversor de mensagem para JSON.
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
