package pers.jhshop.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.order.consts.JhShopOrderApiConstants;
import pers.jhshop.order.model.req.RefundsCreateReq;
import pers.jhshop.order.model.req.RefundsQueryReq;
import pers.jhshop.order.model.req.RefundsUpdateReq;
import pers.jhshop.order.model.vo.RefundsVO;
import pers.jhshop.order.service.IRefundsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 订单退款表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@RestController
@RequestMapping(JhShopOrderApiConstants.API_USER + "refunds")
@RequiredArgsConstructor
public class RefundsController {
    private final IRefundsService refundsService;

    @PostMapping("create")
    public ResultBo create(@RequestBody RefundsCreateReq createReq) {
        refundsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody RefundsUpdateReq updateReq) {
        refundsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<RefundsVO> getById(Long id) {
        RefundsVO vo = refundsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<RefundsVO>> page(@RequestBody RefundsQueryReq queryReq) {
        Page page = refundsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

