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
package net.sf.dynamicreports.test.jasper;

import java.util.List;

import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.group.GroupBuilder;
import net.sf.dynamicreports.report.builder.subtotal.BaseSubtotalBuilder;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.util.JRStyledTextUtil;

/**
 * @author Ricardo Mariaca
 */
public abstract class AbstractJasperValueTest extends AbstractJasperTest {

    protected void elementCountTest(final String name, final int expectedNumberOfElements) {
        Assertions.assertEquals(expectedNumberOfElements, findElement(name).size(), "element count " + name);
    }

    protected void elementValueTest(final String name, final int index, final String value) {
        Assertions.assertEquals(value, getElementValue(name, index), "element value " + name);
    }

    protected void elementFullValueTest(final String name, final int index, final String value) {
        Assertions.assertEquals(value, getElementFullValue(name, index), "element value " + name);
    }

    protected void elementValueTest(final String name, final String... values) {
        final List<JRPrintElement> elements = findElement(name);
        Assertions.assertTrue(values.length <= elements.size());
        for (int i = 0; i < values.length; i++) {
            final JRPrintText textElement = (JRPrintText) elements.get(i);
            final String value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(textElement);
            Assertions.assertEquals("element value " + name, values[i], value);
        }
    }

    protected void elementFullValueTest(final String name, final String... values) {
        final List<JRPrintElement> elements = findElement(name);
        Assertions.assertTrue(values.length <= elements.size());
        for (int i = 0; i < values.length; i++) {
            final String value = ((JRPrintText) elements.get(i)).getFullText();
            Assertions.assertEquals("element value " + name, values[i], value);
        }
    }

    private String getElementValue(final String key, final int index) {
        final JRPrintText textElement = (JRPrintText) getElementAt(key, index);
        final String value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(textElement);
        return value;
    }

    private String getElementFullValue(final String key, final int index) {
        return ((JRPrintText) getElementAt(key, index)).getFullText();
    }

    // column detail
    protected void columnDetailCountTest(final ColumnBuilder<?, ?> column, final int expectedNumberOfElements) {
        elementCountTest(JasperTestUtils.getColumnDetailName(column), expectedNumberOfElements);
    }

    protected void columnDetailValueTest(final ColumnBuilder<?, ?> column, final int index, final String value) {
        elementValueTest(JasperTestUtils.getColumnDetailName(column), index, value);
    }

    protected void columnDetailFullValueTest(final ColumnBuilder<?, ?> column, final int index, final String value) {
        elementFullValueTest(JasperTestUtils.getColumnDetailName(column), index, value);
    }

    protected void columnDetailValueTest(final ColumnBuilder<?, ?> column, final String... values) {
        elementValueTest(JasperTestUtils.getColumnDetailName(column), values);
    }

    protected void columnDetailAtPageIndexTest(final ColumnBuilder<?, ?> column, final int pageIndex) {
        containsElement(JasperTestUtils.getColumnDetailName(column), pageIndex);
    }

    // column title
    protected void columnTitleCountTest(final ColumnBuilder<?, ?> column, final int expectedNumberOfElements) {
        elementCountTest(JasperTestUtils.getColumnTitleName(column), expectedNumberOfElements);
    }

    protected void columnTitleValueTest(final ColumnBuilder<?, ?> column, final int index, final String value) {
        elementValueTest(JasperTestUtils.getColumnTitleName(column), index, value);
    }

    protected void columnTitleFullValueTest(final ColumnBuilder<?, ?> column, final int index, final String value) {
        elementFullValueTest(JasperTestUtils.getColumnTitleName(column), index, value);
    }

    protected void columnTitleValueTest(final ColumnBuilder<?, ?> column, final String... values) {
        elementValueTest(JasperTestUtils.getColumnTitleName(column), values);
    }

    // subtotal label
    protected void subtotalLabelCountTest(final BaseSubtotalBuilder<?, ?> subtotal, final int expectedNumberOfElements) {
        elementCountTest(JasperTestUtils.getSubtotalLabelName(subtotal, 1), expectedNumberOfElements);
    }

