/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
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
package net.sf.dynamicreports.adhoc.test;

import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.adhoc.configuration.AdhocChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroup;
import net.sf.dynamicreports.adhoc.configuration.AdhocRestriction;
import net.sf.dynamicreports.adhoc.configuration.AdhocTextField;
import net.sf.dynamicreports.adhoc.configuration.AdhocValueOperator;
import net.sf.dynamicreports.adhoc.configuration.AdhocValueRestriction;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRGroup;
import net.sf.dynamicreports.report.base.DRPage;
import net.sf.dynamicreports.report.base.DRReport;
import net.sf.dynamicreports.report.base.DRSort;
import net.sf.dynamicreports.report.base.DRSubtotal;
import net.sf.dynamicreports.report.base.chart.DRChart;
import net.sf.dynamicreports.report.base.chart.dataset.DRCategoryDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRAxisPlot;
import net.sf.dynamicreports.report.base.column.DRColumn;
import net.sf.dynamicreports.report.base.component.DRComponent;
import net.sf.dynamicreports.report.base.component.DRDimensionComponent;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.definition.chart.dataset.DRICategoryChartSerie;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca
 */
public class AdhocConfigurationLoadTest extends AdhocTests {

    @Test
    public void test() {
        try {
            final InputStream is = AdhocConfigurationLoadTest.class.getResourceAsStream("adhocconfiguration1.xml");
            final AdhocConfiguration adhocConfiguration = adhocManager.loadConfiguration(is);
            testConfiguration(adhocConfiguration);
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    private void testConfiguration(final AdhocConfiguration adhocConfiguration) {
        DRReport report = null;
        final ReportCustomizer customizer = new ReportCustomizer();
        try {
            final JasperReportBuilder reportBuilder = adhocManager.createReport(adhocConfiguration.getReport(), customizer);
            report = reportBuilder.getReport();
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }

        Assertions.assertNotNull(report.getTextStyle());
        Assertions.assertNotNull(report.getColumnStyle());
        Assertions.assertNotNull(report.getColumnTitleStyle());
        Assertions.assertNotNull(report.getGroupStyle());
        Assertions.assertNotNull(report.getGroupTitleStyle());
        Assertions.assertNotNull(report.getSubtotalStyle());
        Assertions.assertNotNull(report.getDetailOddRowStyle());
        Assertions.assertTrue(report.getHighlightDetailOddRows());
        Assertions.assertNotNull(report.getDetailEvenRowStyle());
        Assertions.assertTrue(report.getHighlightDetailEvenRows());

        final DRIStyle style = (DRIStyle) report.getTextStyle();
        Assertions.assertEquals("a", style.getFont().getFontName());
        Assertions.assertEquals(Integer.valueOf(5), style.getFont().getFontSize());
        Assertions.assertTrue(style.getFont().getBold());
        Assertions.assertTrue(style.getFont().getItalic());
        Assertions.assertTrue(style.getFont().getUnderline());
        Assertions.assertTrue(style.getFont().getStrikeThrough());
        Assertions.assertNotNull(style.getBorder().getTopPen());
        Assertions.assertNotNull(style.getBorder().getLeftPen());
        Assertions.assertNotNull(style.getBorder().getBottomPen());
        Assertions.assertNotNull(style.getBorder().getRightPen());
        Assertions.assertEquals(2f, style.getBorder().getTopPen().getLineWidth(),0);
        Assertions.assertEquals(Color.CYAN, style.getBorder().getTopPen().getLineColor());
        Assertions.assertEquals(Color.WHITE, style.getForegroundColor());
        Assertions.assertEquals(Color.DARK_GRAY, style.getBackgroundColor());
        Assertions.assertEquals(HorizontalTextAlignment.CENTER, style.getHorizontalTextAlignment());
        Assertions.assertEquals(VerticalTextAlignment.MIDDLE, style.getVerticalTextAlignment());
        Assertions.assertEquals("#,###.00", style.getPattern());

        final DRPage page = report.getPage();
        Assertions.assertEquals(Integer.valueOf(100), page.getWidth());
        Assertions.assertEquals(Integer.valueOf(200), page.getHeight());
        Assertions.assertEquals(PageOrientation.PORTRAIT, page.getOrientation());
        Assertions.assertEquals(1, page.getMargin().getTop());
        Assertions.assertEquals(2, page.getMargin().getBottom());
        Assertions.assertEquals(3, page.getMargin().getLeft());
        Assertions.assertEquals(4, page.getMargin().getRight());
        Assertions.assertTrue(page.getIgnorePageWidth());

        Assertions.assertEquals(3, report.getColumns().size());
        DRColumn<?> column = report.getColumns().get(0);
        Assertions.assertEquals("field1", column.getName());
        Assertions.assertNotNull(column.getTitleExpression());
        Assertions.assertNotNull(column.getComponent().getStyle());
        Assertions.assertNotNull(column.getTitleStyle());
        Assertions.assertEquals(Integer.valueOf(50), ((DRDimensionComponent) column.getComponent()).getWidth());
        Assertions.assertEquals(ComponentDimensionType.FIXED, ((DRDimensionComponent) column.getComponent()).getWidthType());

        column = report.getColumns().get(2);
        Assertions.assertEquals("field3", column.getName());
        Assertions.assertNotNull(column.getTitleExpression());
        Assertions.assertNull(column.getComponent().getStyle());
        Assertions.assertNull(column.getTitleStyle());
        Assertions.assertNull(((DRDimensionComponent) column.getComponent()).getWidth());
        Assertions.assertNull(((DRDimensionComponent) column.getComponent()).getWidthType());

        Assertions.assertEquals(2, report.getGroups().size());
        DRGroup group = report.getGroups().get(0);
        Assertions.assertTrue(group.getStartInNewPage());
        Assertions.assertEquals(GroupHeaderLayout.TITLE_AND_VALUE, group.getHeaderLayout());
        Assertions.assertNotNull(group.getValueField().getStyle());
        Assertions.assertNotNull(group.getTitleStyle());

        group = report.getGroups().get(1);
        Assertions.assertNull(group.getStartInNewPage());
        Assertions.assertNull(group.getHeaderLayout());
        Assertions.assertNull(group.getValueField().getStyle());
        Assertions.assertNull(group.getTitleStyle());

        Assertions.assertEquals(2, report.getSorts().size());
        final DRSort sort = report.getSorts().get(0);
        Assertions.assertNotNull(sort.getExpression());
        Assertions.assertEquals(OrderType.DESCENDING, sort.getOrderType());

        Assertions.assertEquals(2, report.getSubtotals().size());
        DRSubtotal<?> subtotal = report.getSubtotals().get(0);
        Assertions.assertNotNull(subtotal.getLabelExpression());
        Assertions.assertNotNull(subtotal.getValueField().getStyle());
        Assertions.assertNotNull(subtotal.getLabelStyle());
        subtotal = report.getSubtotals().get(1);
        Assertions.assertNull(subtotal.getLabelExpression());
        Assertions.assertNull(subtotal.getValueField().getStyle());
        Assertions.assertNull(subtotal.getLabelStyle());

        Assertions.assertEquals(3, customizer.getComponents().size());
        Assertions.assertTrue(adhocConfiguration.getReport().getComponent("textField") instanceof AdhocTextField);
        Assertions.assertTrue(adhocConfiguration.getReport().getComponent("chart1") instanceof AdhocChart);
        Assertions.assertTrue(adhocConfiguration.getReport().getComponent("chart2") instanceof AdhocChart);

        final DRComponent component = customizer.getComponents().get(0).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(0), adhocConfiguration.getReport().getComponent("textField"));
        Assertions.assertNotNull(component.getStyle());
        Assertions.assertEquals(Integer.valueOf(150), ((DRDimensionComponent) component).getWidth());
        Assertions.assertEquals(ComponentDimensionType.FIXED, ((DRDimensionComponent) component).getWidthType());
        Assertions.assertEquals(Integer.valueOf(200), ((DRDimensionComponent) component).getHeight());
        Assertions.assertEquals(ComponentDimensionType.FIXED, ((DRDimensionComponent) component).getHeightType());
        final DRTextField<?> textField = (DRTextField<?>) component;
        Assertions.assertNotNull(textField.getValueExpression());

        DRChart chart = (DRChart) customizer.getComponents().get(1).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(1), adhocConfiguration.getReport().getComponent("chart1"));
        Assertions.assertEquals(ChartType.AREA, chart.getChartType());
        Assertions.assertNotNull(chart.getTitle().getTitle());
        Assertions.assertNotNull(chart.getTitle().getFont());
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor());
        Assertions.assertTrue(chart.getLegend().getShowLegend());
        Assertions.assertNotNull(((DRCategoryDataset) chart.getDataset()).getValueExpression());
        DRICategoryChartSerie chartSerie = (DRICategoryChartSerie) ((DRCategoryDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression());
        Assertions.assertNotNull(chartSerie.getValueExpression());
        Assertions.assertNotNull(chartSerie.getLabelExpression());
        Assertions.assertEquals(Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0));
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getYAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont());
        Assertions.assertEquals(Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor());
        Assertions.assertEquals(Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation());
        Assertions.assertTrue(((DRCategoryDataset) chart.getDataset()).getUseSeriesAsCategory());

        chart = (DRChart) customizer.getComponents().get(2).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(2), adhocConfiguration.getReport().getComponent("chart2"));
        Assertions.assertNull(chart.getStyle());
        Assertions.assertNull(chart.getWidth());
        Assertions.assertNull(chart.getWidthType());
        Assertions.assertNull(chart.getHeight());
        Assertions.assertNull(chart.getHeightType());
        Assertions.assertNull(chart.getTitle().getTitle());
        Assertions.assertNull(chart.getTitle().getFont());
        Assertions.assertNull(chart.getTitle().getColor());
        Assertions.assertNull(chart.getLegend().getShowLegend());
        Assertions.assertNotNull(((DRCategoryDataset) chart.getDataset()).getValueExpression());
        chartSerie = (DRICategoryChartSerie) ((DRCategoryDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNull(chartSerie.getSeriesExpression());
        Assertions.assertNotNull(chartSerie.getValueExpression());
        Assertions.assertNotNull(chartSerie.getLabelExpression());
        Assertions.assertEquals(0, ((DRAxisPlot) chart.getPlot()).getSeriesColors().size());
        Assertions.assertNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression());
        Assertions.assertNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont());
        Assertions.assertNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor());
        Assertions.assertNull(((DRAxisPlot) chart.getPlot()).getOrientation());
        Assertions.assertNull(((DRCategoryDataset) chart.getDataset()).getUseSeriesAsCategory());

        final AdhocGroup adhocGroup = adhocConfiguration.getReport().getGroups().get(1);
        Assertions.assertEquals("value<a>&&</a>1", adhocGroup.getProperty("key1"));
        Assertions.assertEquals("value<a>&&</a>2",adhocGroup.getProperty("key2"));

        Assertions.assertEquals(2, adhocConfiguration.getFilter().getRestrictions().size());
        final AdhocRestriction adhocRestriction = adhocConfiguration.getFilter().getRestrictions().get(0);
        Assertions.assertEquals(adhocRestriction, adhocConfiguration.getFilter().getRestriction("restriction1"));
        Assertions.assertEquals("restriction1", adhocRestriction.getKey());
        Assertions.assertEquals("value", adhocRestriction.getProperty("key"));
        Assertions.assertNull(adhocRestriction.getProperty("key_empty"));
        Assertions.assertTrue((Boolean) adhocRestriction.getProperty("key_boolean"));
        Assertions.assertEquals(Integer.valueOf(100), adhocRestriction.getProperty("key_int"));

        final AdhocValueRestriction adhocValueRestriction = (AdhocValueRestriction) adhocConfiguration.getFilter().getRestrictions().get(1);
        Assertions.assertEquals(adhocValueRestriction, adhocConfiguration.getFilter().getRestriction("restriction2"));
        Assertions.assertEquals("restriction2", adhocValueRestriction.getKey());
        Assertions.assertTrue(adhocValueRestriction.getProperties().isEmpty());
        Assertions.assertEquals("aa", adhocValueRestriction.getName());
        Assertions.assertEquals(AdhocValueOperator.IN, adhocValueRestriction.getOperator());
        Assertions.assertEquals("value1",adhocValueRestriction.getValues().get(0));
        Assertions.assertEquals("value2",adhocValueRestriction.getValues().get(1));
    }

    private class ReportCustomizer extends DefaultAdhocReportCustomizer {

        private List<ComponentBuilder<?, ?>> getComponents() {
            return new ArrayList<ComponentBuilder<?, ?>>(components.values());
        }
    }
}
