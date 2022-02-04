package com.example.testanytehnology.strim.model;

import lombok.*;

import java.math.BigDecimal;
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class LineItem {

    private Integer id;
    private String item;
    private Integer qty;
    private BigDecimal price;
    private BigDecimal total;

    public LineItem(Integer id, String item, Integer qty, BigDecimal price, BigDecimal total) {
        this.id = id;
        this.item = item;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }
}
