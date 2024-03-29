package csu.yulin.springboot3template.controller;


import csu.yulin.springboot3template.common.ResultResponse;
import csu.yulin.springboot3template.model.entity.User;
import csu.yulin.springboot3template.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public ResultResponse<List<User>> get() {
        List<User> list = userService.list();
        return ResultResponse.success(list);
    }
}
