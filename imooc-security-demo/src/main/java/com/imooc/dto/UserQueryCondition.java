package com.imooc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gatsby.Luo
 * @date 2018-10-23
 */
@Data
public class UserQueryCondition {

    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("从多少岁")
    private int age;
    @ApiModelProperty("到多少岁")
    private int ageTo;

    private String xxx;
}
