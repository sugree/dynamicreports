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

import net.sf.dynamicreports.report.base.barcode.DRCode128Barcode;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>Code128BarcodeBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class Code128BarcodeBuilder extends AbstractBarcode4jBuilder<Code128BarcodeBuilder, DRCode128Barcode> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for Code128BarcodeBuilder.</p>
     *
     * @param code a {@link java.lang.String} object.
     */
    protected Code128BarcodeBuilder(String code) {
        super(code, new DRCode128Barcode());
    }

    /**
     * <p>Constructor for Code128BarcodeBuilder.</p>
     *
     * @param codeExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected Code128BarcodeBuilder(DRIExpression<String> codeExpression) {
        super(codeExpression, new DRCode128Barcode());
    }
}
