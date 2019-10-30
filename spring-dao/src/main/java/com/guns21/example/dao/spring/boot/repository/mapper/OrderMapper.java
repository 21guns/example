package com.guns21.example.dao.spring.boot.repository.mapper;

import com.guns21.example.dao.spring.boot.entity.OrderDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author jliu
 * @date 2019-09-18
 */
@Mapper
public interface OrderMapper {

    @Select("select * from `order`")
    List<OrderDO> getOrders();


    @Insert("INSERT INTO `order`(id,no,amount) VALUES(#{id},"
            + "#{no},#{amount})")
    void addOrder(OrderDO orderDO);

    @Delete("TRUNCATE `order`")
    void deleteAll();
}
