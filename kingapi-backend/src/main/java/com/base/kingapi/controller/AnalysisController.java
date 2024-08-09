package com.base.kingapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.kingapi.annotation.AuthCheck;
import com.base.kingapi.common.BaseResponse;
import com.base.kingapi.common.ErrorCode;
import com.base.kingapi.common.ResultUtils;
import com.base.kingapi.exception.BusinessException;
import com.base.kingapi.mapper.UserInterfaceInfoMapper;
import com.base.kingapi.model.vo.InterfaceInfoVO;
import com.base.kingapi.service.InterfaceInfoService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.example.model.entity.InterfaceInfo;
import org.example.model.entity.UserInterfaceInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.management.relation.RelationSupport;

/**
 * ClassName:AnalysisController
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/9 9:43
 * @version1.0
 */
@RestController
@Slf4j
@RequestMapping("analysis")
public class AnalysisController {
    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Resource
    private InterfaceInfoService interfaceInfoService;
    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo(){
        List<UserInterfaceInfo> userInterfaceInfos = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        Map<Long, List<UserInterfaceInfo>> collect = userInterfaceInfos.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
         QueryWrapper<InterfaceInfo> InterfaceInfoQueryWrapper = new QueryWrapper<>();
         InterfaceInfoQueryWrapper.in("id",collect.keySet());
        List<InterfaceInfo> list = interfaceInfoService.list(InterfaceInfoQueryWrapper);
        if (CollectionUtils.isEmpty(list)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
         List<InterfaceInfoVO> collectList = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            int totalNum = collect.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(collectList);

    }
}
