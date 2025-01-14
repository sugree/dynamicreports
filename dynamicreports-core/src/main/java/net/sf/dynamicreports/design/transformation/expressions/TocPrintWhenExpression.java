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
package net.sf.dynamicreports.design.transformation.expressions;

import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import java.util.List;

/**
 * <p>TocPrintWhenExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class TocPrintWhenExpression extends AbstractComplexExpression<Boolean> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Object lastValue;

    /**
     * <p>Constructor for TocPrintWhenExpression.</p>
     *
     * @param expression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public TocPrintWhenExpression(DRIExpression<?> expression) {
        addExpression(expression);
    }

    /** {@inheritDoc} */
    @Override
    public Boolean evaluate(List<?> values, ReportParameters reportParameters) {
        Object value = values.get(0);
        if (lastValue != null && lastValue.equals(value)) {
            lastValue = value;
            return false;
        }
        lastValue = value;
        return true;
    }
}
