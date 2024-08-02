package org.kingapi.kingapiinterface;

import org.kingapi.kingapiinterface.client.KingApiClient;
import org.kingapi.kingapiinterface.model.User;

/**
 * ClassName:Main
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/2 17:31
 * @version1.0
 */
public class Main {
    public static void main(String[] args) {
        String  accessKey ="king";
        String  secretKey ="kinglearn";
        KingApiClient kingApiClient = new KingApiClient(accessKey,secretKey);
//        String name1 = kingApiClient.getName("king1");
//        String name2 = kingApiClient.postName("king2");
        User user = new User();
        user.setName("king");
        String name3 = kingApiClient.restfulName(user);
//        System.out.println(name1);
//        System.out.println(name2);
        System.out.println(name3);
    }
}
