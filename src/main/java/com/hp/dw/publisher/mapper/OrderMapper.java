package com.hp.dw.publisher.mapper;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    Long getDau(String date);

    List<Map<String, String>> getDauHour(String date);

    Double getTotalAmount(String date);
}
