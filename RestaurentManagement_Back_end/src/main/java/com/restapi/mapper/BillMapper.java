package com.restapi.mapper;

import com.restapi.dto.BillDTO;
import com.restapi.models.Bill;
import com.restapi.models.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillMapper {

    public static BillDTO billToBillDTOMapper(Bill bill){
        Map<String,Integer> menus = new HashMap<>();
        for (Map.Entry<Menu,Integer> entry: bill.getMenus().entrySet()) {
            menus.put(entry.getKey().getName(), entry.getValue());
        }
        return new BillDTO(
                bill.getId(),
                menus,
                bill.getTotalPrice(),
                bill.getPaymentStatus(),
                bill.getCreateDate());
    }
    public static Bill billDTOToBillMapper(BillDTO billDTO){
        Bill bill = new Bill();
        bill.setTotalPrice(billDTO.getTotalPrice());
        return bill;
    }
    public static List<BillDTO> billsToBillDTOMapper(List<Bill> bills){
        List<BillDTO> billDTOs = new ArrayList<>();
        for (Bill bill: bills) {
            billDTOs.add(billToBillDTOMapper(bill));
        }
        return billDTOs;
    }
}
