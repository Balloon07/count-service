package com.balloon.common.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 王思远
 * @date 2023-12-17 02:30
 */
public class JsonUtil {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        // 在ObjectMapper对象中配置JavaTimeModule，此为全局配置
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(Date.class, new DateJsonSerializer());
        javaTimeModule.addDeserializer(Date.class, new DateJsonDeserializer());
        objectMapper.registerModule(javaTimeModule);
    }

    /**
     * Date序列化处理
     */
    public static class DateJsonSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null) {
                gen.writeNull();
                return;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
            gen.writeString(dateFormat.format(value));
        }
    }

    /**
     * Date反序列化处理
     */
    public static class DateJsonDeserializer extends JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            String date = p.getText();
            if (StringUtils.isBlank(date)) {
                return null;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
            try {
                return simpleDateFormat.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException("日期解析失败，date=" + date);
            }
        }
    }

    private JsonUtil() {
    }

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

}
