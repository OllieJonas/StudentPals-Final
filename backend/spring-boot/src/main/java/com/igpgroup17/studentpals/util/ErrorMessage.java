package com.igpgroup17.studentpals.util;


import lombok.AllArgsConstructor;
import lombok.Getter;

// dirty hack to ensure everything we return is json
@Getter
@AllArgsConstructor(staticName = "of")
public class ErrorMessage {

    private final String message;
}
