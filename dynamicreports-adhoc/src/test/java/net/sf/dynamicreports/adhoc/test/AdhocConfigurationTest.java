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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.adhoc.configuration.AdhocAxisFormat;
import net.sf.dynamicreports.adhoc.configuration.AdhocCalculation;
import net.sf.dynamicreports.adhoc.configuration.AdhocChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocChartSerie;
import net.sf.dynamicreports.adhoc.configuration.AdhocChartType;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocFilter;
import net.sf.dynamicreports.adhoc.configuration.AdhocFont;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroup;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroupHeaderLayout;
import net.sf.dynamicreports.adhoc.configuration.AdhocHorizontalAlignment;
import net.sf.dynamicreports.adhoc.configuration.AdhocOrderType;
import net.sf.dynamicreports.adhoc.configuration.AdhocOrientation;
import net.sf.dynamicreports.adhoc.configuration.AdhocPage;
import net.sf.dynamicreports.adhoc.configuration.AdhocPageOrientation;
import net.sf.dynamicreports.adhoc.configuration.AdhocPen;
import net.sf.dynamicreports.adhoc.configuration.AdhocProperties;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.configuration.AdhocRestriction;
import net.sf.dynamicreports.adhoc.configuration.AdhocSort;
import net.sf.dynamicreports.adhoc.configuration.AdhocStyle;
import net.sf.dynamicreports.adhoc.configuration.AdhocSubtotal;
import net.sf.dynamicreports.adhoc.configuration.AdhocSubtotalPosition;
import net.sf.dynamicreports.adhoc.configuration.AdhocTextField;
import net.sf.dynamicreports.adhoc.configuration.AdhocValueOperator;
import net.sf.dynamicreports.adhoc.configuration.AdhocValueRestriction;
import net.sf.dynamicreports.adhoc.configuration.AdhocVerticalAlignment;
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
import net.sf.dynamicreports.report.constant.SubtotalPosition;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.definition.chart.dataset.DRICategoryChartSerie;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca
 */
public class AdhocConfigurationTest extends AdhocTests {
    private AdhocConfiguration adhocConfiguration;

