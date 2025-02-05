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
package net.sf.dynamicreports.report.definition.barcode;

/**
 * <p>DRIPdf417Barcode interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIPdf417Barcode extends DRIBarcode4j {

    /**
     * <p>getMinColumns.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getMinColumns();

    /**
     * <p>getMaxColumns.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getMaxColumns();

    /**
     * <p>getMinRows.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getMinRows();

    /**
     * <p>getMaxRows.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getMaxRows();

    /**
     * <p>getWidthToHeightRatio.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getWidthToHeightRatio();

    /**
     * <p>getErrorCorrectionLevel.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getErrorCorrectionLevel();
}
