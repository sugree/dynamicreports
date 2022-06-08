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

import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.report.base.chart.DRChart;
import net.sf.dynamicreports.report.base.chart.dataset.DRCategoryDataset;
import net.sf.dynamicreports.report.base.chart.dataset.DRSeriesDataset;
import net.sf.dynamicreports.report.base.chart.dataset.DRTimeSeriesDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRAxisPlot;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.definition.chart.dataset.DRICategoryChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIXyChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIXyzChartSerie;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca
 */
public class AdhocChartLoadTest extends AdhocTests {

    @Test
    public void test() {
        try {
            final InputStream is = AdhocChartLoadTest.class.getResourceAsStream("adhocconfiguration3.xml");
            final AdhocConfiguration adhocConfiguration = adhocManager.loadConfiguration(is);
            testConfiguration(adhocConfiguration);
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
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
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(0), adhocConfiguration.getReport().getComponent("chart1"), "component key");
        Assertions.assertEquals(ChartType.AREA, chart.getChartType(), "chart type");
        Assertions.assertNotNull(chart.getTitle().getTitle(), "chart title");
        Assertions.assertNotNull(chart.getTitle().getFont(), "chart title font");
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor(), "chart title color");
        Assertions.assertTrue(chart.getLegend().getShowLegend(), "chart show legend");
        Assertions.assertNotNull(((DRCategoryDataset) chart.getDataset()).getValueExpression(), "chart category");
        DRIChartSerie chartSerie = ((DRCategoryDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression(), "chart serie series");
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getValueExpression(), "chart serie value");
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getLabelExpression(), "chart serie label");
        Assertions.assertEquals(Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0), "chart series color");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat(), "chart category axis format");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getYAxisFormat(), "chart value axis format");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression(), "axis format label");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont(), "axis format label font");
        Assertions.assertEquals(Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor(), "axis format label color");
        Assertions.assertEquals(Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation(), "chart orientation");
        Assertions.assertTrue(((DRCategoryDataset) chart.getDataset()).getUseSeriesAsCategory(), "chart use series as category");

        chart = (DRChart) customizer.getComponents().get(1).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(1), adhocConfiguration.getReport().getComponent("chart2"), "component key");
        Assertions.assertEquals(ChartType.TIMESERIES, chart.getChartType(), "chart type");
        Assertions.assertNotNull(chart.getTitle().getTitle(), "chart title");
        Assertions.assertNotNull(chart.getTitle().getFont(), "chart title font");
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor(), "chart title color");
        Assertions.assertTrue(chart.getLegend().getShowLegend(), "chart show legend");
        Assertions.assertNotNull(((DRTimeSeriesDataset) chart.getDataset()).getValueExpression(), "chart time value");
        chartSerie = ((DRTimeSeriesDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression(), "chart serie series");
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getValueExpression(), "chart serie value");
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getLabelExpression(), "chart serie label");
        Assertions.assertEquals(Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0), "chart series color");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat(), "chart category axis format");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getYAxisFormat(), "chart value axis format");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression(), "axis format label");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont(), "axis format label font");
        Assertions.assertEquals(Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor(), "axis format label color");
        Assertions.assertEquals(Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation(), "chart orientation");

        chart = (DRChart) customizer.getComponents().get(2).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(2), adhocConfiguration.getReport().getComponent("chart3"), "component key");
        Assertions.assertEquals(ChartType.PIE, chart.getChartType(), "chart type");
        Assertions.assertNotNull(chart.getTitle().getTitle(), "chart title");
        Assertions.assertNotNull(chart.getTitle().getFont(), "chart title font");
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor(), "chart title color");
        Assertions.assertTrue(chart.getLegend().getShowLegend(), "chart show legend");
        Assertions.assertNotNull(((DRSeriesDataset) chart.getDataset()).getValueExpression(), "chart key value");
        chartSerie = ((DRSeriesDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression(), "chart serie series");
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getValueExpression(),"chart serie value");
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getLabelExpression(), "chart serie label");

        chart = (DRChart) customizer.getComponents().get(3).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(3), adhocConfiguration.getReport().getComponent("chart4"), "component key");
        Assertions.assertEquals(ChartType.XYBAR, chart.getChartType(), "chart type");
        Assertions.assertNotNull(chart.getTitle().getTitle(), "chart title");
        Assertions.assertNotNull(chart.getTitle().getFont(), "chart title font");
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor(), "chart title color");
        Assertions.assertTrue(chart.getLegend().getShowLegend(), "chart show legend");
        Assertions.assertNotNull(((DRSeriesDataset) chart.getDataset()).getValueExpression(), "chart time value");
        chartSerie = ((DRSeriesDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression(), "chart serie series");
        Assertions.assertNotNull(((DRIXyChartSerie) chartSerie).getYValueExpression(), "chart serie value");
        Assertions.assertNotNull(((DRIXyChartSerie) chartSerie).getLabelExpression(), "chart serie label");
        Assertions.assertEquals(Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0), "chart series color");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat(), "chart category axis format");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getYAxisFormat(), "chart value axis format");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression(), "axis format label");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont(), "axis format label font");
        Assertions.assertEquals(Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor(), "axis format label color");
        Assertions.assertEquals(Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation(),"chart orientation");

        chart = (DRChart) customizer.getComponents().get(4).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(4), adhocConfiguration.getReport().getComponent("chart5"),"component key");
        Assertions.assertEquals(ChartType.SPIDER, chart.getChartType(), "chart type");
        Assertions.assertNotNull(chart.getTitle().getTitle(), "chart title");
        Assertions.assertNotNull(chart.getTitle().getFont(), "chart title font");
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor(), "chart title color");
        Assertions.assertTrue(chart.getLegend().getShowLegend(), "chart show legend");
        Assertions.assertNotNull(((DRCategoryDataset) chart.getDataset()).getValueExpression(), "chart time value");
        chartSerie = ((DRCategoryDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression(), "chart serie series");
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getValueExpression(), "chart serie value");
        Assertions.assertNotNull(((DRICategoryChartSerie) chartSerie).getLabelExpression(), "chart serie label");

        chart = (DRChart) customizer.getComponents().get(5).getComponent();
        Assertions.assertEquals(adhocConfiguration.getReport().getComponents().get(5), adhocConfiguration.getReport().getComponent("chart6"), "component key");
        Assertions.assertEquals(ChartType.BUBBLE, chart.getChartType(), "chart type");
        Assertions.assertNotNull(chart.getTitle().getTitle(), "chart title");
        Assertions.assertNotNull(chart.getTitle().getFont(), "chart title font");
        Assertions.assertEquals(Color.MAGENTA, chart.getTitle().getColor(), "chart title color");
        Assertions.assertTrue(chart.getLegend().getShowLegend(), "chart show legend");
        Assertions.assertNotNull(((DRSeriesDataset) chart.getDataset()).getValueExpression(), "chart time value");
        chartSerie = ((DRSeriesDataset) chart.getDataset()).getSeries().get(0);
        Assertions.assertNotNull(chartSerie.getSeriesExpression(), "chart serie series");
        Assertions.assertNotNull(((DRIXyzChartSerie) chartSerie).getYValueExpression(), "chart serie value");
        Assertions.assertNotNull(((DRIXyzChartSerie) chartSerie).getZValueExpression(), "chart serie label");
        Assertions.assertEquals(Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0), "chart series color");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat(), "chart category axis format");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getYAxisFormat(), "chart value axis format");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression(), "axis format label");
        Assertions.assertNotNull(((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont(), "axis format label font");
        Assertions.assertEquals(Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor(), "axis format label color");
        Assertions.assertEquals(Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation(), "chart orientation");
    }

    private class ReportCustomizer extends DefaultAdhocReportCustomizer {

        private List<ComponentBuilder<?, ?>> getComponents() {
            return new ArrayList<ComponentBuilder<?, ?>>(components.values());
        }
    }
}
