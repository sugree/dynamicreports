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
package net.sf.dynamicreports.googlecharts.jasper;

import net.sf.jasperreports.engine.export.GenericElementHandler;
import net.sf.jasperreports.engine.export.GenericElementHandlerBundle;
import net.sf.jasperreports.engine.export.HtmlExporter;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>GoogleChartsHandler class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class GoogleChartsHandler implements GenericElementHandlerBundle {
    private Map<String, GenericElementHandler> handlers;

    /**
     * <p>Constructor for GoogleChartsHandler.</p>
     */
    public GoogleChartsHandler() {
        handlers = new HashMap<>();
    }

    /**
     * <p>add.</p>
     *
     * @param name    a {@link java.lang.String} object.
     * @param handler a {@link net.sf.jasperreports.engine.export.GenericElementHandler} object.
     */
    public void add(String name, GenericElementHandler handler) {
        handlers.put(name, handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamespace() {
        return GoogleChartsExtensionsRegistryFactory.NAMESPACE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericElementHandler getHandler(String elementName, String exporterKey) {
        if (handlers.containsKey(elementName)) {
            if (HtmlExporter.HTML_EXPORTER_KEY.equals(exporterKey)) {
                return handlers.get(elementName);
            }
        }
        return null;
    }
}
