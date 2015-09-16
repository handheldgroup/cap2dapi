package com.handheldgroup.cap2dapi;

import android.content.Context;
import android.content.Intent;

public class ScannerIntent extends Intent {
    public static final String INTENT_UPDATE_SETTINGS = "com.handheld.scannercap.UPDATE_SETTINGS";

    public static final String INTENT_SEND_RAW = "com.handheld.scannercap.SEND_RAW";

    public static final String INTENT_SCAN_START = "com.handheld.scannercap.SCAN_START";
    public static final String INTENT_SCAN_RESULT = "com.handheld.scannercap.SCAN_RESULT";

    /**
     * Contains the barcode data as a String
     */
    public static final String EXTRA_BARCODE_DATA = "com.handheld.scannercap.extra.DATA";
    public static final String EXTRA_DATA = "com.handheld.scannercap.extra.DATA";

    /**
     * Contains the barcode type as a Char<br>
     * Use {@link com.handheldgroup.cap2dapi.SsiCodeTypes#getName(int) SsiPacket.CodeTypes.getName} method to get the name for this type.
     */
    public static final String EXTRA_BARCODE_TYPE = "com.handheld.scannercap.extra.TYPE";

    /**
     * The way the scanned code should be handled.<br>
     * Set {@link ScannerIntent.Builder.ResultType#KEYBOARD KEYBOARD} to simulate keypressed<br>
     * Set {@link ScannerIntent.Builder.ResultType#USER_EVENT USER_EVENT} to return a {@link ScannerIntent#INTENT_SCAN_RESULT INTENT_SCAN_RESULT} broadcast
     */
    public static final String EXTRA_BARCODE_EVENT = "com.handheld.scannercap.extra.EVENT";

    public static class Builder {

        public static class ResultType{
            public static final int KEYBOARD = 0;
            public static final int USER_EVENT = 1;
        }

        private int returnMethod = ResultType.KEYBOARD;
        private SsiPacket packet;
        private String action = INTENT_SCAN_START;

        public Builder() {
        }

        /**
         * {@link ScannerIntent#EXTRA_BARCODE_EVENT}
         */
        public Builder setReturnMethod(int value) {
            returnMethod = value;
            return this;
        }

        public Builder setPacket(SsiPacket packet) {
            action = INTENT_SEND_RAW;
            this.packet = packet;
            return this;
        }

        public ScannerIntent build() {
            return new ScannerIntent(this);
        }
    }

    public void start(Context context){
        context.sendBroadcast(this);
    }


    private ScannerIntent(Builder builder) {
        super(builder.action);
        switch (builder.action){
            case INTENT_SCAN_START:
                putExtra(EXTRA_BARCODE_EVENT, builder.returnMethod);
                break;
            case INTENT_SEND_RAW:
                putExtra(EXTRA_DATA, builder.packet.getBytes());
                break;
        }
    }

}
