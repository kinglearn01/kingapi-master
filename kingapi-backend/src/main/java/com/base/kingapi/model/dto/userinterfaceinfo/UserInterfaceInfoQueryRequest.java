package com.base.kingapi.model.dto.userinterfaceinfo;

import com.base.kingapi.common.PageRequest;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ClassName:UserInterfaceInfoQueryRequest
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/4 15:54
 * @version1.0
 */
@EqualsAndHashCode()
@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

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
    private static final long serialVersionUID = 1L;
}
