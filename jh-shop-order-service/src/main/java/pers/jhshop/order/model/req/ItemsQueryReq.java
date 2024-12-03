package pers.jhshop.order.model.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.order.model.entity.Items;
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
 * 订单商品表查询Req
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ItemsQueryReq", description = "订单商品表查询Req")
public class ItemsQueryReq extends Page<Items> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单商品ID，主键")
    private Long id;

    @ApiModelProperty(value = "订单ID，外键关联订单表")
    private Integer orderId;

    @ApiModelProperty(value = "商品ID，外键关联商品表")
    private Integer productId;

    @ApiModelProperty(value = "商品数量")
    private Integer quantity;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal price;

    @ApiModelProperty(value = "商品总价（数量 * 单价）")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "商品添加到订单的时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "描述-模糊匹配")
    private String descriptionLike;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    private Boolean validFlag;



}
