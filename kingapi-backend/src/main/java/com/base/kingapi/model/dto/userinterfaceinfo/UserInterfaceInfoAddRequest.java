package com.base.kingapi.model.dto.userinterfaceinfo;

import java.io.Serializable;
import lombok.Data;

/**
 * ClassName:UserInterfaceInfoAddRequest
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/4 15:53
 * @version1.0
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {


    /**
     * 调用用户 id
     */
    private Long userId;

    /**
     * 接口 id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

    /**
     * 0-正常，1-禁用
     */
    private Integer status;

}
