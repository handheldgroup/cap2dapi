package com.handheldgroup.cap2dapi;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
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

    // From SSI Programmer’s Guide - 2008.pdf, Table A-8, Page 363ff
    public static int Code39 = 0x01;
    public static int Codabar = 0x02;
    public static int Code128 = 0x03;
    public static int D25 = 0x04;
    public static int IATA = 0x05;
    public static int ITF = 0x06;
    public static int Code93 = 0x07;
    public static int UPCA = 0x08;
    public static int UPCE = 0x09;
    public static int EAN8 = 0x0A;
    public static int EAN13 = 0x0B;
    public static int Code11 = 0x0C;
    public static int MSI = 0x0E;
    public static int EAN128 = 0x0F;
    public static int UPCE1 = 0x10;
    public static int PDF417 = 0x11;
    public static int Code39ASCII = 0x13;
    public static int Trioptic = 0x15;
    public static int Bookland = 0x16;
    public static int CouponCode = 0x17;
    public static int ISBT128 = 0x19;
    public static int MicroPDF = 0x1A;
    public static int DataMatrix = 0x1B;
    public static int QRCode = 0x1C;
    public static int PostnetUS = 0x1E;
    public static int PlanetUS = 0x1F;
    public static int Code32 = 0x20;
    public static int ISBT128Concat = 0x21;
    public static int PostalJapan = 0x22;
    public static int PostalAustralia = 0x23;
    public static int PostalDutch = 0x24;
    public static int Maxicode = 0x25;
    public static int PostbarCA = 0x26;
    public static int PostalUK = 0x27;
    public static int MacroPDF417 = 0x28;
    public static int RSS14 = 0x30;
}
