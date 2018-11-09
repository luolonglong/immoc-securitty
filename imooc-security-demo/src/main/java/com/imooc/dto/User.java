package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author Gatsby.Luo
 * @date 2018-10-23
 */
@Data
public class User {

    public interface UserSimpleView {
    }

    ;

    public interface UserDetailView extends UserSimpleView {
    }

    ;

    private String id;

    @JsonView(UserSimpleView.class)
    private String username;

    @JsonView(UserDetailView.class)
    @NotNull(message = "密码不能为空")
    private String password;

    @Past
    @JsonView(UserSimpleView.class)
    private Date birthday;
}