    protected void subtotalLabelValueTest(final BaseSubtotalBuilder<?, ?> subtotal, final int index, final String value) {
        elementValueTest(JasperTestUtils.getSubtotalLabelName(subtotal, 1), index, value);
    }

    protected void subtotalLabelValueTest(final BaseSubtotalBuilder<?, ?> subtotal, final String... values) {
        elementValueTest(JasperTestUtils.getSubtotalLabelName(subtotal, 1), values);
    }

    protected void subtotalLabelIndexCountTest(final BaseSubtotalBuilder<?, ?> subtotal, final int subtotalIndex, final int expectedNumberOfElements) {
        elementCountTest(JasperTestUtils.getSubtotalLabelName(subtotal, subtotalIndex), expectedNumberOfElements);
    }

    protected void subtotalLabelIndexValueTest(final BaseSubtotalBuilder<?, ?> subtotal, final int subtotalIndex, final int index, final String value) {
        elementValueTest(JasperTestUtils.getSubtotalLabelName(subtotal, subtotalIndex), index, value);
    }

    protected void subtotalLabelIndexValueTest(final BaseSubtotalBuilder<?, ?> subtotal, final int subtotalIndex, final String... values) {
        elementValueTest(JasperTestUtils.getSubtotalLabelName(subtotal, subtotalIndex), values);
    }

    // subtotal
    protected void subtotalCountTest(final BaseSubtotalBuilder<?, ?> subtotal, final int expectedNumberOfElements) {
        elementCountTest(JasperTestUtils.getSubtotalName(subtotal, 1), expectedNumberOfElements);
    }

    protected void subtotalValueTest(final BaseSubtotalBuilder<?, ?> subtotal, final int index, final String value) {
        elementValueTest(JasperTestUtils.getSubtotalName(subtotal, 1), index, value);
    }

    protected void subtotalValueTest(final BaseSubtotalBuilder<?, ?> subtotal, final String... values) {
        elementValueTest(JasperTestUtils.getSubtotalName(subtotal, 1), values);
    }

    protected void subtotalIndexCountTest(final BaseSubtotalBuilder<?, ?> subtotal, final int subtotalIndex, final int expectedNumberOfElements) {
        elementCountTest(JasperTestUtils.getSubtotalName(subtotal, subtotalIndex), expectedNumberOfElements);
    }

    protected void subtotalIndexValueTest(final BaseSubtotalBuilder<?, ?> subtotal, final int subtotalIndex, final int index, final String value) {
        elementValueTest(JasperTestUtils.getSubtotalName(subtotal, subtotalIndex), index, value);
    }

    protected void subtotalIndexValueTest(final BaseSubtotalBuilder<?, ?> subtotal, final int subtotalIndex, final String... values) {
        elementValueTest(JasperTestUtils.getSubtotalName(subtotal, subtotalIndex), values);
    }

    // group header title
    protected void groupHeaderTitleCountTest(final GroupBuilder<?> group, final int expectedNumberOfElements) {
        elementCountTest(JasperTestUtils.getHeaderTitleGroupName(group), expectedNumberOfElements);
    }

    protected void groupHeaderTitleValueTest(final GroupBuilder<?> group, final int index, final String value) {
        elementValueTest(JasperTestUtils.getHeaderTitleGroupName(group), index, value);
    }

    protected void groupHeaderTitleValueTest(final GroupBuilder<?> group, final String... values) {
        elementValueTest(JasperTestUtils.getHeaderTitleGroupName(group), values);
    }

    // group header
    protected void groupHeaderCountTest(final GroupBuilder<?> group, final int expectedNumberOfElements) {
        elementCountTest(JasperTestUtils.getHeaderGroupName(group), expectedNumberOfElements);
    }

    protected void groupHeaderValueTest(final GroupBuilder<?> group, final int index, final String value) {
        elementValueTest(JasperTestUtils.getHeaderGroupName(group), index, value);
    }

    protected void groupHeaderValueTest(final GroupBuilder<?> group, final String... values) {
        elementValueTest(JasperTestUtils.getHeaderGroupName(group), values);
    }
}
