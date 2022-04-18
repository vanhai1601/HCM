/*
 * Copyright (C) 2010 HRPlus. All rights reserved.
 * HRPLUS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package vn.com.mbbank.utils;

import org.apache.commons.beanutils.Converter;

/**
 * Cac truong hop Combobox, khi -chon tat ca-, -chon-, khong co gia tri gi nhung submit ve bang 0.
 * @author author
 * @since 1.0
 * @version 1.0
 */
public class LongConverter implements Converter {

    @SuppressWarnings("unchecked")
    @Override
    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Long) {
            return (value.equals(0L)) ? null : value;
        } else if (value instanceof String) {
            return (value.toString().length() == 0) ? null : Long.parseLong(value.toString());
        } else {
            return value;
        }
    }
}