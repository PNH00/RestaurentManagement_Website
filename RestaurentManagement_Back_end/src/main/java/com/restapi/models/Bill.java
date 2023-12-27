package com.restapi.models;

import com.restapi.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "bills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ElementCollection
    @CollectionTable(
            name = "bill_menu_quantities",
            joinColumns = @JoinColumn(name = "bill_id")
    )
    @MapKeyJoinColumn(name = "menu_id")
    @Column(name = "quantity")
    private Map<Menu, Integer> menus;
    @Column(name = "total_price", nullable = false, columnDefinition = "float")
    private double totalPrice;
    @Column(nullable = false, columnDefinition = "nvarchar(30)")
    private PaymentStatus paymentStatus;
    @Column(nullable = false,name = "create_date")
    private Date createDate;
}