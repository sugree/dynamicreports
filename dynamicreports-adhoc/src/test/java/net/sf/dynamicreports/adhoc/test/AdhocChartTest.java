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
import net.sf.dynamicreports.adhoc.configuration.AdhocChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocChartSerie;
import net.sf.dynamicreports.adhoc.configuration.AdhocChartType;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocFont;
import net.sf.dynamicreports.adhoc.configuration.AdhocOrientation;
import net.sf.dynamicreports.adhoc.configuration.AdhocProperties;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.configuration.AdhocTimePeriod;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.report.base.chart.DRChart;
import net.sf.dynamicreports.report.base.chart.dataset.DRCategoryDataset;
import net.sf.dynamicreports.report.base.chart.dataset.DRSeriesDataset;
import net.sf.dynamicreports.report.base.chart.dataset.DRTimeSeriesDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRAxisPlot;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.definition.chart.dataset.DRICategoryChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIGroupedCategoryChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIXyChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIXyzChartSerie;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca
 */
public class AdhocChartTest extends AdhocTests {
    private AdhocConfiguration adhocConfiguration;

    @BeforeEach
    public void init() {
        adhocConfiguration = new AdhocConfiguration();
        final AdhocReport adhocReport = new AdhocReport();
        adhocConfiguration.setReport(adhocReport);

        AdhocChart adhocChart = new AdhocChart();
        adhocChart.setKey("chart1");
        adhocChart.setTitle("title");
        adhocChart.setTitleFont(new AdhocFont());
        adhocChart.setTitleColor(Color.MAGENTA);
        adhocChart.setShowLegend(true);
        adhocChart.setProperty(AdhocProperties.CHART_SHOW_VALUES, true);
        adhocChart.setProperty(AdhocProperties.CHART_SHOW_PERCENTAGES, true);
        adhocChart.setType(AdhocChartType.AREA);
        adhocChart.setXValue("field2");
        AdhocChartSerie serie = new AdhocChartSerie();
        serie.setYValue("field1");
        serie.setSeries("field5");
        serie.setLabel("label");
        adhocChart.addSerie(serie);
        adhocChart.addSeriesColor(Color.LIGHT_GRAY);
        AdhocAxisFormat axisFormat = new AdhocAxisFormat();
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
        adhocChart.setTitle("title");
        adhocChart.setTitleFont(new AdhocFont());
        adhocChart.setTitleColor(Color.MAGENTA);
        adhocChart.setShowLegend(true);
        adhocChart.setType(AdhocChartType.TIMESERIES);
        adhocChart.setXValue("field2");
        serie = new AdhocChartSerie();
        serie.setYValue("field1");
        serie.setSeries("field5");
        serie.setLabel("label");
        adhocChart.addSerie(serie);
        adhocChart.addSeriesColor(Color.LIGHT_GRAY);
        axisFormat = new AdhocAxisFormat();
        axisFormat.setLabel("label");
        axisFormat.setLabelFont(new AdhocFont());
        axisFormat.setLabelColor(Color.BLUE);
        adhocChart.setXAxisFormat(axisFormat);
        adhocChart.setYAxisFormat(axisFormat);
        adhocChart.setOrientation(AdhocOrientation.VERTICAL);
        adhocChart.setProperty(AdhocProperties.CHART_TIME_PERIOD, AdhocTimePeriod.MONTH);
        adhocReport.addComponent(adhocChart);

        adhocChart = new AdhocChart();
        adhocChart.setKey("chart3");
        adhocChart.setTitle("title");
        adhocChart.setTitleFont(new AdhocFont());
        adhocChart.setTitleColor(Color.MAGENTA);
        adhocChart.setShowLegend(true);
        adhocChart.setType(AdhocChartType.PIE);
        adhocChart.setXValue("field2");
        serie = new AdhocChartSerie();
        serie.setYValue("field1");
        serie.setSeries("field5");
        serie.setLabel("label");
        adhocChart.addSerie(serie);
        adhocReport.addComponent(adhocChart);

        adhocChart = new AdhocChart();
        adhocChart.setKey("chart4");
        adhocChart.setTitle("title");
        adhocChart.setTitleFont(new AdhocFont());
        adhocChart.setTitleColor(Color.MAGENTA);
        adhocChart.setShowLegend(true);
        adhocChart.setType(AdhocChartType.XYBAR);
        adhocChart.setXValue("field2");
        serie = new AdhocChartSerie();
        serie.setYValue("field1");
        serie.setSeries("field5");
        serie.setLabel("label");
        adhocChart.addSerie(serie);
        adhocChart.addSeriesColor(Color.LIGHT_GRAY);
        axisFormat = new AdhocAxisFormat();
        axisFormat.setLabel("label");
        axisFormat.setLabelFont(new AdhocFont());
        axisFormat.setLabelColor(Color.BLUE);
        adhocChart.setXAxisFormat(axisFormat);
        adhocChart.setYAxisFormat(axisFormat);
        adhocChart.setOrientation(AdhocOrientation.VERTICAL);
        adhocReport.addComponent(adhocChart);

        adhocChart = new AdhocChart();
        adhocChart.setKey("chart5");
        adhocChart.setTitle("title");
        adhocChart.setTitleFont(new AdhocFont());
        adhocChart.setTitleColor(Color.MAGENTA);
        adhocChart.setShowLegend(true);
        adhocChart.setType(AdhocChartType.SPIDER);
        adhocChart.setXValue("field2");
        serie = new AdhocChartSerie();
        serie.setYValue("field1");
        serie.setSeries("field5");
        serie.setLabel("label");
        adhocChart.addSerie(serie);
        adhocReport.addComponent(adhocChart);

        adhocChart = new AdhocChart();
        adhocChart.setKey("chart6");
        adhocChart.setTitle("title");
        adhocChart.setTitleFont(new AdhocFont());
        adhocChart.setTitleColor(Color.MAGENTA);
        adhocChart.setShowLegend(true);
        adhocChart.setType(AdhocChartType.BUBBLE);
        adhocChart.setXValue("field2");
        serie = new AdhocChartSerie();
        serie.setYValue("field1");
        serie.setZValue("field2");
        serie.setSeries("field5");
        adhocChart.addSerie(serie);
        adhocChart.addSeriesColor(Color.LIGHT_GRAY);
        axisFormat = new AdhocAxisFormat();
        axisFormat.setLabel("label");
        axisFormat.setLabelFont(new AdhocFont());
        axisFormat.setLabelColor(Color.BLUE);
        adhocChart.setXAxisFormat(axisFormat);
        adhocChart.setYAxisFormat(axisFormat);
        adhocChart.setOrientation(AdhocOrientation.VERTICAL);
        adhocReport.addComponent(adhocChart);

        adhocChart = new AdhocChart();
        adhocChart.setKey("chart7");
        adhocChart.setTitle("title");
        adhocChart.setTitleFont(new AdhocFont());
        adhocChart.setTitleColor(Color.MAGENTA);
        adhocChart.setShowLegend(true);
        adhocChart.setType(AdhocChartType.GROUPEDSTACKEDBAR);
        adhocChart.setXValue("field2");
        serie = new AdhocChartSerie();
        serie.setYValue("field1");
        serie.setSeries("field5");
        serie.setProperty(AdhocProperties.CHART_SERIES_GROUP, "field6");
        serie.setLabel("label");
        adhocChart.addSerie(serie);
        adhocChart.addSeriesColor(Color.LIGHT_GRAY);
        axisFormat = new AdhocAxisFormat();
        axisFormat.setLabel("label");
        axisFormat.setLabelFont(new AdhocFont());
        axisFormat.setLabelColor(Color.BLUE);
        adhocChart.setXAxisFormat(axisFormat);
        adhocChart.setYAxisFormat(axisFormat);
        adhocChart.setOrientation(AdhocOrientation.VERTICAL);
        adhocChart.setProperty(AdhocProperties.CHART_USE_SERIES_AS_CATEGORY, true);
        adhocReport.addComponent(adhocChart);
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
        Assertions.assertTrue(adhocConfiguration.equals(adhocConfiguration));
        Assertions.assertTrue(adhocConfiguration.equals(adhocConfiguration2));
        final AdhocColumn adhocColumn = new AdhocColumn();
        adhocColumn.setName("field3");
        adhocConfiguration2.getReport().addColumn(adhocColumn);
        Assertions.assertFalse(adhocConfiguration.equals(adhocConfiguration2));
        final AdhocConfiguration adhocConfiguration3 = adhocConfiguration2.clone();
        Assertions.assertTrue(adhocConfiguration2.equals(adhocConfiguration3));
        adhocConfiguration3.getReport().getColumns().get(0).setName("c");
        Assertions.assertFalse(adhocConfiguration2.equals(adhocConfiguration3));
    }

