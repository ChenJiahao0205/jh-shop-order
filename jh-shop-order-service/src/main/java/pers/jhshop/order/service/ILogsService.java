package pers.jhshop.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.order.model.entity.Logs;
import pers.jhshop.order.model.req.LogsCreateReq;
import pers.jhshop.order.model.req.LogsQueryReq;
import pers.jhshop.order.model.req.LogsUpdateReq;
import pers.jhshop.order.model.vo.LogsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 订单操作记录表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
public interface ILogsService extends IService<Logs> {

    void createBiz(LogsCreateReq createReq);

    void updateBiz(LogsUpdateReq updateReq);

    LogsVO getByIdBiz(Long id);

    Page<LogsVO> pageBiz(LogsQueryReq queryReq);

    Page<Logs> page(LogsQueryReq queryReq);

    List<Logs> listByQueryReq(LogsQueryReq queryReq);

    Map<Long, Logs> getIdEntityMap(List<Long> ids);

    Logs getOneByQueryReq(LogsQueryReq queryReq);

}
