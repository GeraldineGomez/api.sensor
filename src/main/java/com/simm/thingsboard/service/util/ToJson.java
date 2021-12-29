package com.simm.thingsboard.service.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ToJson {

    public static void printToJson(Object value) {
        ObjectMapper Obj = new ObjectMapper();

        try {
            String jsonStr = Obj.writeValueAsString(value);
            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
