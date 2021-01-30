package br.edu.ifsp.domain.usecases.utils;

import java.util.Collection;

public abstract class Validator<T> {
    public abstract Notification validate(T type);

    public static boolean nullOrEmptyOrBlank(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }

    public static boolean nullOrEmptyOrBlank(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
