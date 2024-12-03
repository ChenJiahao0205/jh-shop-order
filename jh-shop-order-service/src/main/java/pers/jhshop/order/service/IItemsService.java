package pers.jhshop.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.order.model.entity.Items;
import pers.jhshop.order.model.req.ItemsCreateReq;
import pers.jhshop.order.model.req.ItemsQueryReq;
import pers.jhshop.order.model.req.ItemsUpdateReq;
import pers.jhshop.order.model.vo.ItemsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 订单商品表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
public interface IItemsService extends IService<Items> {

    void createBiz(ItemsCreateReq createReq);

    void updateBiz(ItemsUpdateReq updateReq);

    ItemsVO getByIdBiz(Long id);

    Page<ItemsVO> pageBiz(ItemsQueryReq queryReq);

    Page<Items> page(ItemsQueryReq queryReq);

    List<Items> listByQueryReq(ItemsQueryReq queryReq);

    Map<Long, Items> getIdEntityMap(List<Long> ids);

    Items getOneByQueryReq(ItemsQueryReq queryReq);

}
