package com.base.kingapi.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.kingapi.common.ErrorCode;
import com.base.kingapi.exception.BusinessException;
import com.base.kingapi.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.model.entity.InterfaceInfo;
import org.example.model.entity.User;
import org.example.service.InnerInterfaceInfoService;

import javax.annotation.Resource;

/**
 * ClassName:InnerInterfaceInfoServiceImpl
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/7 21:04
 *
 */
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {
   @Resource
   private InterfaceInfoMapper interfaceInfoMapper;
    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if (StringUtils.isAnyBlank(url,method)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("url",url);
        objectQueryWrapper.eq("method",method);
        return interfaceInfoMapper.selectOne(objectQueryWrapper);
    }
}
