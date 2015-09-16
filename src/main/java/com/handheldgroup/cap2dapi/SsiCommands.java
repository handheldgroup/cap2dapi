package com.handheldgroup.cap2dapi;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class SsiCommands {
    public static String getName(char type){
        Field[] fields = SsiCommands.class.getDeclaredFields();
        for(Field field : fields){
            try {
                if(field.getChar(SsiCommands.class) == type){
                    return field.getName();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static final char ABORT_MACRO_PDF = 0x11;
    public static final char AIM_OFF = 0xC4;
    public static final char AIM_ON = 0xC5;
    public static final char BATCH_DATA = 0xD6;
    public static final char BATCH_REQUEST = 0xD5;
    public static final char BEEP = 0xE6;
    public static final char CAPABILITIES_REPLY = 0xD4;
    public static final char CAPABILITIES_REQUEST = 0xD3;
    public static final char CMD_ACK = 0xD0;
    public static final char CMD_NAK = 0xD1;
    public static final char DECODE_DATA = 0xF3;
    public static final char EVENT = 0xF6;
    public static final char FLUSH_MACRO_PDF = 0x10;
    public static final char FLUSH_QUEUE = 0xD2;
    public static final char IMAGE_DATA = 0xB1;
    public static final char IMAGER_MODE = 0xF7;
    public static final char LED_OFF = 0xE8;
    public static final char LED_ON = 0xE7;
    public static final char PARAM_DEFAULTS = 0xC8;
    public static final char PARAM_REQUEST = 0xC7;
    public static final char PARAM_SEND = 0xC6;
    public static final char REPLY_REVISION = 0xA4;
    public static final char REQUEST_REVISION = 0xA3;
    public static final char SCAN_DISABLE = 0xEA;
    public static final char SCAN_ENABLE = 0xE9;
    public static final char SLEEP = 0xEB;
    public static final char START_SESSION = 0xE4;
    public static final char STOP_SESSION = 0xE5;
    public static final char VIDEO_DATA = 0xB4;
    public static final char WAKEUP = 0x00;
}
