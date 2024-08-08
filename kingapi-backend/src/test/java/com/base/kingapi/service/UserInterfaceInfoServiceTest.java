package com.base.kingapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * ClassName:UserInterfaceInfoServiceTest
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/4 19:30
 * @version1.0
 */
@SpringBootTest
public class UserInterfaceInfoServiceTest {
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Test
    public void invokeCount() {
        boolean b = userInterfaceInfoService.invokeCount(1, 1);
        System.out.println(b);;
    }
}