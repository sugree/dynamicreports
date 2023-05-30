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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRStyledTextUtil;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractJasperTest extends AdhocTests {
    private JasperReportBuilder reportBuilder;
    private JasperReport jasperReport;
    private JasperPrint jasperPrint;

    @BeforeAll
    public void init() {
        try {
            reportBuilder = createReport();
            if (serializableTest()) {
                reportBuilder = serializableTest(reportBuilder);
            }
            final JRDataSource dataSource = createDataSource();
            if (dataSource != null) {
                reportBuilder.setDataSource(dataSource);
            }
            build();
        } catch (final Exception e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    protected void build() throws DRException {
        jasperReport = reportBuilder.toJasperReport();
        jasperPrint = reportBuilder.toJasperPrint();
    }

    @Test
    public void test() {
    }

    protected boolean serializableTest() {
        return true;
    }

    private JasperReportBuilder serializableTest(final JasperReportBuilder report) throws IOException, ClassNotFoundException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(report);
        oos.flush();
        oos.close();

        final InputStream stream = new ByteArrayInputStream(bos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(stream);
        return (JasperReportBuilder) ois.readObject();
    }

    public JasperReportBuilder getReportBuilder() {
        return reportBuilder;
    }

    public JasperReport getJasperReport() {
        return jasperReport;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    protected void numberOfPagesTest(final int expectedNumberOfPages) {
        Assertions.assertEquals(expectedNumberOfPages, getNumberOfPages(), "pages");
    }

    private int getNumberOfPages() {
        return jasperPrint.getPages().size();
    }

    protected JRPrintElement getElementAt(final String key, final int index) {
        final List<JRPrintElement> elements = findElement(key);
        if (elements.size() - 1 < index) {
            Assertions.fail("Element " + key + " at index " + index + " not found");
            return null;
        }
        return elements.get(index);
    }

    protected List<JRPrintElement> findElement(final String key) {
        final List<JRPrintElement> elements = new ArrayList<JRPrintElement>();
        for (
            final Iterator<?> iterator = jasperPrint.getPages().iterator(); iterator.hasNext(); ) {
            final JRPrintPage page = (JRPrintPage) iterator.next();
            for (
                final Iterator<?> iterator2 = page.getElements().iterator(); iterator2.hasNext(); ) {
                final JRPrintElement element = (JRPrintElement) iterator2.next();
                findElement(key, elements, element);
            }
        }
        return elements;
    }

    private void findElement(final String key, final List<JRPrintElement> elements, final JRPrintElement element) {
        if (key.equals(element.getKey())) {
            elements.add(element);
        }
        if (element instanceof JRPrintFrame) {
            for (
                final Iterator<?> iterator = ((JRPrintFrame) element).getElements().iterator(); iterator.hasNext(); ) {
                final JRPrintElement element2 = (JRPrintElement) iterator.next();
                findElement(key, elements, element2);
            }
        }
    }

    protected void elementValueTest(final String name, final int index, final String value) {
        Assertions.assertEquals("element value " + name, value, getElementValue(name, index));
    }

    protected void elementValueTest(final String name, final String... values) {
        final List<JRPrintElement> elements = findElement(name);
        Assertions.assertTrue(values.length <= elements.size());
        for (int i = 0; i < values.length; i++) {
            final JRPrintText textElement = (JRPrintText) elements.get(i);
            final String value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(textElement);
            Assertions.assertEquals("element value " + name, values[i], value);
        }
    }

    private String getElementValue(final String key, final int index) {
        final JRPrintText textElement = (JRPrintText) getElementAt(key, index);
        final String value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(textElement);
        return value;
    }

    protected Date toDate(final int year, final int month, final int day) {
        final Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    protected JRDataSource createDataSource() {
        return null;
    }

    protected abstract JasperReportBuilder createReport() throws DRException;
}
