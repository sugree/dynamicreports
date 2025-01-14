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
package net.sf.dynamicreports.test.jasper;

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
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractJasperTest {
    private JasperReportBuilder reportBuilder;
    private JasperReport jasperReport;
    private JasperPrint jasperPrint;

    @BeforeAll
    public void init() {
        try {
            reportBuilder = DynamicReports.report();
            configureReport(reportBuilder);
            if (serializableTest()) {
                reportBuilder = serializableReportTest(reportBuilder);
                serializableParametersTest(reportBuilder.getJasperParameters());
            }
            final JRDataSource dataSource = createDataSource();
            if (dataSource != null) {
                reportBuilder.setDataSource(dataSource);
            }
            build();
            if (serializableJrPrintTest()) {
                jasperPrint = serializableTest(jasperPrint);
            }
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

    protected boolean serializableJrPrintTest() {
        return true;
    }

    private JasperReportBuilder serializableReportTest(final JasperReportBuilder report) throws IOException, ClassNotFoundException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(report);
        oos.flush();
        oos.close();

        final InputStream stream = new ByteArrayInputStream(bos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(stream);
        return (JasperReportBuilder) ois.readObject();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> serializableParametersTest(final Map<String, Object> parameters) throws IOException, ClassNotFoundException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(parameters);
        oos.flush();
        oos.close();

        final InputStream stream = new ByteArrayInputStream(bos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(stream);
        return (Map<String, Object>) ois.readObject();
    }

    private JasperPrint serializableTest(final JasperPrint jasperPrint) throws IOException, JRException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JRSaver.saveObject(jasperPrint, bos);
        bos.flush();
        bos.close();

        final InputStream stream = new ByteArrayInputStream(bos.toByteArray());
        return (JasperPrint) JRLoader.loadObject(stream);
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

    protected void findElement(final String key, final List<JRPrintElement> elements, final JRPrintElement element) {
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

    protected void containsElement(final String key, final int pageIndex) {
        final List<JRPrintElement> elements = new ArrayList<JRPrintElement>();
        final JRPrintPage page = getJasperPrint().getPages().get(pageIndex);
        for (
            final Iterator<?> iterator = page.getElements().iterator(); iterator.hasNext(); ) {
            final JRPrintElement element = (JRPrintElement) iterator.next();
            findElement(key, elements, element);
        }
        if (elements.isEmpty()) {
            Assertions.fail("Element " + key + " at page index " + pageIndex + " not found");
        }
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

    protected abstract void configureReport(JasperReportBuilder rb) throws DRException;
}
