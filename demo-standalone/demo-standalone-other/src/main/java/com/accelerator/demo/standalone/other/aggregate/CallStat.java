package com.accelerator.demo.standalone.other.aggregate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CallStat implements AggregatableRecord<CallStat>, TimestampSupport {
    private static final long serialVersionUID = -2763638750561198315L;
    private static final long[] EMPTY_ARRAY;
    private transient long timestamp;
    private long[] values;
    private String traceId;

    public CallStat() {
        this("", CallStat.EMPTY_ARRAY);
    }

    public CallStat(final long... values) {
        this.values = values;
    }

    public CallStat(final String traceId, final long... values) {
        this.traceId = traceId;
        this.values = values;
    }

    @Override
    public long getTimestamp() {
        return this.timestamp;
    }

    @Override
    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    public long get(final int pos) {
        return (pos >= this.values.length) ? 0L : this.values[pos];
    }

    public void set(final int pos, final long value) {
        if (pos < this.values.length) {
            this.values[pos] = value;
        }
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(final String traceId) {
        this.traceId = traceId;
    }

    public void reset() {
        for (int i = 0; i < this.values.length; ++i) {
            this.values[i] = 0L;
        }
    }

    public int length() {
        return this.values.length;
    }

    @Override
    public void aggregateFrom(final CallStat other) {
        final long[] values2 = other.values;
        final int len = values2.length;
        if (len != this.values.length) {
            if (this.values.length == 0) {
                this.values = Arrays.copyOf(values2, len);
                this.traceId = other.traceId;
            }
            return;
        }
        if (values2.length > 3 && other.values.length > 3) {
            if (this.values[2] > 0L && values2[2] > 0L) {
                if (StringUtils.isBlank(this.traceId)) {
                    this.traceId = other.traceId;
                }
            } else if (this.values[1] < values2[1]) {
                this.traceId = other.traceId;
            }
        }
        if (len <= 8) {
            switch (len) {
                case 8: {
                    final long[] values3 = this.values;
                    final int n = 7;
                    values3[n] += values2[7];
                }
                case 7: {
                    final long[] values4 = this.values;
                    final int n2 = 6;
                    values4[n2] += values2[6];
                }
                case 6: {
                    final long[] values5 = this.values;
                    final int n3 = 5;
                    values5[n3] += values2[5];
                }
                case 5: {
                    final long[] values6 = this.values;
                    final int n4 = 4;
                    values6[n4] += values2[4];
                }
                case 4: {
                    final long[] values7 = this.values;
                    final int n5 = 3;
                    values7[n5] += values2[3];
                }
                case 3: {
                    final long[] values8 = this.values;
                    final int n6 = 2;
                    values8[n6] += values2[2];
                }
                case 2: {
                    final long[] values9 = this.values;
                    final int n7 = 1;
                    values9[n7] += values2[1];
                }
                case 1: {
                    final long[] values10 = this.values;
                    final int n8 = 0;
                    values10[n8] += values2[0];
                    break;
                }
            }
        } else {
            for (int i = 0; i < len; ++i) {
                final long[] values11 = this.values;
                final int n9 = i;
                values11[n9] += values2[i];
            }
        }
    }

    @Override
    public byte[] toBytes() throws IOException {
        return (StringUtils.join(this.values, "|") + '|' + this.traceId).getBytes(UTF_8);
    }

    @Override
    public void fromBytes(final byte[] bytes) throws IOException {
        final String str = new String(bytes, UTF_8);
        final String[] splits = StringUtils.split(str, '|');
        final int len = splits.length - 1;
        final long[] values = new long[len];
        for (int i = 0; i < len; ++i) {
            values[i] = Long.parseLong(splits[i]);
        }
        this.values = values;
        this.traceId = splits[splits.length - 1];
    }

    @Override
    public String toString() {
        return "{" + DateFormatUtils.format(this.timestamp, "HH:mm:ss") + ": "
                + StringUtils.join(this.values, ",") + ',' + this.traceId + "}";
    }

    static {
        EMPTY_ARRAY = new long[0];
    }
}
