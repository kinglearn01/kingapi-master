package com.base.kingapi.controller;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.kingapi.annotation.AuthCheck;
import com.base.kingapi.common.BaseResponse;
import com.base.kingapi.common.DeleteRequest;
import com.base.kingapi.common.ErrorCode;
import com.base.kingapi.common.ResultUtils;
import com.base.kingapi.constant.CommonConstant;
import com.base.kingapi.constant.UserConstant;
import com.base.kingapi.exception.BusinessException;
import com.base.kingapi.exception.ThrowUtils;
import com.base.kingapi.model.dto.userinterfaceinfo.UserInterfaceInfoAddRequest;
import com.base.kingapi.model.dto.userinterfaceinfo.UserInterfaceInfoQueryRequest;
import com.base.kingapi.model.dto.userinterfaceinfo.UserInterfaceInfoUpdateRequest;
import com.base.kingapi.service.UserInterfaceInfoService;
import com.base.kingapi.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.example.model.entity.User;
import org.example.model.entity.UserInterfaceInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 接口参数接口
 *
 *
 * 
 */
@RestController
@RequestMapping("/userInterfaceInfo")
@Slf4j
public class UserInterfaceInfoController {

    @Resource
    private UserInterfaceInfoService userinterfaceInfoService;

    @Resource
    private UserService userService;
    // region 增删改查

    /**
     * 创建
     *
     * @param userinterfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUserInterfaceInfo(@RequestBody UserInterfaceInfoAddRequest userinterfaceInfoAddRequest, HttpServletRequest request) {
        if (userinterfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userinterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userinterfaceInfoAddRequest, userinterfaceInfo);
        userinterfaceInfoService.validUserInterfaceInfo(userinterfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        userinterfaceInfo.setUserId(loginUser.getId());
        boolean result = userinterfaceInfoService.save(userinterfaceInfo);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newUserInterfaceInfoId = userinterfaceInfo.getId();
        return ResultUtils.success(newUserInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUserInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        UserInterfaceInfo oldUserInterfaceInfo = userinterfaceInfoService.getById(id);
        ThrowUtils.throwIf(oldUserInterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldUserInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = userinterfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param userinterfaceInfoUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUserInterfaceInfo(@RequestBody UserInterfaceInfoUpdateRequest userinterfaceInfoUpdateRequest, HttpServletRequest request) {
        if (userinterfaceInfoUpdateRequest == null || userinterfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userinterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userinterfaceInfoUpdateRequest, userinterfaceInfo);
        // 参数校验
        userinterfaceInfoService.validUserInterfaceInfo(userinterfaceInfo, false);
         User loginUser = userService.getLoginUser(request);

        long id = userinterfaceInfoUpdateRequest.getId();
        // 判断是否存在
        UserInterfaceInfo oldUserInterfaceInfo = userinterfaceInfoService.getById(id);
        if (oldUserInterfaceInfo == null){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        if (!oldUserInterfaceInfo.getUserId().equals(loginUser.getId())&&!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = userinterfaceInfoService.updateById(userinterfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<UserInterfaceInfo> getUserInterfaceInfoVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userinterfaceInfo = userinterfaceInfoService.getById(id);

        return ResultUtils.success(userinterfaceInfo);
    }

    /**
     * 分页获取列表（仅管理员）
     *
     * @param userinterfaceInfoQueryRequest
     * @return
     */
    @PostMapping("/list")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<UserInterfaceInfo>> listUserInterfaceInfo(UserInterfaceInfoQueryRequest userinterfaceInfoQueryRequest) {
        UserInterfaceInfo userinterfaceInfoQuery = new UserInterfaceInfo();
        if (userinterfaceInfoQueryRequest != null) {
            BeanUtils.copyProperties(userinterfaceInfoQueryRequest, userinterfaceInfoQuery);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>(userinterfaceInfoQuery);
        List<UserInterfaceInfo> userinterfaceInfoList = userinterfaceInfoService.list(queryWrapper);
        return ResultUtils.success(userinterfaceInfoList);
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param userinterfaceInfoQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<UserInterfaceInfo>> listUserInterfaceInfoByPage(UserInterfaceInfoQueryRequest userinterfaceInfoQueryRequest,
            HttpServletRequest request) {
        if (userinterfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userinterfaceInfoQuery = new UserInterfaceInfo();
        BeanUtils.copyProperties(userinterfaceInfoQueryRequest, userinterfaceInfoQuery);
        long current = userinterfaceInfoQueryRequest.getCurrent();
        long size = userinterfaceInfoQueryRequest.getPageSize();
        String sortField = userinterfaceInfoQueryRequest.getSortField();
        String sortOrder = userinterfaceInfoQueryRequest.getSortOrder();
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>(userinterfaceInfoQuery);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<UserInterfaceInfo> userinterfaceInfoPage = userinterfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(userinterfaceInfoPage);
    }
}
