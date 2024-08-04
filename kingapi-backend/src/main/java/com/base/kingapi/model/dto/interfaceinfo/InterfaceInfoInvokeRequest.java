package com.base.kingapi.model.dto.interfaceinfo;

import java.io.Serializable;
import lombok.Data;

/**
 * 接口调用
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    private Long id;

    /**
     * 请求参数
     */
    private String userRequestParams;

    private static final long serialVersionUID = 1L;



}