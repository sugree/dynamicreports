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
package net.sf.dynamicreports.googlecharts.jasper.geomap;

import net.sf.dynamicreports.googlecharts.report.geomap.GeoMapDataMode;
import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.export.GenericElementHtmlHandler;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterContext;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.util.JRColorUtil;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.HtmlExporterConfiguration;
import net.sf.jasperreports.export.HtmlExporterOutput;
import net.sf.jasperreports.export.HtmlReportConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.awt.Color;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * <p>GeoMapElementHtmlHandler class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class GeoMapElementHtmlHandler implements GenericElementHtmlHandler {

    private static final String GEOMAP_ELEMENT_HTML_TEMPLATE = "net/sf/dynamicreports/googlecharts/jasper/geomap/GeoMapElementHtmlTemplate.vm";
    private static final VelocityEngine VELOCITY_ENGINE;

    static {
        Properties properties = new Properties();
        properties.setProperty(RuntimeConstants.RESOURCE_LOADERS, "classpath");
        properties.setProperty("resource.loader.classpath.class", ClasspathResourceLoader.class.getName());
        VELOCITY_ENGINE = new VelocityEngine(properties);
        VELOCITY_ENGINE.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHtmlFragment(JRHtmlExporterContext context, JRGenericPrintElement element) {
        Boolean showLegend = (Boolean) element.getParameterValue(GeoMapPrintElement.PARAMETER_SHOW_LEGEND);
        GeoMapDataMode dataMode = (GeoMapDataMode) element.getParameterValue(GeoMapPrintElement.PARAMETER_DATA_MODE);
        String region = (String) element.getParameterValue(GeoMapPrintElement.PARAMETER_REGION);
        String valueLabel = (String) element.getParameterValue(GeoMapPrintElement.PARAMETER_VALUE_LABEL);
        if (valueLabel == null) {
            valueLabel = "";
        }
        @SuppressWarnings("unchecked") List<Color> colors = (List<Color>) element.getParameterValue(GeoMapPrintElement.PARAMETER_COLORS);
        StringBuilder stringColors = null;
        if (colors != null && !colors.isEmpty()) {
            stringColors = new StringBuilder();
            for (final Color color : colors) {
                stringColors.append(",").append(getColorString(color));
            }
            stringColors = new StringBuilder(StringUtils.removeStart(stringColors.toString(), ","));
        }
        @SuppressWarnings("unchecked") Set<GeoMapData> dataset = (Set<GeoMapData>) element.getParameterValue(GeoMapPrintElement.PARAMETER_DATASET);

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("showLegend", showLegend);
        if (dataMode != null) {
            switch (dataMode) {
                case REGIONS:
                    velocityContext.put("dataMode", "regions");
                    break;
                case MARKERS:
                    velocityContext.put("dataMode", "markers");
                    break;
                default:
                    break;
            }
        }
        velocityContext.put("id", "map_" + element.hashCode());
        velocityContext.put("region", region);
        velocityContext.put("valueLabel", valueLabel);
        velocityContext.put("colors", stringColors == null ? null : stringColors.toString());
        velocityContext.put("dataset", dataset);
        @SuppressWarnings("unchecked") Exporter<ExporterInput, ? extends HtmlReportConfiguration, ? extends HtmlExporterConfiguration, HtmlExporterOutput> exporter = context.getExporterRef();
        HtmlExporter htmlExporter = exporter instanceof HtmlExporter ? (HtmlExporter) exporter : null;
        if (htmlExporter != null) {
            velocityContext.put("elementX", htmlExporter.toSizeUnit((float) element.getX()));
            velocityContext.put("elementY", htmlExporter.toSizeUnit((float) element.getY()));
        } else {
            velocityContext.put("elementX", element.getX() + "px");
            velocityContext.put("elementY", element.getY() + "px");
        }
        velocityContext.put("elementWidth", element.getWidth());
        velocityContext.put("elementHeight", element.getHeight());

        if (element.getMode() == ModeEnum.OPAQUE) {
            velocityContext.put("backgroundColor", JRColorUtil.getColorHexa(element.getBackcolor()));
        }

        StringWriter writer = new StringWriter();
        try {
            VELOCITY_ENGINE.mergeTemplate(GEOMAP_ELEMENT_HTML_TEMPLATE, "UTF-8", velocityContext, writer);
        } catch (Exception e) {
            throw new JRRuntimeException(e);
        }
        return writer.toString();
    }

    private String getColorString(Color color) {
        int colorMask = Integer.parseInt("FFFFFF", 16);
        String hex = Integer.toHexString(color.getRGB() & colorMask).toUpperCase();
        return "0x" + ("000000" + hex).substring(hex.length());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toExport(JRGenericPrintElement element) {
        return true;
    }
}
