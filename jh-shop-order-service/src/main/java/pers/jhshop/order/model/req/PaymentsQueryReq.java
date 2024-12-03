package pers.jhshop.order.model.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.order.model.entity.Payments;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单支付表查询Req
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PaymentsQueryReq", description = "订单支付表查询Req")
public class PaymentsQueryReq extends Page<Payments> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "支付记录ID，主键")
    private Long id;

    @ApiModelProperty(value = "订单ID，外键关联订单表")
    private Integer orderId;

    @ApiModelProperty(value = "支付方式（如支付宝，微信支付等）")
    private String paymentMethod;

    @ApiModelProperty(value = "支付方式（如支付宝，微信支付等）-模糊匹配")
    private String paymentMethodLike;

    @ApiModelProperty(value = "支付状态（0=未支付，1=已支付）")
    private Boolean paymentStatus;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "描述-模糊匹配")
    private String descriptionLike;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    private Boolean validFlag;



}
