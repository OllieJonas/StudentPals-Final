package com.igpgroup17.studentpals.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ListUtils {

    public <T> List<T> copyOf(List<T> list) {
        return new ArrayList<>(list);
    }
}