    private void testConfiguration(final AdhocConfiguration adhocConfiguration) {
        final ReportCustomizer customizer = new ReportCustomizer();
        try {
            adhocManager.createReport(adhocConfiguration.getReport(), customizer);
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }

        DRChart chart = (DRChart) customizer.getComponents().get(0).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(0), adhocConfiguration.getReport().getComponent("chart1"));
        Assertions.assertEquals(ChartType.AREA, chart.getChartType());
        Assertions.assertNotNull(chart.getTitle().getTitle());
        Assertions.assertNotNull(chart.getTitle().getFont());
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor());
        Assertions.assertTrue(chart.getLegend().getShowLegend());
        Assertions.assertNotNull(((DRCategoryDataset) chart.getDataset()).getValueExpression());
        DRIChartSerie chartSerie = ((DRCategoryDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression());
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getValueExpression());
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getLabelExpression());
        Assertions.assertEquals(Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0));
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getYAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont());
        Assertions.assertEquals(Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor());
        Assertions.assertEquals(Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation());
        Assertions.assertTrue(((DRAxisPlot) chart.getPlot()).getShowValues());
        Assertions.assertTrue(((DRAxisPlot) chart.getPlot()).getShowPercentages());
        Assertions.assertTrue(((DRCategoryDataset) chart.getDataset()).getUseSeriesAsCategory());

        chart = (DRChart) customizer.getComponents().get(1).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(1), adhocConfiguration.getReport().getComponent("chart2"));
        Assertions.assertEquals(ChartType.TIMESERIES, chart.getChartType());
        Assertions.assertNotNull(chart.getTitle().getTitle());
        Assertions.assertNotNull(chart.getTitle().getFont());
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor());
        Assertions.assertTrue(chart.getLegend().getShowLegend());
        Assertions.assertNotNull(((DRTimeSeriesDataset) chart.getDataset()).getValueExpression());
        chartSerie = ((DRTimeSeriesDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression());
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getValueExpression());
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getLabelExpression());
        Assertions.assertEquals(Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0));
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getYAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont());
        Assertions.assertEquals(Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor());
        Assertions.assertEquals(Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation());
        Assertions.assertEquals(TimePeriod.MONTH, ((DRTimeSeriesDataset) chart.getDataset()).getTimePeriodType());

        chart = (DRChart) customizer.getComponents().get(2).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(2), adhocConfiguration.getReport().getComponent("chart3"));
        Assertions.assertEquals(ChartType.PIE, chart.getChartType());
        Assertions.assertNotNull(chart.getTitle().getTitle());
        Assertions.assertNotNull(chart.getTitle().getFont());
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor());
        Assertions.assertTrue(chart.getLegend().getShowLegend());
        Assertions.assertNotNull(((DRSeriesDataset) chart.getDataset()).getValueExpression());
        chartSerie = ((DRSeriesDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression());
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getValueExpression());
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getLabelExpression());

        chart = (DRChart) customizer.getComponents().get(3).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(3), adhocConfiguration.getReport().getComponent("chart4"));
        Assertions.assertEquals(ChartType.XYBAR, chart.getChartType());
        Assertions.assertNotNull(chart.getTitle().getTitle());
        Assertions.assertNotNull(chart.getTitle().getFont());
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor());
        Assertions.assertTrue(chart.getLegend().getShowLegend());
        Assertions.assertNotNull(((DRSeriesDataset) chart.getDataset()).getValueExpression());
        chartSerie = ((DRSeriesDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression());
        Assertions.assertNotNull(((DRIXyChartSerie) chartSerie).getYValueExpression());
        Assertions.assertNotNull(((DRIXyChartSerie) chartSerie).getLabelExpression());
        Assertions.assertEquals(Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0));
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getYAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont());
        Assertions.assertEquals(Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor());
        Assertions.assertEquals(Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation());

        chart = (DRChart) customizer.getComponents().get(4).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(4), adhocConfiguration.getReport().getComponent("chart5"));
        Assertions.assertEquals(ChartType.SPIDER, chart.getChartType());
        Assertions.assertNotNull(chart.getTitle().getTitle());
        Assertions.assertNotNull(chart.getTitle().getFont());
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor());
        Assertions.assertTrue(chart.getLegend().getShowLegend());
        Assertions.assertNotNull(((DRCategoryDataset) chart.getDataset()).getValueExpression());
        chartSerie = ((DRCategoryDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression());
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getValueExpression());
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getLabelExpression());

        chart = (DRChart) customizer.getComponents().get(5).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(5), adhocConfiguration.getReport().getComponent("chart6"));
        Assertions.assertEquals(ChartType.BUBBLE, chart.getChartType());
        Assertions.assertNotNull(chart.getTitle().getTitle());
        Assertions.assertNotNull(chart.getTitle().getFont());
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor());
        Assertions.assertTrue(chart.getLegend().getShowLegend());
        Assertions.assertNotNull(((DRSeriesDataset) chart.getDataset()).getValueExpression());
        chartSerie = ((DRSeriesDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression());
        Assertions.assertNotNull(((DRIXyzChartSerie) chartSerie).getYValueExpression());
        Assertions.assertNotNull(((DRIXyzChartSerie) chartSerie).getZValueExpression());
        Assertions.assertEquals(Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0));
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getYAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont());
        Assertions.assertEquals(Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor());
        Assertions.assertEquals(Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation());

        chart = (DRChart) customizer.getComponents().get(6).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(6), adhocConfiguration.getReport().getComponent("chart7"));
        Assertions.assertEquals(ChartType.GROUPEDSTACKEDBAR, chart.getChartType());
        Assertions.assertNotNull(chart.getTitle().getTitle());
        Assertions.assertNotNull(chart.getTitle().getFont());
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor());
        Assertions.assertTrue(chart.getLegend().getShowLegend());
        Assertions.assertNotNull(((DRCategoryDataset) chart.getDataset()).getValueExpression());
        chartSerie = ((DRCategoryDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression());
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getValueExpression());
        Assertions.assertNotNull(((DRIGroupedCategoryChartSerie) chartSerie).getGroupExpression());
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getLabelExpression());
        Assertions.assertEquals(Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0));
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getYAxisFormat());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression());
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont());
        Assertions.assertEquals(Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor());
        Assertions.assertEquals(Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation());
        Assertions.assertTrue(((DRCategoryDataset) chart.getDataset()).getUseSeriesAsCategory());
    }

    private class ReportCustomizer extends DefaultAdhocReportCustomizer {

        private List<ComponentBuilder<?, ?>> getComponents() {
            return new ArrayList<ComponentBuilder<?, ?>>(components.values());
        }
    }
}
