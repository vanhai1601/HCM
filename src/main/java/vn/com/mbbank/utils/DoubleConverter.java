/*
 * Copyright (C) 2010 HRPlus. All rights reserved.
 * HRPLUS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package vn.com.mbbank.utils;

import org.apache.commons.beanutils.Converter;

/**
 * Convert ve Double, can xu ly truong hop String.
 * @author author
 * @since 1.0
 * @version 1.0
 */
public class DoubleConverter implements Converter {

    @Override
    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return (value.toString().length() == 0) ? null : Double.parseDouble(value.toString());
        } else {
            return value;
        }
    }
}