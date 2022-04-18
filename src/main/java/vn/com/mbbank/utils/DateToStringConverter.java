/*
 * Copyright (C) 2010 HRPlus. All rights reserved.
 * HRPLUS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package vn.com.mbbank.utils;

import java.util.Date;
import org.apache.commons.beanutils.Converter;

/**
 * Convert ve String, can xu ly truong hop Date.
 * @author author
 * @since 1.0
 * @version 1.0
 */
public class DateToStringConverter implements Converter {

    @Override
    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Date) {
            return Utils.convertDateToString((Date) value);
        } else if (value instanceof Double) {
            return Utils.formatNumber((Double) value);
        } else if (value instanceof Long) {
            return Utils.formatNumber((Long) value);
        } else {
            return value.toString();
        }
    }
}
