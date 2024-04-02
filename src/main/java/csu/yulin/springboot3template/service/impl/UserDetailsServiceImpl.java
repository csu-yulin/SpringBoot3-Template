package csu.yulin.springboot3template.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import csu.yulin.springboot3template.enums.ResultCode;
import csu.yulin.springboot3template.exception.BusinessException;
import csu.yulin.springboot3template.model.entity.User;
import csu.yulin.springboot3template.model.entity.UserDetail;
import csu.yulin.springboot3template.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    /**
     * 根据用户名加载用户详细信息
     *
     * @param username 要加载的用户的用户名
     * @return 包含用户详细信息的 UserDetails 对象
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(Wrappers.lambdaQuery(User.class)
                .eq(User::getUsername, username));
        // 如果未找到用户，抛出未授权异常
        if (user == null) {
            throw new BusinessException(ResultCode.DATA_NOT_FOUND);
        }
        // 创建用户详情对象并返回
        return new UserDetail(user);
    }

}