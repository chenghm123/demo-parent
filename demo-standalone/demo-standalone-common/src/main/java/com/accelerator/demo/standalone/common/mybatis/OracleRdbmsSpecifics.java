package com.accelerator.demo.standalone.common.mybatis;

import java.sql.Timestamp;
import java.util.Date;

import static org.apache.commons.lang3.time.DateFormatUtils.format;

class OracleRdbmsSpecifics extends RdbmsSpecifics {

    String formatParameter(Object parameter) {
        if (parameter instanceof Timestamp) {
            String dateStr = format((Timestamp) parameter, "MM/dd/yyyy HH:mm:ss.SSS");
            return String.format("to_timestamp('%s', 'mm/dd/yyyy hh24:mi:ss.ff3')", dateStr);
        } else if (parameter instanceof Date) {
            String dateStr = format((Date) parameter, "MM/dd/yyyy HH:mm:ss");
            return String.format("to_date('%s', 'mm/dd/yyyy hh24:mi:ss')", dateStr);
        } else {
            return super.formatParameter(parameter);
        }
    }

}
