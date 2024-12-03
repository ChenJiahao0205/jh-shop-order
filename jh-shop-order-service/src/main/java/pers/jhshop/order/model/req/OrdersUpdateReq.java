package pers.jhshop.order.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表修改Req
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OrdersUpdateReq", description = "订单表修改Req")
public class OrdersUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "订单ID，主键")
    private Long id;

    @ApiModelProperty(value = "用户ID，外键关联用户表")
    private Integer userId;

    @ApiModelProperty(value = "订单号，唯一标识")
    private String orderNo;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "订单状态（0=待支付，1=已支付，2=待发货，3=已发货，4=已完成，5=已取消）")
    private Boolean status;

    @ApiModelProperty(value = "支付状态（0=未支付，1=已支付）")
    private Boolean paymentStatus;

    @ApiModelProperty(value = "收货地址")
    private String shippingAddress;

    @ApiModelProperty(value = "订单创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "订单更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime shippingTime;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    private Boolean validFlag;
}