    @BeforeEach
    public void init() {
        adhocConfiguration = new AdhocConfiguration();
        final AdhocReport adhocReport = new AdhocReport();
        adhocConfiguration.setReport(adhocReport);

        adhocReport.setProperty("report", "property value");

        final AdhocStyle adhocStyle1 = new AdhocStyle();
        adhocStyle1.setForegroundColor(Color.BLUE);
        final AdhocStyle adhocStyle2 = new AdhocStyle();
        final AdhocFont adhocFont = new AdhocFont();
        adhocFont.setFontName("a");
        adhocFont.setFontSize(5);
        adhocFont.setBold(true);
        adhocFont.setItalic(true);
        adhocFont.setUnderline(true);
        adhocFont.setStrikeThrough(true);
        adhocStyle2.setFont(adhocFont);
        final AdhocPen adhocPen = new AdhocPen();
        adhocPen.setLineWidth(2f);
        adhocPen.setLineColor(Color.CYAN);
        adhocStyle2.setTopBorder(adhocPen);
        adhocStyle2.setLeftBorder(adhocPen);
        adhocStyle2.setBottomBorder(adhocPen);
        adhocStyle2.setRightBorder(adhocPen);
        adhocStyle2.setForegroundColor(Color.WHITE);
        adhocStyle2.setBackgroundColor(Color.DARK_GRAY);
        adhocStyle2.setHorizontalAlignment(AdhocHorizontalAlignment.CENTER);
        adhocStyle2.setVerticalAlignment(AdhocVerticalAlignment.MIDDLE);
        adhocStyle2.setPattern("#,###.00");

        AdhocColumn adhocColumn = new AdhocColumn();
        adhocColumn.setName("field1");
        adhocColumn.setTitle("Column1");
        adhocColumn.setStyle(adhocStyle1);
        adhocColumn.setTitleStyle(adhocStyle1);
        adhocColumn.setWidth(50);
        adhocColumn.setProperty("type", "integer");
        adhocReport.addColumn(adhocColumn);

        adhocReport.setTextStyle(adhocStyle2);
        adhocReport.setColumnStyle(adhocStyle1);
        adhocReport.setColumnTitleStyle(adhocStyle1);
        adhocReport.setGroupStyle(adhocStyle1);
        adhocReport.setGroupTitleStyle(adhocStyle1);
        adhocReport.setSubtotalStyle(adhocStyle1);
        adhocReport.setDetailOddRowStyle(adhocStyle1);
        adhocReport.setHighlightDetailOddRows(true);
        adhocReport.setDetailEvenRowStyle(adhocStyle1);
        adhocReport.setHighlightDetailEvenRows(true);
        adhocReport.setIgnorePagination(true);
        adhocReport.setTableOfContents(true);

        final AdhocPage adhocPage = new AdhocPage();
        adhocPage.setWidth(100);
        adhocPage.setHeight(200);
        adhocPage.setOrientation(AdhocPageOrientation.PORTRAIT);
        adhocPage.setTopMargin(1);
        adhocPage.setBottomMargin(2);
        adhocPage.setLeftMargin(3);
        adhocPage.setRightMargin(4);
        adhocPage.setIgnorePageWidth(true);
        adhocReport.setPage(adhocPage);

        adhocColumn = new AdhocColumn();
        adhocColumn.setName("field2");
        adhocColumn.setTitle("Column2");
        adhocReport.addColumn(adhocColumn);

        adhocColumn = new AdhocColumn();
        adhocColumn.setName("field3");
        adhocReport.addColumn(adhocColumn);

        AdhocGroup adhocGroup = new AdhocGroup();
        adhocGroup.setName("field4");
        adhocGroup.setStartInNewPage(true);
        adhocGroup.setHeaderLayout(AdhocGroupHeaderLayout.TITLE_AND_VALUE);
        adhocGroup.setStyle(adhocStyle1);
        adhocGroup.setTitleStyle(adhocStyle1);
        adhocReport.addGroup(adhocGroup);

        adhocGroup = new AdhocGroup();
        adhocGroup.setName("field4");
        adhocGroup.setProperty("key1", "value<a>&&</a>1");
        adhocGroup.setProperty("key2", "value<a>&&</a>2");
        adhocReport.addGroup(adhocGroup);

        AdhocSort adhocSort = new AdhocSort();
        adhocSort.setName("field1");
        adhocSort.setOrderType(AdhocOrderType.DESCENDING);
        adhocReport.addSort(adhocSort);

        adhocSort = new AdhocSort();
        adhocSort.setName("field1");
        adhocReport.addSort(adhocSort);

        AdhocSubtotal adhocSubtotal = new AdhocSubtotal();
        adhocSubtotal.setName("field1");
        adhocSubtotal.setLabel("label");
        adhocSubtotal.setCalculation(AdhocCalculation.SUM);
        adhocSubtotal.setStyle(adhocStyle1);
        adhocSubtotal.setLabelStyle(adhocStyle1);
        adhocReport.addSubtotal(adhocSubtotal);

        adhocSubtotal = new AdhocSubtotal();
        adhocSubtotal.setName("field1");
        adhocReport.addSubtotal(adhocSubtotal);

        adhocSubtotal = new AdhocSubtotal();
        adhocSubtotal.setName("field2");
        adhocSubtotal.setPosition(AdhocSubtotalPosition.GROUP_FOOTER);
        adhocSubtotal.setGroupName("field4");
        adhocReport.addSubtotal(adhocSubtotal);

        final AdhocTextField adhocTextField = new AdhocTextField();
        adhocTextField.setKey("textField");
        adhocTextField.setStyle(adhocStyle1);
        adhocTextField.setWidth(150);
        adhocTextField.setHeight(200);
        adhocTextField.setText("text");
        adhocReport.addComponent(adhocTextField);

        AdhocChart adhocChart = new AdhocChart();
        adhocChart.setKey("chart1");
        adhocChart.setTitle("title");
        adhocChart.setTitleFont(new AdhocFont());
        adhocChart.setTitleColor(Color.MAGENTA);
        adhocChart.setShowLegend(true);
        adhocChart.setType(AdhocChartType.AREA);
        adhocChart.setXValue("field2");
        AdhocChartSerie serie = new AdhocChartSerie();
        serie.setYValue("field1");
        serie.setSeries("field5");
        serie.setLabel("label");
        serie.setProperty("series_key", "series_value");
        adhocChart.addSerie(serie);
        adhocChart.addSeriesColor(Color.LIGHT_GRAY);
        final AdhocAxisFormat axisFormat = new AdhocAxisFormat();
        axisFormat.setLabel("label");
        axisFormat.setLabelFont(new AdhocFont());
        axisFormat.setLabelColor(Color.BLUE);
        adhocChart.setXAxisFormat(axisFormat);
        adhocChart.setYAxisFormat(axisFormat);
        adhocChart.setOrientation(AdhocOrientation.VERTICAL);
        adhocChart.setProperty(AdhocProperties.CHART_USE_SERIES_AS_CATEGORY, true);
        adhocReport.addComponent(adhocChart);

        adhocChart = new AdhocChart();
        adhocChart.setKey("chart2");
        adhocChart.setXValue("field2");
        serie = new AdhocChartSerie();
        serie.setYValue("field1");
        adhocChart.addSerie(serie);
        adhocReport.addComponent(adhocChart);

        final AdhocFilter adhocFilter = new AdhocFilter();
        adhocConfiguration.setFilter(adhocFilter);

        final AdhocRestriction adhocRestriction = new AdhocRestriction();
        adhocRestriction.setKey("restriction1");
        adhocRestriction.setProperty("key", "value");
        adhocRestriction.setProperty("key_empty", null);
        adhocRestriction.setProperty("key_boolean", true);
        adhocRestriction.setProperty("key_int", 100);
        adhocFilter.addRestriction(adhocRestriction);

        final AdhocValueRestriction adhocValueRestriction = new AdhocValueRestriction();
        adhocValueRestriction.setKey("restriction2");
        adhocValueRestriction.setName("aa");
        adhocValueRestriction.addValue("value1");
        adhocValueRestriction.addValue("value2");
        adhocValueRestriction.setOperator(AdhocValueOperator.IN);
        adhocFilter.addRestriction(adhocValueRestriction);
    }

