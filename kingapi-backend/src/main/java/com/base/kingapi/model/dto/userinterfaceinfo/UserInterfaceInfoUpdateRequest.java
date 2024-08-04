package com.base.kingapi.model.dto.userinterfaceinfo;

import com.base.kingapi.common.PageRequest;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ClassName:UserInterfaceInfoUpdateRequest
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/4 15:54
 * @version1.0
 *
 */

@Data
public class UserInterfaceInfoUpdateRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

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
