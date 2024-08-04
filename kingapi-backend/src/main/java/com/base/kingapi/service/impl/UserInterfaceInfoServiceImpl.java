package com.base.kingapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.kingapi.common.ErrorCode;
import com.base.kingapi.exception.BusinessException;
import com.base.kingapi.mapper.UserInterfaceInfoMapper;
import com.base.kingapi.model.entity.UserInterfaceInfo;
import com.base.kingapi.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

/**
* @author 13615

*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userinterfaceInfo, boolean b) {
        if (userinterfaceInfo==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (b) {
            if (userinterfaceInfo.getInterfaceInfoId()<=0||userinterfaceInfo.getUserId()<=0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口或用户不存在");
            }
        }
        if (userinterfaceInfo.getLeftNum()<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"剩余次数不能小于0");
        }
    }

    @Override
    public synchronized boolean invokeCount(long interfaceInfoId, long userId) {
        if(interfaceInfoId<=0||userId<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId",interfaceInfoId);
        updateWrapper.eq("userId",userId);
        updateWrapper.gt("leftNum",0);
        updateWrapper.setSql("leftNum=leftNum-1,totalNum=totalNum+1");
        return this.update(updateWrapper);
    }

}





