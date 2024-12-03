package pers.jhshop.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.order.model.entity.Refunds;
import pers.jhshop.order.model.req.RefundsCreateReq;
import pers.jhshop.order.model.req.RefundsQueryReq;
import pers.jhshop.order.model.req.RefundsUpdateReq;
import pers.jhshop.order.model.vo.RefundsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 订单退款表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
public interface IRefundsService extends IService<Refunds> {

    void createBiz(RefundsCreateReq createReq);

    void updateBiz(RefundsUpdateReq updateReq);

    RefundsVO getByIdBiz(Long id);

    Page<RefundsVO> pageBiz(RefundsQueryReq queryReq);

    Page<Refunds> page(RefundsQueryReq queryReq);

    List<Refunds> listByQueryReq(RefundsQueryReq queryReq);

    Map<Long, Refunds> getIdEntityMap(List<Long> ids);

    Refunds getOneByQueryReq(RefundsQueryReq queryReq);

}
