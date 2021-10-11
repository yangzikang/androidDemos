package com.yzk.baselib.tools;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    public static <T> List<T> toList(T[] array) {
        List<T> list = new ArrayList<>();
        if (array == null) {
            return list;
        }
        for (T bean : array) {
            list.add(bean);
        }
        return list;
    }

}
