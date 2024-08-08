package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.model.entity.User;

/**
 * 用户服务
 *
 *
 * 
 */
public interface InnerUserService {

    /**
     * 判断是否给用户分配签名
     * @param accesskey
     * @return
     */
    User getInvokeUser(String accesskey);

}
