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
package net.sf.dynamicreports.report.definition.chart.plot;

/**
 * <p>DRIPiePlot interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIPiePlot extends DRIBasePlot {

    /**
     * <p>getCircular.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCircular();

    /**
     * <p>getShowLabels.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getShowLabels();

    /**
     * <p>getShowValues.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getShowValues();

    /**
     * <p>getValuePattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getValuePattern();

    /**
     * <p>getShowPercentages.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getShowPercentages();

    /**
     * <p>getPercentValuePattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPercentValuePattern();

    /**
     * <p>getLabelFormat.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLabelFormat();

    /**
     * <p>getLegendLabelFormat.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLegendLabelFormat();
}
