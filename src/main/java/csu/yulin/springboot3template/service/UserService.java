package csu.yulin.springboot3template.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import csu.yulin.springboot3template.model.dto.user.UserQueryRequest;
import csu.yulin.springboot3template.model.entity.User;

/**
 * @author 刘飘
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-03-27 19:39:36
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param user 用户对象，包含登录所需的用户名和密码信息
     * @return 登录成功后生成的 JWT Token
     */
    String login(User user);

    /**
     * 用户注册
     *
     * @param user 用户对象，包含注册所需的用户名和密码信息
     * @return 注册成功后生成的 JWT Token
     */
    String register(User user);

    /**
     * 获取当前登录用户
     *
     * @return 当前登录用户
     */
    User getLoginUser();

    /**
     * 根据用户查询请求对象构建查询条件包装器
     *
     * @param userQueryRequest 用户查询请求对象
     * @return 查询条件包装器
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
