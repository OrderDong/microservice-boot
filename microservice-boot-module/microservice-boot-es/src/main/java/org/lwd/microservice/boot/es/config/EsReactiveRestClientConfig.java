package org.lwd.microservice.boot.es.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;

/**
 * 响应式客户端
 * The ReactiveElasticsearchClient is a non official driver based on WebClient.
 * It uses the request/response objects provided by the Elasticsearch core project.
 * Calls are directly operated on the reactive stack, not wrapping async (thread pool bound) responses into reactive types.
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/28
 */
@Configuration
public class EsReactiveRestClientConfig extends AbstractReactiveElasticsearchConfiguration {

    @Value("${spring.elasticsearch.uris}")
    private String uris;

    @Bean
    @Override
    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(uris) //
                .build();
        return ReactiveRestClients.create(clientConfiguration);
    }
}
