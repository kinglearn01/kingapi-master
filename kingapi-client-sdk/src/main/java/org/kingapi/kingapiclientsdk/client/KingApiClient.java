package org.kingapi.kingapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.kingapi.kingapiclientsdk.model.User;

import static org.kingapi.kingapiclientsdk.Utils.SignUtil.getSign;


/**
 * ClassName:KingApiClient
 * Description:调用第三方接口
 *
 * @Author:kinglearn
 * @Create2024/8/2 17:15
 * @version1.0
 */
public class KingApiClient {
    private String accessKey;
    private String secretKey;

    public KingApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getName(String name){
        //POST请求
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String content = HttpUtil.get("http://localhost:8123/api/name/",paramMap);
        System.out.println(content);
        return content;


    }

    public String postName(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String content = HttpUtil.post("http://localhost:8123/api/name/post",paramMap);
        System.out.println(content);
        return content;
    }


    private Map<String,String> getHeaderMap(String body){
        Map<String,String> hashmap = new HashMap<>();
        hashmap.put("accessKey",accessKey);
//        hashmap.put("secretKey",secretKey);
        hashmap.put("nonce", RandomUtil.randomNumbers(4));
        hashmap.put("body",body);
        hashmap.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
        hashmap.put("sign",getSign(body,secretKey));
        return hashmap;
    }

    public String  restfulName(User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse content = HttpRequest.post("http://localhost:8123/api/name/rest")
                .charset(StandardCharsets.UTF_8)
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(content.getStatus());
        System.out.println(content.body());
        return content.body();
    }
}
