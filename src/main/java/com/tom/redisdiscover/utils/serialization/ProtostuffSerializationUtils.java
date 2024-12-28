package com.tom.redisdiscover.utils.serialization;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName ProtostuffSerializationUtils.java
 * @Description TODO
 * @createTime 2024年12月26日 07:30:00
 */
public class ProtostuffSerializationUtils {

    // 缓冲区
    private static LinkedBuffer BUFFER = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    //Schema缓存
    private static final Map<String, Schema<?>> SCHEMA_CACHE = new ConcurrentHashMap<>();

    public static <T> byte[] serialize(T object) {
        Class<T> clazz = (Class<T>) object.getClass();
        try {
            return ProtostuffIOUtil.toByteArray(object, getSchemaInstance(clazz), BUFFER);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            BUFFER.clear();
        }
    }

    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        Schema<T> schemaInstance = getSchemaInstance(clazz);
        T object = schemaInstance.newMessage();
        ProtostuffIOUtil.mergeFrom(data, object, schemaInstance);
        return object;
    }

    private static <T> Schema<T> getSchemaInstance(Class<T> clazz) {
        return (Schema<T>) SCHEMA_CACHE.computeIfAbsent(clazz.getName(), x -> RuntimeSchema.getSchema(clazz));
    }

    private ProtostuffSerializationUtils() {
        // do nothing.
    }
}
