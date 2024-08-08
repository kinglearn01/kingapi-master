package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.model.entity.UserInterfaceInfo;

public interface InnerUserInterfaceInfoService{

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId,long userId);




}