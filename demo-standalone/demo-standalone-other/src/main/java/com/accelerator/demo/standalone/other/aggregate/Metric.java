package com.accelerator.demo.standalone.other.aggregate;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.Serializable;
import java.util.Arrays;

public class Metric implements Serializable {
    private static final long serialVersionUID = -7824717738246876865L;
    private static final ThreadLocal<char[]> tbuffer = ThreadLocal.withInitial(() -> new char[4096]);
    private String[] prefixes;
    private String time;
    private String[] suffixes;
    private transient int hash;

    private Metric(String[] prefixes, String time, String[] suffixes) {
        this.prefixes = prefixes;
        this.time = time;
        this.suffixes = suffixes;
    }

    public static final Metric of(String[] prefixes, String time, String[] suffixes) {
        return new Metric(prefixes, time, suffixes);
    }

    public static final Metric of(String[] prefixes, long time, String[] suffixes) {
        return new Metric(prefixes, Long.toString(time, 10), suffixes);
    }

    private int getLength() {
        int length = 0;
        String[] var2 = this.prefixes;
        int var3 = var2.length;

        int var4;
        String suffix;
        for (var4 = 0; var4 < var3; ++var4) {
            suffix = var2[var4];
            length += suffix.length() + 1;
        }

        var2 = this.suffixes;
        var3 = var2.length;

        for (var4 = 0; var4 < var3; ++var4) {
            suffix = var2[var4];
            length += suffix.length() + 1;
        }

        return length;
    }

    public byte[] getRowkey() {
        char[] buffer = tbuffer.get();
        int length = this.getLength();
        if (length > 4000) {
            buffer = new char[length + 100];
        }

        int offset = 0;
        offset = this.appendTo(this.prefixes, buffer, offset);
        buffer[offset++] = '|';
        offset = this.appendTo(this.time, buffer, offset);
        int offset2 = offset;
        buffer[offset++] = '|';
        offset = this.appendTo(this.suffixes, buffer, offset);
        byte[] hash = Base64.encodeBase64(Bytes.toBytes(this.hashCodeOf(buffer, 0, offset2)));
        byte[] prefixBytes = new byte[]{hash[0], hash[1], hash[2], hash[3], hash[4], hash[5], hash[6], hash[7], 126};
        byte[] data = Bytes.toBytes(String.valueOf(buffer, 0, offset));
        byte[] bytes = new byte[prefixBytes.length + data.length];
        System.arraycopy(prefixBytes, 0, bytes, 0, prefixBytes.length);
        System.arraycopy(data, 0, bytes, prefixBytes.length, data.length);
        return bytes;
    }

    protected final int appendTo(String[] array, char[] buffer, int start) {
        if (!ArrayUtils.isEmpty(array)) {
            start = this.appendTo(array[0], buffer, start);

            for (int i = 1; i < array.length; ++i) {
                buffer[start++] = '|';
                start = this.appendTo(array[i], buffer, start);
            }
        }

        return start;
    }

    protected final int appendTo(String str, char[] buffer, int start) {
        if (!StringUtils.isEmpty(str)) {
            int length = str.length();
            str.getChars(0, length, buffer, start);
            return start + length;
        } else {
            return start;
        }
    }

    protected final int hashCodeOf(char[] buffer, int start, int end) {
        int len = end - start;
        int h = 0;

        for (int i = 0; i < len; ++i) {
            h = 31 * h + buffer[start++];
        }

        return h;
    }

    public String[] getPrefixes() {
        return this.prefixes;
    }

    public String getTime() {
        return this.time;
    }

    public String[] getSuffixes() {
        return this.suffixes;
    }

    public String toString() {
        StringBuilder appender = new StringBuilder(128);
        appender.append("Metric [").append(Bytes.toString(this.getRowkey())).append(']');
        return appender.toString();
    }

    public int hashCode() {
        if (this.hash == 0) {
            int result = 1;
            result = 31 * result + Arrays.hashCode(this.prefixes);
            result = 31 * result + Arrays.hashCode(this.suffixes);
            result = 31 * result + (this.time == null ? 0 : this.time.hashCode());
            this.hash = result;
        }

        return this.hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Metric other = (Metric) obj;
            if (this.hash != 0 && other.hash != 0 && this.hash != other.hash) {
                return false;
            } else if (!Arrays.equals(this.prefixes, other.prefixes)) {
                return false;
            } else if (!Arrays.equals(this.suffixes, other.suffixes)) {
                return false;
            } else {
                if (this.time == null) {
                    if (other.time != null) {
                        return false;
                    }
                } else if (!this.time.equals(other.time)) {
                    return false;
                }
                return true;
            }
        }
    }
}