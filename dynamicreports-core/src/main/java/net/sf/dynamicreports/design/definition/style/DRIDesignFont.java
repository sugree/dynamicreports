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
package net.sf.dynamicreports.design.definition.style;

import java.io.Serializable;

/**
 * <p>DRIDesignFont interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignFont extends Serializable {

    /**
     * <p>getFontName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFontName();

    /**
     * <p>getBold.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getBold();

    /**
     * <p>getItalic.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getItalic();

    /**
     * <p>getUnderline.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getUnderline();

    /**
     * <p>getStrikeThrough.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getStrikeThrough();

    /**
     * <p>getFontSize.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getFontSize();

    /**
     * <p>getPdfFontName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPdfFontName();

    /**
     * <p>getPdfEncoding.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPdfEncoding();

    /**
     * <p>getPdfEmbedded.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getPdfEmbedded();
}
