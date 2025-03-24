package com.app.adapter.output.WebClient;

import com.app.domain.model.Order;
import com.app.port.output.WebClientPort;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;
import java.util.Set;

@Component
public class WebClientAdapter implements WebClientPort {
    private final WebClient webClient;
    private static final Set<String> sentOrderIds = new HashSet<>();

    public WebClientAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void sendOrder(Order order) {
        if (sentOrderIds.contains(order.getOrderId())) {
            System.out.printf("Order %s has already been sent. Ignoring... %n", order.getOrderId());
            return;  // Impede o envio
        }

        System.out.printf("Sending Order %s to External Service... %n", order.getOrderId());
        sentOrderIds.add(order.getOrderId());  // Marca o pedido como enviado

        webClient.method(HttpMethod.POST)
                .uri("/orders")
                .bodyValue(order)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnTerminate(() ->
                        System.out.printf("Order %s Sent %n", order.getOrderId()))
                .subscribe();
    }
}
