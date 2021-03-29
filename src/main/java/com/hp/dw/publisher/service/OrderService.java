package com.hp.dw.publisher.service;

import java.util.Map;

public interface OrderService {
    Long getDau(String date);
    Map<String, String> getDauHour(String date);

    Double getTotalAmount(String date);
}
