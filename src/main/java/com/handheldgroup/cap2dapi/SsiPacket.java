package com.handheldgroup.cap2dapi;

import java.util.Arrays;
/*
Packet format:
 [0]    Length without the 2 checksum bytes
 [1]    OP Code
 [2]    Source
 [3]    Status
 [4..]  Data (n Bytes)
 [..]   Checksum (2 Bytes)
 */
public class SsiPacket {
    public char opcode;
    public char source;
    public SsiStatus status;
    public char[] data;

    public static SsiPacket parse(CharSequence raw) {
        SsiPacket packet = new SsiPacket();
        packet.opcode = raw.charAt(1);
        packet.source = raw.charAt(2);
        packet.status = new SsiStatus();
        packet.data = new char[raw.charAt(0) - 4];
        for(int i = 0; i < packet.data.length; i++){
            packet.data[i] = raw.charAt(i + 4);
        }
        return packet;
    }

    public SsiPacket() {
        status = new SsiStatus();
    }

    public SsiPacket(char opcode, char source) {
        status = new SsiStatus();
        this.opcode = opcode;
        this.source = source;
        this.data = new char[0];
    }

    @SuppressWarnings("unused")
    public SsiPacket(char opcode, char source, char[] data) {
        status = new SsiStatus();
        this.opcode = opcode;
        this.source = source;
        this.data = data;
    }

    public byte[] getBytes() {
        int size = 6 + data.length;
        char[] message = new char[size];
        message[0] = (char) (4 + data.length); // Length of the message w/o Checksum
        message[1] = opcode;
        message[2] = source;
        message[3] = status.toByte();
        System.arraycopy(data, 0, message, 4, data.length);
        System.arraycopy(checksum(message), 0, message, 4 + data.length, 2);

        byte[] buffer = new byte[message.length];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) message[i];
        }
        return buffer;
    }

    @Override
    public String toString() {
        return "SsiPacket{" +
                "opcode=" + asHex(opcode) +
                ", source=" + asHex(source) +
                ", status=" + asHex(status.toByte()) +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    private char[] checksum(char[] message) {
        long sum = 0;
        for (char outputByte : message) {
            sum += outputByte;
        }
        long value = (~((sum & 0xFFFF) + (sum >> 16))) & 0xFFFF;
        return new char[]{(char) (value >> 8), (char) (value + 1 & 0xFF)};
    }


    public static class SsiStatus {
        public boolean retransmit;
        public boolean intermediate;
        public boolean permanent;

        public char toByte() {
            byte b = 0;
            if (retransmit) b |= 1;
            if (intermediate) b |= 1 << 1;
            if (permanent) b |= 1 << 3;
            return (char) (b & 0xFF);
        }
    }

    @SuppressWarnings("unused")
    public static class Source{
        public static char DECODER = 0x00;
        public static char HOST = 0x04;
    }

    @SuppressWarnings("unused")
    public static class Commands{

    }

    @SuppressWarnings("unused")
    public static SsiPacket ACK = new SsiPacket(SsiCommands.CMD_ACK, Source.HOST);

    @SuppressWarnings("unused")
    public static SsiPacket NAK = new SsiPacket(SsiCommands.CMD_NAK, Source.HOST);

    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    private static String asHex(char buf)
    {
        char[] chars = new char[2];
        chars[0] = HEX_CHARS[(buf & 0xF0) >>> 4];
        chars[1] = HEX_CHARS[buf & 0x0F];
        return new String(chars);
    }
}
