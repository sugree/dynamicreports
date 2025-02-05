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
package net.sf.dynamicreports.design.transformation.chartcustomizer;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;

import java.io.Serializable;

/**
 * <p>XyStepRendererCustomizer class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class XyStepRendererCustomizer implements DRIChartCustomizer, Serializable {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Double stepPoint;

    /**
     * <p>Constructor for XyStepRendererCustomizer.</p>
     *
     * @param stepPoint a {@link java.lang.Double} object.
     */
    public XyStepRendererCustomizer(Double stepPoint) {
        this.stepPoint = stepPoint;
    }

    /** {@inheritDoc} */
    @Override
    public void customize(JFreeChart chart, ReportParameters reportParameters) {
        XYLineAndShapeRenderer lineRenderer = (XYLineAndShapeRenderer) chart.getXYPlot().getRenderer();
        XYStepRenderer renderer = new XYStepRenderer();

        renderer.setBaseItemLabelsVisible(lineRenderer.getBaseItemLabelsVisible());
        renderer.setBaseItemLabelFont(lineRenderer.getBaseItemLabelFont());
        renderer.setBaseItemLabelPaint(lineRenderer.getBaseItemLabelPaint());
        renderer.setBaseItemLabelGenerator(lineRenderer.getBaseItemLabelGenerator());
        renderer.setBaseShapesVisible(lineRenderer.getBaseShapesVisible());
        renderer.setBaseLinesVisible(lineRenderer.getBaseLinesVisible());

        if (stepPoint != null) {
            renderer.setStepPoint(stepPoint);
        }
        chart.getXYPlot().setRenderer(renderer);
    }
}
