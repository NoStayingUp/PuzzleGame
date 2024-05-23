package com.jk.tool;

import java.util.ArrayList;
import java.util.Random;

public class CodeUtil {
    public static String getCode(){

        //用集合，添加元素更加方便
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i) + "");
            list.add((char)('A' + i) + "");
        }
        for (int i = 0; i < 10; i++) {
            list.add((i + ""));
        }
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = r.nextInt(list.size());
            sb.append(list.get(index));
        }
        return sb.toString();
    }
}
