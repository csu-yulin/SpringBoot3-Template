package csu.yulin.springboot3template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import csu.yulin.springboot3template.mapper.UserMapper;
import csu.yulin.springboot3template.model.entity.User;
import csu.yulin.springboot3template.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author 刘飘
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-03-27 19:39:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    /**
     * 根据请求获取当前登录用户。
     *
     * @param request HttpServletRequest 对象，包含当前请求的信息。
     * @return 当前登录用户对象。
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        return null;
    }
}




