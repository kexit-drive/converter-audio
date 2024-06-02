package kpi.zaranik.kexitdrive.converteraudio.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class WebClientConfiguration {

    @Bean
    public WebClient webClient(
        @Value("${spring.servlet.multipart.max-file-size}") DataSize maxInMemorySize,
        @Value("${converterUrl}") String converterUrl
    ) {
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
            .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(Math.toIntExact(maxInMemorySize.toBytes())))
            .build();
        return WebClient.builder()
            .baseUrl(converterUrl)
            .exchangeStrategies(strategies)
            .build();
    }

}
