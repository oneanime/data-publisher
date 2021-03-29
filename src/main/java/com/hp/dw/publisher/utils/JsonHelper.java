package com.hp.dw.publisher.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonHelper {
    public static final ObjectMapper JsonMapper = new ObjectMapper();
    /**
     * String类型的javaType
     */
    public static final JavaType StringJavaType = JsonMapper.getTypeFactory().constructType(
            String.class);

    /**
     * 支持Map<String,String>
     */
    public static final JavaType StringStringMap = JsonMapper.getTypeFactory().constructMapType(HashMap.class,
            String.class, String.class);

    /**
     * 支持Map<String,Map<String,String>>
     */
    public static final JavaType StringStringStringMap = JsonMapper.getTypeFactory()
            .constructMapType(HashMap.class, StringJavaType, StringStringMap);

    /**
     * 支持Map<String,Map<String,Map<String,String>>>
     */
    public static final JavaType StringStringStringStringMap = JsonMapper.getTypeFactory()
            .constructMapType(HashMap.class, StringJavaType, StringStringStringMap);

    /**
     * 支持List<Object>
     */
    public static final JavaType ListObject = JsonMapper.getTypeFactory().constructCollectionType(ArrayList.class, Object.class);

    /**
     * 支持List<List<Object>>
     */
    public static final JavaType ListListObject = JsonMapper.getTypeFactory().constructCollectionType(ArrayList.class, ListObject);

}