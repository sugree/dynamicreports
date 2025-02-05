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
package net.sf.dynamicreports.design.base.chart.dataset;

import net.sf.dynamicreports.design.definition.DRIDesignHyperLink;
import net.sf.dynamicreports.design.definition.chart.dataset.DRIDesignChartSerie;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>Abstract DRDesignChartSerie class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class DRDesignChartSerie implements DRIDesignChartSerie {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIDesignExpression seriesExpression;
    private DRIDesignHyperLink itemHyperLink;

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getSeriesExpression() {
        return seriesExpression;
    }

    /**
     * <p>Setter for the field <code>seriesExpression</code>.</p>
     *
     * @param seriesExpression a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setSeriesExpression(DRIDesignExpression seriesExpression) {
        this.seriesExpression = seriesExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignHyperLink getItemHyperLink() {
        return itemHyperLink;
    }

    /**
     * <p>Setter for the field <code>itemHyperLink</code>.</p>
     *
     * @param itemHyperLink a {@link net.sf.dynamicreports.design.definition.DRIDesignHyperLink} object.
     */
    public void setItemHyperLink(DRIDesignHyperLink itemHyperLink) {
        this.itemHyperLink = itemHyperLink;
    }

}
