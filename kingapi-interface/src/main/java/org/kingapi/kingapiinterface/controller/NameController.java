package org.kingapi.kingapiinterface.controller;
import org.kingapi.kingapiclientsdk.Utils.SignUtil;
import org.kingapi.kingapiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:NameController
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/2 15:30
 * @version1.0
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/get")
    public String getName(String name, HttpServletRequest request){
        return "GET 你的名字是"+name;
    }
    @PostMapping("/post")
    public String postName(@RequestParam String name){
        return "Post 你的名字是"+name;
    }
    @PostMapping("/rest")
    public String restfulName(@RequestBody User user, HttpServletRequest request){
//        String accessKey = request.getHeader("accessKey");
//        String nonce = request.getHeader( "nonce");
//        String body = request.getHeader("body");
//        String timestamp = request.getHeader("timestamp");
//        String sign = request.getHeader("sign");
//        if (!accessKey.equals("king")){
//            throw new RuntimeException("无权限");
//        }
//        if (Long.parseLong(nonce)>10000){
//            throw new RuntimeException("无权限");
//        }
//        // todo 时间和当前时间不能超过五分钟
////        if (timestamp){
////
////        }
//        String serverSign = SignUtil.getSign(body, "kinglearn");
//        if (!sign.equals(serverSign)){
//            throw new RuntimeException("无权限");
//        }

        return "RESTFul 你的名字是"+user;
    }

}
