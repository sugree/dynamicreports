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

import net.sf.dynamicreports.report.constant.AxisPosition;
import net.sf.dynamicreports.report.definition.chart.DRIChart;

import java.io.Serializable;

/**
 * <p>DRIChartAxis interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIChartAxis extends Serializable {

    /**
     * <p>getPosition.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.constant.AxisPosition} object.
     */
    public AxisPosition getPosition();

    /**
     * <p>getChart.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.chart.DRIChart} object.
     */
    public DRIChart getChart();
}
