package com.tma.training.restaurant.commons.utils;

public class DataConvertUtil {
    public static Object convertToFieldType(String value, Class<?> targetType) {
        if (targetType == String.class) {
            return value;
        }
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.valueOf(value);
        }
        if (targetType == double.class || targetType == Double.class) {
            return Double.valueOf(value);
        }
        if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.valueOf(value);
        }
        if (targetType == long.class || targetType == Long.class) {
            return Long.valueOf(value);
        }
        if (targetType == float.class || targetType == Float.class) {
            return Float.valueOf(value);
        }
        if (targetType == short.class || targetType == Short.class) {
            return Short.valueOf(value);
        }
        if (targetType == byte.class || targetType == Byte.class) {
            return Byte.valueOf(value);
        }
        if (targetType == char.class || targetType == Character.class) {
            if (value.length() != 1) {
                throw new IllegalArgumentException("Invalid value for char type: " + value);
            }
            return value.charAt(0);
        }
        throw new IllegalArgumentException("Unsupported data type: " + targetType);
    }
}
