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
package net.sf.dynamicreports.test.design;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.design.base.DRDesignBand;
import net.sf.dynamicreports.design.base.DRDesignReport;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca
 */
public abstract class AbstractBandTest {

    @Test
    public void test() {
        final ReportBuilder<?> rb = new DesignReportBuilder();
        configureReport(rb);
        try {
            final DRDesignReport report = new DRDesignReport(rb.getReport());
            titleBandTest(report.getTitleBand());
            pageHeaderBandTest(report.getPageHeaderBand());
            pageFooterBandTest(report.getPageFooterBand());
            columnHeaderBandTest(report.getColumnHeaderBand());
            columnFooterBandTest(report.getColumnFooterBand());
            for (final DRDesignBand designBand : report.getDetailBands()) {
                detailBandTest(designBand);
            }
            lastPageFooterBandTest(report.getLastPageFooterBand());
            summaryBandTest(report.getSummaryBand());
            noDataBandTest(report.getNoDataBand());
            backgroundBandTest(report.getBackgroundBand());
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    protected void titleBandTest(final DRDesignBand band) {
        Assertions.assertNull(band);
    }

    protected void pageHeaderBandTest(final DRDesignBand band) {
        Assertions.assertNull(band);
    }

    protected void pageFooterBandTest(final DRDesignBand band) {
        Assertions.assertNull(band);
    }

    protected void columnHeaderBandTest(final DRDesignBand band) {
        Assertions.assertNull(band);
    }

    protected void columnFooterBandTest(final DRDesignBand band) {
        Assertions.assertNull(band);
    }

    protected void detailBandTest(final DRDesignBand band) {
        Assertions.assertNull(band);
    }

    protected void lastPageFooterBandTest(final DRDesignBand band) {
        Assertions.assertNull(band);
    }

    protected void summaryBandTest(final DRDesignBand band) {
        Assertions.assertNull(band);
    }

    protected void noDataBandTest(final DRDesignBand band) {
        Assertions.assertNull(band);
    }

    protected void backgroundBandTest(final DRDesignBand band) {
        Assertions.assertNull(band);
    }

    protected void componentPositionTest(final DRDesignComponent component, final int x, final int y, final int width, final int height) {
        Assertions.assertNotNull(component.getWidth(), "width");
        Assertions.assertEquals(Integer.valueOf(width), component.getWidth(), "width");
        Assertions.assertNotNull(component.getHeight(), "height");
        Assertions.assertEquals(Integer.valueOf(height), component.getHeight(), "height");
        Assertions.assertNotNull(component.getX(), "x");
        Assertions.assertEquals(Integer.valueOf(x), component.getX(), "x");
        Assertions.assertNotNull(component.getY(), "y");
        Assertions.assertEquals(Integer.valueOf(y), component.getY(), "y");
    }

    public abstract void configureReport(ReportBuilder<?> rb);
}
