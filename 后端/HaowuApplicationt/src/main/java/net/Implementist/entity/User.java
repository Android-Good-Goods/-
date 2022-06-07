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
    private Integer uid;

    @ApiModelProperty(value = "用户账号")
    @TableField("Uaccount")
    private String account;

    @ApiModelProperty(value = "密码")
    @TableField("Upwd")
    private String password;

    @ApiModelProperty(value = "环信id")
    @TableField("Uhxid")
    private String hxid;

    @ApiModelProperty(value = "昵称")
    @TableField("Unickname")
    private String nickname;

    @ApiModelProperty(value = "头像,储存图片路径")
    @TableField("Uphoto")
    private String headphoto;

    @ApiModelProperty(value = "余额")
    @TableField("Ubalance")
    private Double balance;

    @ApiModelProperty(value = "性别，1是男，2是女")
    @TableField("Usex")
    private Integer sex;

    @ApiModelProperty(value = "学校")
    @TableField("Uschool")
    private String school;

    @ApiModelProperty(value = "地址")
    @TableField("Uaddress")
    private String address;

    @ApiModelProperty(value = "信誉值")
    @TableField("Ureputation")
    private Integer reputation;

    @ApiModelProperty(value = "电话")
    @TableField("Utel")
    private String tel;

    @ApiModelProperty(value = "用户状态")
    @TableField("Ustate")
    private Integer state;


}
