package pers.jhshop.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.order.consts.JhShopOrderApiConstants;
import pers.jhshop.order.model.req.ItemsCreateReq;
import pers.jhshop.order.model.req.ItemsQueryReq;
import pers.jhshop.order.model.req.ItemsUpdateReq;
import pers.jhshop.order.model.vo.ItemsVO;
import pers.jhshop.order.service.IItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 订单商品表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@RestController
@RequestMapping(JhShopOrderApiConstants.API_USER + "items")
@RequiredArgsConstructor
public class ItemsController {
    private final IItemsService itemsService;

    @GetMapping("test")
    public ResultBo test() {
        return ResultBo.success();
    }

    @PostMapping("create")
    public ResultBo create(@RequestBody ItemsCreateReq createReq) {
        itemsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody ItemsUpdateReq updateReq) {
        itemsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<ItemsVO> getById(Long id) {
        ItemsVO vo = itemsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<ItemsVO>> page(@RequestBody ItemsQueryReq queryReq) {
        Page page = itemsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

