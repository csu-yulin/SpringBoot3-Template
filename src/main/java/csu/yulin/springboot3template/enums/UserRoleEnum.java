package csu.yulin.springboot3template.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户角色枚举
 */
@AllArgsConstructor
@Getter
public enum UserRoleEnum {
    USER("用户", "user"),
    ADMIN("管理员", "admin"),
    BAN("被封号", "banned");

    private final String text;

    private final String value;
}
