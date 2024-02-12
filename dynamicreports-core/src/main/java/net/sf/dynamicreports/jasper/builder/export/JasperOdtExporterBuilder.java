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
package net.sf.dynamicreports.jasper.builder.export;

import net.sf.dynamicreports.jasper.base.export.JasperOdtExporter;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>JasperOdtExporterBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperOdtExporterBuilder extends AbstractJasperExporterBuilder<JasperOdtExporterBuilder, JasperOdtExporter> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for JasperOdtExporterBuilder.</p>
     */
    protected JasperOdtExporterBuilder() {
        super(new JasperOdtExporter());
    }

    /**
     * <p>setFlexibleRowHeight.</p>
     *
     * @param flexibleRowHeight a {@link java.lang.Boolean} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder} object.
     */
    public JasperOdtExporterBuilder setFlexibleRowHeight(Boolean flexibleRowHeight) {
        this.getObject().setFlexibleRowHeight(flexibleRowHeight);
        return this;
    }

    /**
     * <p>setIgnoreHyperLink.</p>
     *
     * @param ignoreHyperLink a {@link java.lang.Boolean} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder} object.
     */
    public JasperOdtExporterBuilder setIgnoreHyperLink(Boolean ignoreHyperLink) {
        this.getObject().setIgnoreHyperLink(ignoreHyperLink);
        return this;
    }
}
