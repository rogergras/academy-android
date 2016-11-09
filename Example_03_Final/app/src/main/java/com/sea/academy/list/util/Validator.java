package com.sea.academy.list.util;


public final class Validator {

    private Validator() {

    }

    /**
     * Retrieves the {@link IllegalArgumentException} exception.
     * @param obj The object.
     */
    public static void checkNull(Object obj) {
        if(obj == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Retrieves the {@link IllegalArgumentException} exception.
     * @param obj The string object.
     */
    public static void checkNullEmpty(String obj) {
        if(obj == null || obj.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Retrieves the {@link IllegalArgumentException} exception with the message: "{name} can't be null".
     * @param obj The object.
     * @param name Object name.
     */
    public static void checkNull(Object obj, String name) {
        if(obj == null) {
            throw new IllegalArgumentException(String.format("'%s' can't be null.", name));
        }
    }

}