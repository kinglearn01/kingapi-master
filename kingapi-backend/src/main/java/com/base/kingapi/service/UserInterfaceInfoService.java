package com.base.kingapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.model.entity.UserInterfaceInfo;

/**
* @author 13615

*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userinterfaceInfo, boolean b);

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId,long userId);
}

