package com.guns21.example.dao.spring.boot;

import com.guns21.example.dao.spring.boot.entity.OrderDO;
import com.guns21.example.dao.spring.boot.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @see https://www.ibm.com/developerworks/cn/java/j-master-spring-transactional-use/index.html?ca=drs-
 * @author jliu
 * @date 2019-10-29
 */
@Service
public class TransactionalService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 在 Spring 的 AOP 代理下，只有目标方法由外部调用，目标方法才由 Spring 生成的代理对象来管理，这会造成自调用问题。
     * 若同一类中的其他没有@Transactional 注解的方法内部调用有@Transactional 注解的方法，
     * 有@Transactional 注解的方法的事务被忽略，不会发生回滚
     * @param orderDO
     * @throws Exception
     */
    public void  save(OrderDO orderDO) throws Exception {
        this._save(orderDO) ;
    }

    @Transactional
    private void _save(OrderDO orderDO) throws Exception{
        orderMapper.addOrder(orderDO);
        throw new RuntimeException();
    }

    /**
     * 正确的设置@Transactional 的 rollbackFor 属性
     * 默认情况下，如果在事务中抛出了未检查异常（继承自 RuntimeException 的异常）或者 Error，则 Spring 将回滚事务；除此之外，Spring 不会回滚事务。
     *
     * 如果在事务中抛出其他类型的异常，并期望 Spring 能够回滚事务，可以指定 rollbackFor。例：
     *
     * @Transactional(propagation= Propagation.REQUIRED, rollbackFor= MyException.class)
     *
     * 通过分析 Spring 源码可以知道，若在目标方法中抛出的异常是 rollbackFor 指定的异常的子类，事务同样会回滚。
     *
     * @param orderDO
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class})
    public void exceptionSave(OrderDO orderDO) throws Exception{
        orderMapper.addOrder(orderDO);
        throw new Exception();
//        throw new RuntimeException();
    }

    public List<OrderDO> getOrders() {
        List<OrderDO> orders = orderMapper.getOrders();
        orderMapper.deleteAll();
        return orders;
    }


}
