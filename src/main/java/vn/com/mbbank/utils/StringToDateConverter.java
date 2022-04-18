/*
 * Copyright (C) 2010 HRPlus. All rights reserved.
 * HRPLUS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package vn.com.mbbank.utils;

import org.apache.commons.beanutils.Converter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Convert Date, can xu ly truong hop String.
 *
 * @author author
 * @since 1.0
 * @version 1.0
 */
public class StringToDateConverter implements Converter {

    private static final Logger LOGGER = LogManager.getLogger(StringToDateConverter.class);

    @Override
    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            try {
                return Utils.convertStringToDate(value.toString());
            } catch (Exception ex) {
                LOGGER.debug("debug", ex);
                return null;
            }
        } else {
            return value;
        }
    }
}
