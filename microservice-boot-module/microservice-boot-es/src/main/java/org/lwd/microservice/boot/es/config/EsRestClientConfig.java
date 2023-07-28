package org.lwd.microservice.boot.es.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * rest风格客户端
 * The Java High Level REST Client is the default client of Elasticsearch, it is configured like shown:
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/27
 */
@Configuration
public class EsRestClientConfig extends AbstractElasticsearchConfiguration {

    @Value("${spring.elasticsearch.uris}")
    private String uris;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(uris)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

}
