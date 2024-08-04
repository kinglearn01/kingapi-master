package org.kingapi.kingapiinterface;

import org.junit.jupiter.api.Test;
import org.kingapi.kingapiclientsdk.client.KingApiClient;
import org.kingapi.kingapiclientsdk.model.User;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class KingapiInterfaceApplicationTests {
    @Resource
    private KingApiClient kingApiClient;
    @Test
    void contextLoads() {

        String name = kingApiClient.getName("king1");
        System.out.println(name);
        User user = new User();
        user.setName("king");
        String string1 = kingApiClient.restfulName(user);
        System.out.println(string1);
    }

}
