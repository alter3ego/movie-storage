package com.movie.storage.entity;

public enum Type {
    FULL,
    SHORT,
    SERIAL;

    public static boolean enumExist(String type) {
        try {
            Type.valueOf(type);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
