package com.khoana.funnyfood.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.khoana.funnyfood.entity.key.OrderDetailKey;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "orderdetail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderid")
    private Order order;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "foodid")
    private Food food;

    @Column(name = "createdate")
    private LocalDateTime createDate;

    @Column(name = "price")
    private BigDecimal price;
}
