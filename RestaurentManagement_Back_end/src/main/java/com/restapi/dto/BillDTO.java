package com.restapi.dto;

import com.restapi.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private UUID id;
    private Map<String,Integer> menus;
    private double totalPrice;
    private PaymentStatus paymentStatus;
    private Date createDate;
}