package pers.jhshop.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.order.consts.JhShopOrderApiConstants;
import pers.jhshop.order.model.req.PaymentsCreateReq;
import pers.jhshop.order.model.req.PaymentsQueryReq;
import pers.jhshop.order.model.req.PaymentsUpdateReq;
import pers.jhshop.order.model.vo.PaymentsVO;
import pers.jhshop.order.service.IPaymentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 订单支付表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@RestController
@RequestMapping(JhShopOrderApiConstants.API_USER + "payments")
@RequiredArgsConstructor
public class PaymentsController {
    private final IPaymentsService paymentsService;

    @PostMapping("create")
    public ResultBo create(@RequestBody PaymentsCreateReq createReq) {
        paymentsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody PaymentsUpdateReq updateReq) {
        paymentsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<PaymentsVO> getById(Long id) {
        PaymentsVO vo = paymentsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<PaymentsVO>> page(@RequestBody PaymentsQueryReq queryReq) {
        Page page = paymentsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

