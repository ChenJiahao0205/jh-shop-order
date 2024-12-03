package pers.jhshop.order.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("orders")
@ApiModel(value = "Orders对象", description = "订单表")
public class Orders extends Model<Orders> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID，主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID，外键关联用户表")
    @TableField("USER_ID")
    private Integer userId;

    @ApiModelProperty(value = "订单号，唯一标识")
    @TableField("ORDER_NO")
    private String orderNo;

    @ApiModelProperty(value = "订单总金额")
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "订单状态（0=待支付，1=已支付，2=待发货，3=已发货，4=已完成，5=已取消）")
    @TableField("STATUS")
    private Boolean status;

    @ApiModelProperty(value = "支付状态（0=未支付，1=已支付）")
    @TableField("PAYMENT_STATUS")
    private Boolean paymentStatus;

    @ApiModelProperty(value = "收货地址")
    @TableField("SHIPPING_ADDRESS")
    private String shippingAddress;

    @ApiModelProperty(value = "订单创建时间")
    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "订单更新时间")
    @TableField("UPDATED_AT")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "支付时间")
    @TableField("PAYMENT_TIME")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "发货时间")
    @TableField("SHIPPING_TIME")
    private LocalDateTime shippingTime;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    @TableField("VALID_FLAG")
    private Boolean validFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
