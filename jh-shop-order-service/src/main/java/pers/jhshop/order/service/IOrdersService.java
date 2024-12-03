package pers.jhshop.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.order.model.entity.Orders;
import pers.jhshop.order.model.req.OrdersCreateReq;
import pers.jhshop.order.model.req.OrdersQueryReq;
import pers.jhshop.order.model.req.OrdersUpdateReq;
import pers.jhshop.order.model.vo.OrdersVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
public interface IOrdersService extends IService<Orders> {

    void createBiz(OrdersCreateReq createReq);

    void updateBiz(OrdersUpdateReq updateReq);

    OrdersVO getByIdBiz(Long id);

    Page<OrdersVO> pageBiz(OrdersQueryReq queryReq);

    Page<Orders> page(OrdersQueryReq queryReq);

    List<Orders> listByQueryReq(OrdersQueryReq queryReq);

    Map<Long, Orders> getIdEntityMap(List<Long> ids);

    Orders getOneByQueryReq(OrdersQueryReq queryReq);

}
