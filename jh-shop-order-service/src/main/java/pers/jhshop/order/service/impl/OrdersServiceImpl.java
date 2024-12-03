package pers.jhshop.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.order.model.req.OrdersCreateReq;
import pers.jhshop.order.model.req.OrdersQueryReq;
import pers.jhshop.order.model.req.OrdersUpdateReq;
import pers.jhshop.order.model.vo.OrdersVO;
import pers.jhshop.order.model.entity.Orders;
import pers.jhshop.order.mapper.OrdersMapper;
import pers.jhshop.order.service.IOrdersService;
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
 * 订单表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(OrdersCreateReq createReq) {


        Orders entity = new Orders();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(OrdersUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        Orders entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("订单表不存在");
        }

        // 2.重复性判断
        Orders entityToUpdate = new Orders();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public OrdersVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        Orders entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("订单表不存在");
        }

        OrdersVO vo = new OrdersVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<OrdersVO> pageBiz(OrdersQueryReq queryReq) {
        Page<Orders> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<Orders> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<OrdersVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<Orders> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<OrdersVO> vos = records.stream().map(record -> {
            OrdersVO vo = new OrdersVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<Orders> page(OrdersQueryReq queryReq) {
        Page<Orders> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<Orders> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<Orders> listByQueryReq(OrdersQueryReq queryReq) {
        LambdaQueryWrapper<Orders> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<Orders> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, Orders> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<Orders> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Orders::getId, ids);
        List<Orders> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(Orders::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public Orders getOneByQueryReq(OrdersQueryReq queryReq) {
        LambdaQueryWrapper<Orders> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<Orders> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<Orders> getLambdaQueryWrapper(OrdersQueryReq queryReq) {
        LambdaQueryWrapper<Orders> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), Orders::getId, queryReq.getId());
        queryWrapper.eq(Objects.nonNull(queryReq.getUserId()), Orders::getUserId, queryReq.getUserId());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getOrderNo()), Orders::getOrderNo, queryReq.getOrderNo());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getOrderNoLike()), Orders::getOrderNo, queryReq.getOrderNoLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getTotalAmount()), Orders::getTotalAmount, queryReq.getTotalAmount());
        queryWrapper.eq(Objects.nonNull(queryReq.getStatus()), Orders::getStatus, queryReq.getStatus());
        queryWrapper.eq(Objects.nonNull(queryReq.getPaymentStatus()), Orders::getPaymentStatus, queryReq.getPaymentStatus());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getShippingAddress()), Orders::getShippingAddress, queryReq.getShippingAddress());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getShippingAddressLike()), Orders::getShippingAddress, queryReq.getShippingAddressLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getCreatedAt()), Orders::getCreatedAt, queryReq.getCreatedAt());
        queryWrapper.eq(Objects.nonNull(queryReq.getUpdatedAt()), Orders::getUpdatedAt, queryReq.getUpdatedAt());
        queryWrapper.eq(Objects.nonNull(queryReq.getPaymentTime()), Orders::getPaymentTime, queryReq.getPaymentTime());
        queryWrapper.eq(Objects.nonNull(queryReq.getShippingTime()), Orders::getShippingTime, queryReq.getShippingTime());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), Orders::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), Orders::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), Orders::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
