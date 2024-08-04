package org.kingapi.kingapiclientsdk;

import lombok.Data;
import org.kingapi.kingapiclientsdk.client.KingApiClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:KingApiClientConfig
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/3 8:08
 * @version1.0
 */
@Configuration
@ConfigurationProperties("kingapi.client")
@Data
@ComponentScan
public class KingApiClientConfig {
    private String accessKey;
    private String secretKey;
    @Bean
    public KingApiClient kingApiClient(){
        return new KingApiClient(accessKey, secretKey);
    }
}
