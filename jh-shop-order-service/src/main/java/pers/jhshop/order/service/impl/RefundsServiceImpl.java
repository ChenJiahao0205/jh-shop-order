package pers.jhshop.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.order.model.req.RefundsCreateReq;
import pers.jhshop.order.model.req.RefundsQueryReq;
import pers.jhshop.order.model.req.RefundsUpdateReq;
import pers.jhshop.order.model.vo.RefundsVO;
import pers.jhshop.order.model.entity.Refunds;
import pers.jhshop.order.mapper.RefundsMapper;
import pers.jhshop.order.service.IRefundsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.jhshop.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

/**
 * <p>
 * 订单退款表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RefundsServiceImpl extends ServiceImpl<RefundsMapper, Refunds> implements IRefundsService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(RefundsCreateReq createReq) {


        Refunds entity = new Refunds();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(RefundsUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        Refunds entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("订单退款表不存在");
        }

        // 2.重复性判断
        Refunds entityToUpdate = new Refunds();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public RefundsVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        Refunds entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("订单退款表不存在");
        }

        RefundsVO vo = new RefundsVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<RefundsVO> pageBiz(RefundsQueryReq queryReq) {
        Page<Refunds> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<Refunds> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<RefundsVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<Refunds> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<RefundsVO> vos = records.stream().map(record -> {
            RefundsVO vo = new RefundsVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<Refunds> page(RefundsQueryReq queryReq) {
        Page<Refunds> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<Refunds> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<Refunds> listByQueryReq(RefundsQueryReq queryReq) {
        LambdaQueryWrapper<Refunds> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<Refunds> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, Refunds> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<Refunds> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Refunds::getId, ids);
        List<Refunds> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(Refunds::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public Refunds getOneByQueryReq(RefundsQueryReq queryReq) {
        LambdaQueryWrapper<Refunds> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<Refunds> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<Refunds> getLambdaQueryWrapper(RefundsQueryReq queryReq) {
        LambdaQueryWrapper<Refunds> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), Refunds::getId, queryReq.getId());
        queryWrapper.eq(Objects.nonNull(queryReq.getOrderId()), Refunds::getOrderId, queryReq.getOrderId());
        queryWrapper.eq(Objects.nonNull(queryReq.getRefundAmount()), Refunds::getRefundAmount, queryReq.getRefundAmount());
        queryWrapper.eq(Objects.nonNull(queryReq.getRefundStatus()), Refunds::getRefundStatus, queryReq.getRefundStatus());
        queryWrapper.eq(Objects.nonNull(queryReq.getRefundTime()), Refunds::getRefundTime, queryReq.getRefundTime());
        queryWrapper.eq(Objects.nonNull(queryReq.getCreatedAt()), Refunds::getCreatedAt, queryReq.getCreatedAt());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), Refunds::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), Refunds::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), Refunds::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
