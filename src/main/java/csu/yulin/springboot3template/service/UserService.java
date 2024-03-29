package csu.yulin.springboot3template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import csu.yulin.springboot3template.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author 刘飘
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-03-27 19:39:36
 */
public interface UserService extends IService<User> {

    /**
     * 根据请求获取当前登录用户。
     *
     * @param request HttpServletRequest 对象，包含当前请求的信息。
     * @return 当前登录用户对象。
     */
    User getLoginUser(HttpServletRequest request);

}