    @Test
    public void test() {
        testConfiguration(adhocConfiguration);
    }

    @Test
    public void testSaveAndLoad() {
        try {
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            adhocManager.saveConfiguration(adhocConfiguration, os);
            final ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
            final AdhocConfiguration adhocConfiguration = adhocManager.loadConfiguration(is);
            Assertions.assertTrue( this.adhocConfiguration.equals(adhocConfiguration));
            Assertions.assertTrue( this.adhocConfiguration.equals(adhocConfiguration.clone()));
            testConfiguration(adhocConfiguration);
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void testEqualsAndClone() {
        final AdhocConfiguration adhocConfiguration2 = adhocConfiguration.clone();
        testConfiguration(adhocConfiguration2);
        Assertions.assertTrue( adhocConfiguration.equals(adhocConfiguration));
        Assertions.assertTrue( adhocConfiguration.equals(adhocConfiguration2));
        final AdhocColumn adhocColumn = new AdhocColumn();
        adhocColumn.setName("field3");
        adhocConfiguration2.getReport().addColumn(adhocColumn);
        Assertions.assertFalse( adhocConfiguration.equals(adhocConfiguration2));
        final AdhocConfiguration adhocConfiguration3 = adhocConfiguration2.clone();
        Assertions.assertTrue( adhocConfiguration2.equals(adhocConfiguration3));
        adhocConfiguration3.getReport().getColumns().get(0).setName("c");
        Assertions.assertFalse( adhocConfiguration2.equals(adhocConfiguration3));
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

        Assertions.assertEquals("property value", adhocConfiguration.getReport().getProperty("report"));

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
        Assertions.assertEquals(2f, style.getBorder().getTopPen().getLineWidth(), 0);
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
        Assertions.assertEquals("integer", adhocConfiguration.getReport().getColumn("field1").getProperty("type"));

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

        Assertions.assertEquals(3, report.getSubtotals().size());
        DRSubtotal<?> subtotal = report.getSubtotals().get(0);
        Assertions.assertNull(subtotal.getLabelExpression());
        Assertions.assertNull(subtotal.getValueField().getStyle());
        Assertions.assertNull(subtotal.getLabelStyle());
        Assertions.assertEquals(SubtotalPosition.GROUP_FOOTER, subtotal.getPosition());
        Assertions.assertEquals(report.getGroups().get(1), subtotal.getGroup());
        subtotal = report.getSubtotals().get(1);
        Assertions.assertNotNull(subtotal.getLabelExpression());
        Assertions.assertNotNull(subtotal.getValueField().getStyle());
        Assertions.assertNotNull(subtotal.getLabelStyle());
        subtotal = report.getSubtotals().get(2);
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
        final AdhocChart adhocChart = (AdhocChart) adhocConfiguration.getReport().getComponents().get(1);
        Assertions.assertEquals("series_value", adhocChart.getSeries().get(0).getProperty("series_key"));
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
        Assertions.assertEquals("value<a>&&</a>2", adhocGroup.getProperty("key2"));

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
        Assertions.assertEquals("value1", adhocValueRestriction.getValues().get(0));
        Assertions.assertEquals("value2", adhocValueRestriction.getValues().get(1));

    }

    private class ReportCustomizer extends DefaultAdhocReportCustomizer {

        private List<ComponentBuilder<?, ?>> getComponents() {
            return new ArrayList<ComponentBuilder<?, ?>>(components.values());
        }
    }
}
