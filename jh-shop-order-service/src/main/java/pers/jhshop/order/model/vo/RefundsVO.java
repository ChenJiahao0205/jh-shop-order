package pers.jhshop.order.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import pers.jhshop.common.entity.BaseVo;

/**
 * <p>
 * 订单退款表VO
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RefundsVO", description = "订单退款表列表展示VO")
public class RefundsVO extends BaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "退款记录ID，主键")
    private Long id;

    @ApiModelProperty(value = "订单ID，外键关联订单表")
    private Integer orderId;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "退款状态（0=待处理，1=已退款）")
    private Boolean refundStatus;

    @ApiModelProperty(value = "退款时间")
    private LocalDateTime refundTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "描述")
    private String description;

}
