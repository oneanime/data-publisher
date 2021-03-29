package com.hp.dw.publisher.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.dw.publisher.service.OrderServiceImp;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {
    @Resource
    OrderServiceImp orderServiceImp;
    @Resource
    ObjectMapper objectMapper;

//    http://localhost:8070/realtime_total?date=2019-05-16

    @GetMapping("/realtime_total")
    public String realtimeTotal(@RequestParam("date") String date) {
        ArrayList<Map<String, String>> list = new ArrayList<>();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "dau");
        hashMap.put("name", "新增日活");
        Long dau = orderServiceImp.getDau(date);
        hashMap.put("value", dau.toString());
        list.add(hashMap);


        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("id", "order_amount");
        hashMap1.put("name", "新增交易额");
        Double totalAmount = orderServiceImp.getTotalAmount(date);
        hashMap1.put("value", String.valueOf(totalAmount));
        list.add(hashMap1);

        String result = null;
        try {
            result = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    @GetMapping("/realtime_dau_hour")
    public String realtimeDuaHour(@RequestParam("date") String date) {
        String result = null;
        try {
            Date now = DateUtils.parseDate(date, "yyyy-MM-dd");
            String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.addDays(now, -1));

            Map<String, String> dauHour = orderServiceImp.getDauHour(date);
            Map<String, String> yesterdayDauHour = orderServiceImp.getDauHour(yesterday);
            HashMap<String, Map<String,String>> resultMap = new HashMap<>();
            resultMap.put("today", dauHour);
            resultMap.put("yesterday", yesterdayDauHour);

            result= objectMapper.writeValueAsString(resultMap);
        } catch (ParseException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
