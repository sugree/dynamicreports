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
package net.sf.dynamicreports.report.builder.component;

import net.sf.dynamicreports.report.base.component.DRXyListCell;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>XyListCellBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class XyListCellBuilder extends AbstractBuilder<XyListCellBuilder, DRXyListCell> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for XyListCellBuilder.</p>
     *
     * @param x         a {@link java.lang.Integer} object.
     * @param y         a {@link java.lang.Integer} object.
     * @param component a {@link net.sf.dynamicreports.report.builder.component.ComponentBuilder} object.
     */
    protected XyListCellBuilder(Integer x, Integer y, ComponentBuilder<?, ?> component) {
        super(new DRXyListCell(x, y, component.build()));
    }

    /**
     * <p>Constructor for XyListCellBuilder.</p>
     *
     * @param x         a {@link java.lang.Integer} object.
     * @param y         a {@link java.lang.Integer} object.
     * @param width     a {@link java.lang.Integer} object.
     * @param height    a {@link java.lang.Integer} object.
     * @param component a {@link net.sf.dynamicreports.report.builder.component.ComponentBuilder} object.
     */
    protected XyListCellBuilder(Integer x, Integer y, Integer width, Integer height, ComponentBuilder<?, ?> component) {
        super(new DRXyListCell(x, y, component.build()));
        if (component instanceof DimensionComponentBuilder) {
            ((DimensionComponentBuilder<?, ?>) component).setWidth(width);
            ((DimensionComponentBuilder<?, ?>) component).setHeight(height);
        }
    }

    /**
     * <p>getXyListCell.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.base.component.DRXyListCell} object.
     */
    public DRXyListCell getXyListCell() {
        return build();
    }
}
