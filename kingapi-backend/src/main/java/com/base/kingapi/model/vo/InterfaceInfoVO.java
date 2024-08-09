package com.base.kingapi.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.example.model.entity.InterfaceInfo;

import javax.swing.text.StyledEditorKit;

/**
 * ClassName:InterfaceInfoVO
 * Description:接口信息封装信息
 *
 * @Author:kinglearn
 * @Create2024/8/1 20:56
 * @version1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoVO extends InterfaceInfo {

    /**
     * 调用次数
     */
    private Integer totalNum;

    private static final long serialVersionUID = -6161083307553076899L;
}
