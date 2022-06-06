package net.Implementist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("token")
@ApiModel(value="Token对象", description="")
public class Token implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "token的id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "token的字符串")
    private String apptoken;

    @ApiModelProperty(value = "token有效时间，在有效时间内不去获取")
    private String expires;

    @ApiModelProperty(value = "当前app的UUID值")
    private String application;


}
