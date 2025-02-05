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
package net.sf.dynamicreports.design.base.chart.plot;

import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignChartAxis;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignMultiAxisPlot;
import net.sf.dynamicreports.report.constant.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>DRDesignMultiAxisPlot class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignMultiAxisPlot extends DRDesignAxisPlot implements DRIDesignMultiAxisPlot {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private List<DRIDesignChartAxis> axes;

    /**
     * <p>Constructor for DRDesignMultiAxisPlot.</p>
     */
    public DRDesignMultiAxisPlot() {
        axes = new ArrayList<DRIDesignChartAxis>();
    }

    /** {@inheritDoc} */
    @Override
    public List<DRIDesignChartAxis> getAxes() {
        return axes;
    }

    /**
     * <p>Setter for the field <code>axes</code>.</p>
     *
     * @param axes a {@link java.util.List} object.
     */
    public void setAxes(List<DRIDesignChartAxis> axes) {
        this.axes = axes;
    }

}
