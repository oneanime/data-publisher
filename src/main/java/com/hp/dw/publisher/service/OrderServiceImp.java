package com.hp.dw.publisher.service;

import com.hp.dw.publisher.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImp implements OrderService{

    @Resource
    private OrderMapper mapper;

    @Override
    public Long getDau(String date) {
        return mapper.getDau(date);
    }

    @Override
    public Map<String, String> getDauHour(String date) {
        List<Map<String, String>> dauHour = mapper.getDauHour(date);
        HashMap<String, String> result = new HashMap<>();
        for (Map<String, String> dau : dauHour) {
            String create_hour = dau.get("CREATE_HOUR").toString();
            String count = String.valueOf(dau.get("COUNT(1)"));
            result.put(create_hour, count);
        }
        return result;
    }

    @Override
    public Double getTotalAmount(String date) {
        return mapper.getTotalAmount(date);
    }
}
