package com.example.testanytehnology.strim.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Order {

    private Integer id;
    private String invoice;
    private List<LineItem> lineItems;
    private BigDecimal total;

    public Order(Integer id, String invoice, List<LineItem> lineItems, BigDecimal total) {
        this.id = id;
        this.invoice = invoice;
        this.lineItems = lineItems;
        this.total = total;
    }
}
