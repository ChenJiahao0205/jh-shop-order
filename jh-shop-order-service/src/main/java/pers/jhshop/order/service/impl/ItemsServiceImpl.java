package pers.jhshop.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.order.model.req.ItemsCreateReq;
import pers.jhshop.order.model.req.ItemsQueryReq;
import pers.jhshop.order.model.req.ItemsUpdateReq;
import pers.jhshop.order.model.vo.ItemsVO;
import pers.jhshop.order.model.entity.Items;
import pers.jhshop.order.mapper.ItemsMapper;
import pers.jhshop.order.service.IItemsService;
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
 * 订单商品表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements IItemsService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(ItemsCreateReq createReq) {


        Items entity = new Items();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(ItemsUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        Items entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("订单商品表不存在");
        }

        // 2.重复性判断
        Items entityToUpdate = new Items();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public ItemsVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        Items entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("订单商品表不存在");
        }

        ItemsVO vo = new ItemsVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<ItemsVO> pageBiz(ItemsQueryReq queryReq) {
        Page<Items> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<Items> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<ItemsVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<Items> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<ItemsVO> vos = records.stream().map(record -> {
            ItemsVO vo = new ItemsVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<Items> page(ItemsQueryReq queryReq) {
        Page<Items> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<Items> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<Items> listByQueryReq(ItemsQueryReq queryReq) {
        LambdaQueryWrapper<Items> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<Items> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, Items> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<Items> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Items::getId, ids);
        List<Items> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(Items::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public Items getOneByQueryReq(ItemsQueryReq queryReq) {
        LambdaQueryWrapper<Items> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<Items> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<Items> getLambdaQueryWrapper(ItemsQueryReq queryReq) {
        LambdaQueryWrapper<Items> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), Items::getId, queryReq.getId());
        queryWrapper.eq(Objects.nonNull(queryReq.getOrderId()), Items::getOrderId, queryReq.getOrderId());
        queryWrapper.eq(Objects.nonNull(queryReq.getProductId()), Items::getProductId, queryReq.getProductId());
        queryWrapper.eq(Objects.nonNull(queryReq.getQuantity()), Items::getQuantity, queryReq.getQuantity());
        queryWrapper.eq(Objects.nonNull(queryReq.getPrice()), Items::getPrice, queryReq.getPrice());
        queryWrapper.eq(Objects.nonNull(queryReq.getTotalPrice()), Items::getTotalPrice, queryReq.getTotalPrice());
        queryWrapper.eq(Objects.nonNull(queryReq.getCreatedAt()), Items::getCreatedAt, queryReq.getCreatedAt());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), Items::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), Items::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), Items::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
