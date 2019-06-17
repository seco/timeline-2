package com.wedul.wedul_timeline.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-17
 **/
public class ObjectHelper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectHelper getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final ObjectHelper INSTANCE = new ObjectHelper();
    }

    public  <T> T readValue(String data, Class<T> clazz) throws IOException {
        return objectMapper.readValue(data, clazz);
    }

    public <T> String writeValueAsString(T data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }

}
