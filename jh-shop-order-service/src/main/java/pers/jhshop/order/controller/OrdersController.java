package pers.jhshop.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.order.consts.JhShopOrderApiConstants;
import pers.jhshop.order.model.req.OrdersCreateReq;
import pers.jhshop.order.model.req.OrdersQueryReq;
import pers.jhshop.order.model.req.OrdersUpdateReq;
import pers.jhshop.order.model.vo.OrdersVO;
import pers.jhshop.order.service.IOrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@RestController
@RequestMapping(JhShopOrderApiConstants.API_USER + "orders")
@RequiredArgsConstructor
public class OrdersController {
    private final IOrdersService ordersService;

    @PostMapping("create")
    public ResultBo create(@RequestBody OrdersCreateReq createReq) {
        ordersService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody OrdersUpdateReq updateReq) {
        ordersService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<OrdersVO> getById(Long id) {
        OrdersVO vo = ordersService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<OrdersVO>> page(@RequestBody OrdersQueryReq queryReq) {
        Page page = ordersService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

