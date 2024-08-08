package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.model.entity.InterfaceInfo;

/**
* @author 13615
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-08-01 17:37:54
*/
public interface InnerInterfaceInfoService{
    /**
     *
     * @param url
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String url, String method);

}
