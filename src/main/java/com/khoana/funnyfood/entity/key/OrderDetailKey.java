package com.khoana.funnyfood.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name = "orderid")
    private Integer orderId;

    @Column(name = "foodid")
    private Integer foodId;

    public OrderDetailKey() {
    }

    public OrderDetailKey(Integer orderId, Integer foodId) {
        this.orderId = orderId;
        this.foodId = foodId;
    }
}
