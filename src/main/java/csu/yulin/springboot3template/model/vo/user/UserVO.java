package csu.yulin.springboot3template.model.vo.user;

import csu.yulin.springboot3template.model.entity.User;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username; // 脱敏后的用户名
    private String email; // 脱敏后的电子邮箱
    private String role;
    private LocalDateTime createdTime;


    // 构造方法或静态方法用于转换User对象为UserVO对象
    public static UserVO fromUser(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        // 对电子邮箱进行脱敏处理，例如只显示@符号前的部分加上星号
        userVO.setEmail(maskEmail(user.getEmail()));
        userVO.setRole(user.getRole());
        userVO.setCreatedTime(user.getCreatedTime());
        return userVO;
    }

    /*
     * 对电子邮箱进行脱敏处理
     */
    private static String maskEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "";
        }
        int atIndex = email.indexOf("@");
        if (atIndex <= 0) {
            return email;
        }
        // 显示@符号前的部分加上星号
        return "****" + email.substring(atIndex);
    }
}
