package com.khoana.funnyfood.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class MenuRestaurantKey implements Serializable {
    @Column(name = "cateid")
    private Integer cateId;

    @Column(name = "resid")
    private Integer resId;
}
