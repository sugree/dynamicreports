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
package net.sf.dynamicreports.report.builder.chart;

import net.sf.dynamicreports.report.base.chart.dataset.DRGroupedCategoryChartSerie;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.VariableBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import org.apache.commons.lang3.Validate;

/**
 * <p>GroupedCategoryChartSerieBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class GroupedCategoryChartSerieBuilder extends AbstractCategoryChartSerieBuilder<GroupedCategoryChartSerieBuilder, DRGroupedCategoryChartSerie> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for GroupedCategoryChartSerieBuilder.</p>
     *
     * @param column a {@link net.sf.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     */
    protected GroupedCategoryChartSerieBuilder(ValueColumnBuilder<?, ? extends Number> column) {
        super(new DRGroupedCategoryChartSerie(), column);
    }

    /**
     * <p>Constructor for GroupedCategoryChartSerieBuilder.</p>
     *
     * @param field a {@link net.sf.dynamicreports.report.builder.FieldBuilder} object.
     */
    protected GroupedCategoryChartSerieBuilder(FieldBuilder<? extends Number> field) {
        super(new DRGroupedCategoryChartSerie(), field);
    }

    /**
     * <p>Constructor for GroupedCategoryChartSerieBuilder.</p>
     *
     * @param valueExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected GroupedCategoryChartSerieBuilder(DRIExpression<? extends Number> valueExpression) {
        super(new DRGroupedCategoryChartSerie(), valueExpression);
    }

    /**
     * <p>Constructor for GroupedCategoryChartSerieBuilder.</p>
     *
     * @param variable a {@link net.sf.dynamicreports.report.builder.VariableBuilder} object.
     */
    protected GroupedCategoryChartSerieBuilder(VariableBuilder<? extends Number> variable) {
        super(new DRGroupedCategoryChartSerie(), variable);
    }

    // group

    /**
     * <p>setGroup.</p>
     *
     * @param column a {@link net.sf.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @return a {@link net.sf.dynamicreports.report.builder.chart.GroupedCategoryChartSerieBuilder} object.
     */
    public GroupedCategoryChartSerieBuilder setGroup(ValueColumnBuilder<?, String> column) {
        Validate.notNull(column, "column must not be null");
        getObject().setGroupExpression(column.getColumn());
        return this;
    }

    /**
     * <p>setGroup.</p>
     *
     * @param field a {@link net.sf.dynamicreports.report.builder.FieldBuilder} object.
     * @return a {@link net.sf.dynamicreports.report.builder.chart.GroupedCategoryChartSerieBuilder} object.
     */
    public GroupedCategoryChartSerieBuilder setGroup(FieldBuilder<String> field) {
        Validate.notNull(field, "field must not be null");
        getObject().setGroupExpression(field.build());
        return this;
    }

    /**
     * <p>setGroup.</p>
     *
     * @param valueExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link net.sf.dynamicreports.report.builder.chart.GroupedCategoryChartSerieBuilder} object.
     */
    public GroupedCategoryChartSerieBuilder setGroup(DRIExpression<String> valueExpression) {
        getObject().setGroupExpression(valueExpression);
        return this;
    }

    /**
     * <p>setGroup.</p>
     *
     * @param variable a {@link net.sf.dynamicreports.report.builder.VariableBuilder} object.
     * @return a {@link net.sf.dynamicreports.report.builder.chart.GroupedCategoryChartSerieBuilder} object.
     */
    public GroupedCategoryChartSerieBuilder setGroup(VariableBuilder<String> variable) {
        Validate.notNull(variable, "variable must not be null");
        getObject().setGroupExpression(variable.build());
        return this;
    }
}
