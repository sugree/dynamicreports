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
package net.sf.dynamicreports.test.jasper.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintText;

/**
 * @author Ricardo Mariaca
 */
public class MultiPageList4Test extends AbstractJasperValueTest {
    private TextColumnBuilder<String> column1;

    @Override
    protected void configureReport(final JasperReportBuilder rb) {
        column1 = col.column("Column1", "field1", type.stringType());

        final MultiPageListBuilder multiPageList = cmp.multiPageList();
        multiPageList.setSplitType(SplitType.PREVENT);
        multiPageList.add(cmp.subreport(createSubreport()));
        multiPageList.add(cmp.verticalGap(730));
        multiPageList.add(cmp.subreport(createSubreport()));
        rb.title(multiPageList);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(2);

        int count = 0;
        for (final JRPrintElement element : getJasperPrint().getPages().get(0).getElements()) {
            if (element instanceof JRPrintText) {
                count++;
            }
        }
        Assertions.assertEquals(3, count, "MultipageList split type");
        count = 0;
        for (final JRPrintElement element : getJasperPrint().getPages().get(1).getElements()) {
            if (element instanceof JRPrintText) {
                count++;
            }
        }
        Assertions.assertEquals(3, count, "MultipageList split type");
    }

    private JasperReportBuilder createSubreport() {
        final JasperReportBuilder report = report();
        report.columns(column1).setDataSource(createSubreportDataSource());

        return report;
    }

    protected JRDataSource createSubreportDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1");
        dataSource.add("text");
        dataSource.add("text");
        return dataSource;
    }
}
