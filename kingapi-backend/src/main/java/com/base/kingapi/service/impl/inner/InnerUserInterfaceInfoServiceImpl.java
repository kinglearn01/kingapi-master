package com.base.kingapi.service.impl.inner;

import com.base.kingapi.common.ErrorCode;
import com.base.kingapi.exception.BusinessException;
import com.base.kingapi.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.model.entity.UserInterfaceInfo;
import org.example.service.InnerUserInterfaceInfoService;

import javax.annotation.Resource;

/**
 * ClassName:InnerUserInterfaceInfoServiceImpl
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/8 8:45
 * @version1.0
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId){
        return userInterfaceInfoService.invokeCount(interfaceInfoId,userId);
    }

}
