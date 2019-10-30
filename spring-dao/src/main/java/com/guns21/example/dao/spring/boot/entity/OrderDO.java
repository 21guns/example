package com.guns21.example.dao.spring.boot.entity;

import com.guns21.support.entity.LongIDEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 订单
 */
//@Builder(toBuilder = true)
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode(callSuper = true)
//@Setter
//@Getter
public class OrderDO extends LongIDEntity {

	/**
 	 * 编号
 	 * 
	 */
	private String no;

	/**
 	 * 类型
 	 * 1：商品
	 */
	private Byte type;

	/**
 	 * 用户id
 	 * 
	 */
	private String userId;

	/**
 	 * 金额
 	 * 
	 */
	private BigDecimal amount;

	/**
 	 * 商铺id
 	 * 
	 */
	private String shopId;

	/**
 	 * 过期时间
 	 * 
	 */
	private LocalDateTime expiryTime;

	/**
 	 * 推广令id
 	 * 
	 */
	private String promotionTokenId;

	/**
	 * 支付标记: 1：待支付、2：已支付
	 */
	private Byte paymentMark;

	/**
 	 * 状态
 	 * 1：已下单、2：待支付、3：已支付、4：完成、5：已取消
	 */
	private Byte status;

	/**
 	 * 备注
 	 * 
	 */
	private String note;

	/**
	 * 父订单id
	 */
	private Long parentOrderId;


    @Override
    public void preCreate() {
        super.preCreate();
		if (Objects.isNull(getAmount())) {
			this.setAmount(BigDecimal.ZERO);
		}
    }

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}