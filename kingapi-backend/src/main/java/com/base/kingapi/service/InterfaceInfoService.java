package com.base.kingapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.model.entity.InterfaceInfo;

/**
* @author 13615
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-08-01 17:37:54
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 校验
     * @param interfaceInfo
     * @param add
     */

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
