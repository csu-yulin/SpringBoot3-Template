package csu.yulin.springboot3template.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import csu.yulin.springboot3template.common.response.ResultResponse;
import csu.yulin.springboot3template.enums.ResultCode;
import csu.yulin.springboot3template.enums.UserRoleEnum;
import csu.yulin.springboot3template.exception.BusinessException;
import csu.yulin.springboot3template.model.dto.user.*;
import csu.yulin.springboot3template.model.entity.User;
import csu.yulin.springboot3template.model.vo.user.UserVO;
import csu.yulin.springboot3template.service.UserService;
import csu.yulin.springboot3template.utils.ThrowUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * 用户接口
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "用户接口", description = "用户登录、注册以及管理等接口")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户登录
     *
     * @param userLoginRequest 包含用户身份信息的User对象
     * @return 登录成功后生成的访问令牌
     */
    @Operation(summary = "用户登录", description = "用户登录接口")
    @PostMapping("/login")
    public ResultResponse<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        if (Objects.isNull(userLoginRequest) || StringUtils.isAnyBlank(userLoginRequest.getUsername(), userLoginRequest.getPassword())) {
            throw new BusinessException(ResultCode.PARAMS_IS_BLANK);
        }
        User user = new User();
        BeanUtils.copyProperties(userLoginRequest, user);
        String token = userService.login(user);
        return ResultResponse.success(token);
    }

    /**
     * 用户注册接口
     *
     * @param userRegisterRequest 包含注册所需信息的用户对象
     * @return 注册成功后生成的 JWT Token
     */
    @Operation(summary = "用户注册", description = "用户注册接口")
    @PostMapping("/register")
    public ResultResponse<String> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (Objects.isNull(userRegisterRequest) || StringUtils.isAnyBlank(userRegisterRequest.getUsername(),
                userRegisterRequest.getPassword(), userRegisterRequest.getEmail())) {
            throw new BusinessException(ResultCode.PARAMS_IS_BLANK);
        }

        User user = new User();
        BeanUtils.copyProperties(userRegisterRequest, user);

        // 对用户密码进行加密
        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegisterRequest.getPassword());
        user.setPassword(encryptedPassword);
        // 注册用户并获取生成的 JWT Token
        String token = userService.register(user);
        // 返回成功响应，包含生成的 Token
        return ResultResponse.success(token);
    }

    /**
     * 添加用户
     *
     * @param userAddRequest 用户对象
     * @return 返回用户ID
     */
    @Operation(summary = "添加用户", description = "管理员权限下添加用户接口")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResultResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
        if (Objects.isNull(userAddRequest) || StringUtils.isAnyBlank(userAddRequest.getUsername(),
                userAddRequest.getPassword(), userAddRequest.getEmail(), userAddRequest.getRole())) {
            throw new BusinessException(ResultCode.PARAMS_IS_BLANK);
        }
        if (!UserRoleEnum.containsRole(userAddRequest.getRole())) {
            throw new BusinessException(ResultCode.USER_ROLE_UNKNOWN);
        }

        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);

        // 对用户密码进行BCrypt加密
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        boolean result = userService.save(user);
        ThrowUtils.throwIf(!result, ResultCode.OPERATION_FAILURE);

        return ResultResponse.success(user.getId());
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 返回操作结果，true表示删除成功，false表示删除失败
     */
    @Operation(summary = "删除用户", description = "管理员权限下删除用户接口")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete")
    public ResultResponse<Boolean> deleteUser(@RequestBody Long id) {
        if (Objects.isNull(id)) {
            throw new BusinessException(ResultCode.PARAMS_IS_BLANK);
        }

        boolean result = userService.removeById(id);
        ThrowUtils.throwIf(!result, ResultCode.OPERATION_FAILURE);

        return ResultResponse.success(true);
    }

    /**
     * 更新用户信息
     *
     * @param userUpdateRequest 包含更新后用户信息的对象，必须包含用户ID
     * @return 返回操作结果，true表示更新成功，false表示更新失败
     */
    @Operation(summary = "更新用户", description = "更新用户信息接口")
    @PostMapping("/update")
    public ResultResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (Objects.isNull(userUpdateRequest) || Objects.isNull(userUpdateRequest.getId())) {
            throw new BusinessException(ResultCode.PARAMS_IS_BLANK);
        }

        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);

        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ResultCode.OPERATION_FAILURE);

        return ResultResponse.success(true);
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Operation(summary = "根据用户ID获取用户信息", description = "根据用户ID获取用户信息接口")
    @GetMapping("/get")
    public ResultResponse<UserVO> getUserById(Long id) {
        if (Objects.isNull(id)) {
            throw new BusinessException(ResultCode.PARAMS_IS_BLANK);
        }
        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, ResultCode.OPERATION_FAILURE);

        return ResultResponse.success(UserVO.fromUser(user));
    }

    /**
     * 分页查询用户信息
     *
     * @param userQueryRequest 用户查询请求对象
     * @return 用户信息分页结果
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "分页查询用户信息", description = "管理员权限下的分页查询用户信息接口")
    @PostMapping("/page")
    public ResultResponse<Page<UserVO>> page(@RequestBody UserQueryRequest userQueryRequest) {
        if (Objects.isNull(userQueryRequest)) {
            throw new BusinessException(ResultCode.PARAMS_IS_BLANK);
        }

        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();

        Page<User> pageInfo = new Page<>(current, size);
        userService.page(pageInfo, userService.getQueryWrapper(userQueryRequest));

        // 将User对象转换为UserVO对象
        Page<UserVO> userVOPage = (Page<UserVO>) pageInfo.convert(UserVO::fromUser);

        return ResultResponse.success(userVOPage);
    }
}