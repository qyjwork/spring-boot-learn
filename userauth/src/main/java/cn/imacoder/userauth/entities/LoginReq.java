package cn.imacoder.userauth.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录请求实体")
public class LoginReq {

    @ApiModelProperty(value = "用户名")
    private String userId;

    @ApiModelProperty(value = "用户密码")
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
