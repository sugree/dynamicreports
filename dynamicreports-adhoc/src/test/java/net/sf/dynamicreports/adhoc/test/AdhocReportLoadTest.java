/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
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
package net.sf.dynamicreports.adhoc.test;

import java.awt.Color;
import java.io.InputStream;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBoxPen;

/**
 * @author Ricardo Mariaca
 */
public class AdhocReportLoadTest extends AbstractJasperTest {

    private AdhocConfiguration adhocConfiguration;
    private String groupName;

    @Override
    public void init() {
        try {
            final InputStream is = AdhocConfigurationLoadTest.class.getResourceAsStream("adhocconfiguration2.xml");
            adhocConfiguration = adhocManager.loadConfiguration(is);
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }

        super.init();
    }

    @Override
    protected JasperReportBuilder createReport() throws DRException {
        final JasperReportBuilder report = adhocManager.createReport(adhocConfiguration.getReport());
        report.setLocale(Locale.ENGLISH);

        groupName = report.getReport().getGroups().get(0).getName();

        return report;
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
        dataSource.add("test", 1, toDate(2011, 1, 1), "test");
        return dataSource;
    }



    @Override
    @Test
    public void test() {

        numberOfPagesTest(1);

        elementValueTest("columnHeader.column_field1.title1", 0, "Column1 && C<a>aa</a> \"a\" 'c'");
        elementValueTest("columnHeader.column_field2.title1", 0, "Column2");

        elementValueTest("detail.column_field11", 0, "test");
        elementValueTest("detail.column_field21", 0, "1");
        //fixme remove comma introduced in java9 "Expected :1/1/11 12:00 AM", "Actual   :1/1/11, 12:00 AM"
        //elementValueTest("detail.column_field31", 0, new SimpleDateFormat("M/d/yy h:mm a", new DateFormatSymbols(Locale.ENGLISH)).format(toDate(2011, 1, 1)));

        elementValueTest("groupHeaderTitleAndValue.group_" + groupName + "1", "test");

        JRStyle style = getElementAt("columnHeader.column_field1.title1", 0).getStyle();
        final JRBoxPen pen = style.getLineBox().getLeftPen();
        Assertions.assertEquals(2f, pen.getLineWidth());
        style = getElementAt("detail.column_field11", 0).getStyle();
        Assertions.assertEquals(Color.BLUE, style.getForecolor(), "foreColor");
    }
}
