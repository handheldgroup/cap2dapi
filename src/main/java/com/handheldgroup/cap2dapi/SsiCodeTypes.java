package com.handheldgroup.cap2dapi;

import java.lang.reflect.Field;

/**
 * Created by sg on 14.09.2015.
 */
public class SsiCodeTypes {
    public static String getName(int type){
        Field[] fields = SsiCodeTypes.class.getDeclaredFields();
        for(Field field : fields){
            try {
                if(field.getInt(SsiCodeTypes.class) == type){
                    return field.getName();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int Bookland = 0x16;
    public static int Codabar = 0x02;
    public static int Code11 = 0x0C;
    public static int Code128 = 0x03;

    //TODO Add more CodeTypes
}
