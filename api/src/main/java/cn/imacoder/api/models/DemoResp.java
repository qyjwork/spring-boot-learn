package cn.imacoder.api.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value ="示例输出实体" ,description = "示例输出实体")
public class DemoResp {


    @ApiModelProperty(name = "Id", value = "唯一标识Id")
    private String id;

    @ApiModelProperty(name = "name", value = "名称", position = 1)
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
