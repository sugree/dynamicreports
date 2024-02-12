package net.sf.dynamicreports.adhoc.test;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.AdhocToXmlTransformDecorator;
import net.sf.dynamicreports.adhoc.XmlToAdhocTransformDecorator;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.exception.ConfigurationMarshallerException;
import net.sf.dynamicreports.adhoc.transformation.AdhocToXmlTransform;
import net.sf.dynamicreports.adhoc.transformation.XmlToAdhocTransform;
import net.sf.dynamicreports.report.exception.DRException;

public class AdhocExceptionsTests
{

    private final AdhocManager adhocMan = AdhocManager.getInstance(
                    new AdhocToXmlTransformDecorator(new AdhocToXmlTransform()),
                    new XmlToAdhocTransformDecorator(new XmlToAdhocTransform()));
    // TODO IXmlToAdhocTransform interface
    // TODO XmlToAdhocTransformDecorator
    // TODO call ConfigurationMarshallerException from
    // XmlToAdhocTransformDecorator for null pointers

    @Test
    public void libraryCannotTransformNull()
        throws DRException
    {
        Assertions.assertThrows(ConfigurationMarshallerException.class, () -> {
            final AdhocConfiguration configuration = new AdhocConfiguration();
            final AdhocReport adhocReport = new AdhocReport();
            adhocReport.setProperty("Test_Property", "Test_Property_01");
            configuration.setReport(adhocReport);
            configuration.setFilter(null); // The catch is that java makes this
                                           // possible, we don't want this
                                           // possible

            adhocMan.saveConfiguration(configuration, new ByteArrayOutputStream());
        });

    }

    @Test
    public void libraryCannotUnMarshallNull()
        throws DRException
    {

        Assertions.assertThrows(ConfigurationMarshallerException.class, () -> {
            adhocMan.loadConfiguration(null);
        });

    }
}
