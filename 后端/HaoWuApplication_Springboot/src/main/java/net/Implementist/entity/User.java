package net.Implementist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "Uid", type = IdType.AUTO)
    private Integer Uid;

    @ApiModelProperty(value = "用户账号")
    @TableField("Uaccount")
    private String Uaccount;

    @ApiModelProperty(value = "密码")
    @TableField("Upwd")
    private String Upwd;

    @ApiModelProperty(value = "环信id")
    @TableField("Uhxid")
    private String Uhxid;

    @ApiModelProperty(value = "昵称")
    @TableField("Unickname")
    private String Unickname;

    @ApiModelProperty(value = "头像,储存图片路径")
    @TableField("Uphoto")
    private String Uphoto;

    @ApiModelProperty(value = "余额")
    @TableField("Ubalance")
    private Double Ubalance;

    @ApiModelProperty(value = "性别，1是男，2是女")
    @TableField("Usex")
    private Integer Usex;

    @ApiModelProperty(value = "学校")
    @TableField("Uschool")
    private String Uschool;

    @ApiModelProperty(value = "地址")
    @TableField("Uaddress")
    private String Uaddress;

    @ApiModelProperty(value = "信誉值")
    @TableField("Ureputation")
    private Integer Ureputation;

    @ApiModelProperty(value = "电话")
    @TableField("Utel")
    private String Utel;

    @ApiModelProperty(value = "用户状态")
    @TableField("Ustate")
    private Integer Ustate;


}
