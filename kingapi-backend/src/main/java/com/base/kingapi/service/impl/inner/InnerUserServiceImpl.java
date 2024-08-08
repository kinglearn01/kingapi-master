package com.base.kingapi.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.kingapi.common.ErrorCode;
import com.base.kingapi.exception.BusinessException;
import com.base.kingapi.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.model.entity.User;
import org.example.service.InnerUserService;


import javax.annotation.Resource;

/**
 * ClassName:InnerUserServiceImpl
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/7 21:01
 * @version1.0
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User getInvokeUser(String accesskey) {
        if (StringUtils.isBlank(accesskey)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("accesskey",accesskey);
        return userMapper.selectOne(objectQueryWrapper);
    }
}
