/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2022 The Dynamic Reports Contributors
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.dynamicreports.test.base;

import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca
 */
public class DataTypeTest {

    @Test
    public void detectTypeTest() {
        try {
            detectTypeTest(type.bigDecimalType(), BigDecimal.class);
            detectTypeTest(type.bigIntegerType(), BigInteger.class);
            detectTypeTest(type.byteType(), Byte.class);
            detectTypeTest(type.doubleType(), Double.class);
            detectTypeTest(type.floatType(), Float.class);
            detectTypeTest(type.integerType(), Integer.class);
            detectTypeTest(type.longType(), Long.class);
            detectTypeTest(type.shortType(), Short.class);
            detectTypeTest(type.dateType(), Date.class);
            detectTypeTest(type.dateYearToMonthType(), "DateYearToMonth");
            detectTypeTest(type.dateYearToHourType(), "DateYearToHour");
            detectTypeTest(type.dateYearToMinuteType(), "DateYearToMinute");
            detectTypeTest(type.dateYearToSecondType(), "DateYearToSecond");
            detectTypeTest(type.dateYearToFractionType(), "DateYearToFraction");
            detectTypeTest(type.dateYearType(), "DateYear");
            detectTypeTest(type.dateMonthType(), "DateMonth");
            detectTypeTest(type.dateDayType(), "DateDay");
            detectTypeTest(type.timeHourToMinuteType(), "TimeHourToMinute");
            detectTypeTest(type.timeHourToSecondType(), "TimeHourToSecond");
            detectTypeTest(type.timeHourToFractionType(), "TimeHourToFraction");
            detectTypeTest(type.percentageType(), "Percentage");
            detectTypeTest(type.booleanType(), Boolean.class);
            detectTypeTest(type.characterType(), Character.class);
            detectTypeTest(type.stringType(), String.class);
            detectTypeTest(type.stringType(), "Text");
            detectTypeTest(type.listType(), List.class);

            type.detectType(BigDecimal.class);
            type.detectType(String.class);
        } catch (final DRException e) {
            Assertions.fail(e.getMessage());
        }
    }

    public <U, T extends U> void detectTypeTest(final DRIDataType<U, T> dataType, final Class<T> valueClass) throws DRException {
        detectTypeTest(dataType, valueClass.getSimpleName());
        Assertions.assertEquals(dataType.getClass(), type.detectType(valueClass.getName()).getClass(), "Detect data type");
        Assertions.assertEquals(dataType.getClass(), type.detectType(valueClass).getClass(), "Detect data type");
    }

    public <U, T extends U> void detectTypeTest(final DRIDataType<U, T> dataType, final String... dataTypes) throws DRException {
        for (final String stringDataType : dataTypes) {
            Assertions.assertEquals(dataType.getClass(), type.detectType(stringDataType).getClass(), "Detect data type");
            Assertions.assertEquals(dataType.getClass(), type.detectType(stringDataType.toLowerCase()).getClass(), "Detect data type");
            Assertions.assertEquals(dataType.getClass(), type.detectType(stringDataType.toUpperCase()).getClass(), "Detect data type");
        }
    }

    @Test
    public void valueConversionTest() {
        valueConversionTest("BigDecimal", type.bigDecimalType(), 1000, "1,000.00");
        valueConversionTest("BigInteger", type.bigIntegerType(), 1000, "1,000");
        valueConversionTest("Byte", type.byteType(), 100, "100");
        valueConversionTest("Double", type.doubleType(), 1000.1, "1,000.1");
        valueConversionTest("Float", type.floatType(), 1000.1, "1,000.1");
        valueConversionTest("Integer", type.integerType(), 1000, "1,000");
        valueConversionTest("Long", type.longType(), 1000, "1,000");
        valueConversionTest("Short", type.shortType(), 1000, "1,000");

        final Calendar c = Calendar.getInstance();
        c.set(2010, 0, 2, 15, 5, 20);
        c.set(Calendar.MILLISECOND, 100);
        final Date date = c.getTime();
        valueConversionTest("Date", type.dateType(), date, "01/02/2010");
        valueConversionTest("DateYearToMonth", type.dateYearToMonthType(), date, "01/2010");
        valueConversionTest("DateYearToHour", type.dateYearToHourType(), date, "01/02/2010 3 PM");
        valueConversionTest("DateYearToMinute", type.dateYearToMinuteType(), date, "01/02/2010 3:05 PM");
        valueConversionTest("DateYearToSecond", type.dateYearToSecondType(), date, "01/02/2010 3:05:20 PM");
        valueConversionTest("DateYearToFraction", type.dateYearToFractionType(), date, "01/02/2010 3:05:20,100 PM");
        valueConversionTest("DateYear", type.dateYearType(), date, "2010");
        valueConversionTest("DateMonth", type.dateMonthType(), date, "January");
        valueConversionTest("DateDay", type.dateDayType(), date, "02");
        valueConversionTest("TimeHourToMinute", type.timeHourToMinuteType(), date, "3:05 PM");
        valueConversionTest("TimeHourToSecond", type.timeHourToSecondType(), date, "3:05:20 PM");
        valueConversionTest("TimeHourToFraction", type.timeHourToFractionType(), date, "3:05:20,100 PM");

        valueConversionTest("Percentage", type.percentageType(), 0.89156, "89.16%");
        valueConversionTest("Boolean", type.booleanType(), true, "true");
        valueConversionTest("Character", type.characterType(), 'a', "a");
        valueConversionTest("String", type.stringType(), "text", "text");
    }

    private <U, T extends U> void valueConversionTest(final String name, final DRIDataType<U, T> dataType, final U value, final String stringValue) {
        Assertions.assertEquals(stringValue, dataType.valueToString(value, Locale.ENGLISH), name + " valueToString");

        try {
            final String stringResult = dataType.valueToString(value, Locale.ENGLISH);
            final U stringToValue = dataType.stringToValue(stringValue, Locale.ENGLISH);
            Assertions.assertTrue(stringToValue.getClass().equals(dataType.getValueClass()), name + " stringToValue class ");
            Assertions.assertEquals(stringResult, dataType.valueToString(stringToValue, Locale.ENGLISH), name + " stringToValue");
        } catch (final DRException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
