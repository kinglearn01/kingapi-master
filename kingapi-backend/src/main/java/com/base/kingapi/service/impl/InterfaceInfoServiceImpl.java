package com.base.kingapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.kingapi.common.ErrorCode;
import com.base.kingapi.exception.BusinessException;
import com.base.kingapi.mapper.InterfaceInfoMapper;
import com.base.kingapi.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.example.model.entity.InterfaceInfo;
import org.springframework.stereotype.Service;

/**
* @author 13615
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2024-08-01 17:37:54
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
     String name = interfaceInfo.getName();
        if (add) {
            if (StringUtils.isAnyBlank(name)){
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name)&&name.length()>50){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"名称过长");
        }
    }
   }




