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
package net.sf.dynamicreports.report.builder.barcode;

import net.sf.dynamicreports.report.base.barcode.DREan8Barcode;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>Ean8BarcodeBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class Ean8BarcodeBuilder extends AbstractChecksumBarcodeBuilder<Ean8BarcodeBuilder, DREan8Barcode> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for Ean8BarcodeBuilder.</p>
     *
     * @param code a {@link java.lang.String} object.
     */
    protected Ean8BarcodeBuilder(String code) {
        super(code, new DREan8Barcode());
    }

    /**
     * <p>Constructor for Ean8BarcodeBuilder.</p>
     *
     * @param codeExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected Ean8BarcodeBuilder(DRIExpression<String> codeExpression) {
        super(codeExpression, new DREan8Barcode());
    }
}
