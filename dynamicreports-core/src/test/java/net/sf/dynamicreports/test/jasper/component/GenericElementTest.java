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
import static net.sf.dynamicreports.report.builder.DynamicReports.export;
import static net.sf.dynamicreports.report.builder.DynamicReports.field;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder;
import net.sf.dynamicreports.report.base.AbstractScriptlet;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.GenericElementBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenericElementTest {
    private String data = "";
    private String output;

    @BeforeAll
    public void init() {
        try {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            final JasperHtmlExporterBuilder htmlExporter = export.htmlExporter(outputStream);

            final JasperReportBuilder reportBuilder = DynamicReports.report();
            configureReport(reportBuilder);
            reportBuilder.setDataSource(createDataSource()).toHtml(htmlExporter);

            output = outputStream.toString();
        } catch (final Exception e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    protected void configureReport(final JasperReportBuilder rb) {
        final GenericElementBuilder genericElement = cmp.genericElement("http://www.dynamicreports.org/custom", "custom").addParameter("id", "10").addParameter("data", new ParameterExpression());

        rb.scriptlets(new ReportScriptlet()).fields(field("field1", type.stringType())).summary(genericElement);
    }

    @Test
    public void test() {
        Assertions.assertTrue(output.indexOf("<div id=\"10\">ABCD</div>") != -1, "generic element output");
    }

    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1");
        dataSource.add("A");
        dataSource.add("B");
        dataSource.add("C");
        dataSource.add("D");
        return dataSource;
    }

    private class ParameterExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(final ReportParameters reportParameters) {
            return data;
        }
    }

    private class ReportScriptlet extends AbstractScriptlet {

        @Override
        public void afterDetailEval(final ReportParameters reportParameters) {
            super.afterDetailEval(reportParameters);
            final String name = reportParameters.getValue("field1");
            data += name;
        }
    }
}
