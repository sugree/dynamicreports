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
package net.sf.dynamicreports.report.definition.component;

import net.sf.dynamicreports.report.definition.DRITableOfContentsHeading;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRIPropertyExpression;
import net.sf.dynamicreports.report.definition.style.DRIReportStyle;

import java.io.Serializable;
import java.util.List;

/**
 * <p>DRIComponent interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIComponent extends Serializable {

    /**
     * <p>getStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getStyle();

    /**
     * <p>getPrintWhenExpression.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRIExpression<Boolean> getPrintWhenExpression();

    /**
     * <p>getRemoveLineWhenBlank.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getRemoveLineWhenBlank();

    /**
     * <p>getPropertyExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIPropertyExpression> getPropertyExpressions();

    /**
     * <p>getTableOfContentsHeading.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.DRITableOfContentsHeading} object.
     */
    public DRITableOfContentsHeading getTableOfContentsHeading();
}
