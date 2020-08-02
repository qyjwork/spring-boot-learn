package cn.imacoder.userauth.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录输出实体")
public class LoginResp {

    @ApiModelProperty(value = "Token")
    private String token;

    @ApiModelProperty(value = "有效时间")
    private int expire;

    public LoginResp(String token, int expire) {
        this.token = token;
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}
