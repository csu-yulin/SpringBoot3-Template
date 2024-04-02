package csu.yulin.springboot3template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import csu.yulin.springboot3template.constant.CommonConstant;
import csu.yulin.springboot3template.enums.ResultCode;
import csu.yulin.springboot3template.enums.UserRoleEnum;
import csu.yulin.springboot3template.exception.BusinessException;
import csu.yulin.springboot3template.mapper.UserMapper;
import csu.yulin.springboot3template.model.dto.user.UserQueryRequest;
import csu.yulin.springboot3template.model.entity.User;
import csu.yulin.springboot3template.model.entity.UserDetail;
import csu.yulin.springboot3template.service.UserService;
import csu.yulin.springboot3template.utils.JwtUtil;
import csu.yulin.springboot3template.utils.SqlUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 刘飘
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-03-27 19:39:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtUtil jwtUtil;

    /**
     * 用户登录方法
     *
     * @param user 包含登录所需的用户名和密码信息的用户对象
     * @return 登录成功后生成的 JWT Token
     */
    @Override
    public String login(User user) throws BusinessException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // 进行身份验证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        UserDetail loginUser = (UserDetail) authenticate.getPrincipal();

        // 获取认证成功后的用户信息
        user = loginUser.getUser();
        UserRoleEnum userRoleEnum = UserRoleEnum.fromValue(user.getRole());
        if (Objects.isNull(userRoleEnum)) {
            throw new BusinessException(ResultCode.DATA_NOT_FOUND);
        }
        // 生成 JWT Token
        return jwtUtil.generateToken(user.getUsername(), userRoleEnum);
    }

    /**
     * 用户注册方法
     *
     * @param user 包含注册所需的用户名和密码信息的用户对象
     * @return 注册成功后生成的 JWT Token
     */
    @Override
    public String register(User user) throws BusinessException {
        // 默认分配用户角色: USER
        user.setRole(UserRoleEnum.USER.getValue());
        save(user);
        UserRoleEnum userRoleEnum = UserRoleEnum.fromValue(user.getRole());
        if (Objects.isNull(userRoleEnum)) {
            throw new BusinessException(ResultCode.DATA_NOT_FOUND);
        }
        // 生成 JWT Token
        return jwtUtil.generateToken(user.getUsername(), userRoleEnum);
    }

    /**
     * 获取当前登录用户
     *
     * @return 当前登录用户
     */
    @Override
    public User getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetail) {
            return ((UserDetail) principal).getUser();
        }
        return null;
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        Long id = userQueryRequest.getId();
        String username = userQueryRequest.getUsername();
        String email = userQueryRequest.getEmail();
        String role = userQueryRequest.getRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(email), "email", email);
        queryWrapper.eq(StringUtils.isNotBlank(role), "role", role);
        queryWrapper.like(StringUtils.isNotBlank(username), "userName", username);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);

        return queryWrapper;
    }
}




