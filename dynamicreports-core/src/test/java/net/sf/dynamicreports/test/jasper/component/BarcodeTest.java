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

import static net.sf.dynamicreports.report.builder.DynamicReports.bcode;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.template;

import org.junit.jupiter.api.Assertions;
import org.krysalis.barcode4j.BaselineAlignment;
import org.krysalis.barcode4j.ChecksumMode;
import org.krysalis.barcode4j.impl.datamatrix.SymbolShapeHint;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.BarcodeBaselinePosition;
import net.sf.dynamicreports.report.constant.BarcodeChecksumMode;
import net.sf.dynamicreports.report.constant.BarcodeOrientation;
import net.sf.dynamicreports.report.constant.BarcodeShape;
import net.sf.dynamicreports.report.constant.BarcodeTextPosition;
import net.sf.dynamicreports.report.constant.QrCodeErrorCorrectionLevel;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.test.jasper.AbstractJasperTest;
import net.sf.jasperreports.components.barcode4j.Barcode4jComponent;
import net.sf.jasperreports.components.barcode4j.CodabarComponent;
import net.sf.jasperreports.components.barcode4j.Code128Component;
import net.sf.jasperreports.components.barcode4j.Code39Component;
import net.sf.jasperreports.components.barcode4j.DataMatrixComponent;
import net.sf.jasperreports.components.barcode4j.EAN128Component;
import net.sf.jasperreports.components.barcode4j.EAN13Component;
import net.sf.jasperreports.components.barcode4j.EAN8Component;
import net.sf.jasperreports.components.barcode4j.ErrorCorrectionLevelEnum;
import net.sf.jasperreports.components.barcode4j.Interleaved2Of5Component;
import net.sf.jasperreports.components.barcode4j.OrientationEnum;
import net.sf.jasperreports.components.barcode4j.PDF417Component;
import net.sf.jasperreports.components.barcode4j.POSTNETComponent;
import net.sf.jasperreports.components.barcode4j.QRCodeComponent;
import net.sf.jasperreports.components.barcode4j.RoyalMailCustomerComponent;
import net.sf.jasperreports.components.barcode4j.TextPositionEnum;
import net.sf.jasperreports.components.barcode4j.UPCAComponent;
import net.sf.jasperreports.components.barcode4j.UPCEComponent;
import net.sf.jasperreports.components.barcode4j.USPSIntelligentMailComponent;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.base.JRBaseComponentElement;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.renderers.SimpleRenderToImageAwareDataRenderer;

/**
 * @author Ricardo Mariaca
 */
public class BarcodeTest extends AbstractJasperTest {

    @Override
    protected void configureReport(final JasperReportBuilder rb) {
        rb.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL).setTemplate(template().setBarcodeHeight(20)).title(
            // codabar
            cmp.horizontalList(bcode.codabar("12345678"), bcode.codabar("12345678")
                                                               .setPattern("1")
                                                               .setModuleWidth(2d)
                                                               .setOrientation(BarcodeOrientation.LEFT)
                                                               .setTextPosition(BarcodeTextPosition.TOP)
                                                               .setQuietZone(100d)
                                                               .setVerticalQuietZone(50d)
                                                               .setWideFactor(2.5)),

            // code128
            cmp.horizontalList(bcode.code128("12345678"), bcode.code128("12345678")
                                                               .setPattern("1")
                                                               .setModuleWidth(2d)
                                                               .setOrientation(BarcodeOrientation.LEFT)
                                                               .setTextPosition(BarcodeTextPosition.TOP)
                                                               .setQuietZone(100d)
                                                               .setVerticalQuietZone(50d)),

            // ean128
            cmp.horizontalList(bcode.ean128("12345678"), bcode.ean128("12345678")
                                                              .setPattern("1")
                                                              .setModuleWidth(2d)
                                                              .setOrientation(BarcodeOrientation.LEFT)
                                                              .setTextPosition(BarcodeTextPosition.TOP)
                                                              .setQuietZone(100d)
                                                              .setVerticalQuietZone(50d)
                                                              .setChecksumMode(BarcodeChecksumMode.AUTO)),

            // dataMatrix
            cmp.horizontalList(bcode.dataMatrix("12345678"), bcode.dataMatrix("12345678")
                                                                  .setPattern("1")
                                                                  .setModuleWidth(2d)
                                                                  .setOrientation(BarcodeOrientation.LEFT)
                                                                  .setTextPosition(BarcodeTextPosition.TOP)
                                                                  .setQuietZone(100d)
                                                                  .setVerticalQuietZone(50d)
                                                                  .setShape(BarcodeShape.RECTANGLE)),

            // code39
            cmp.horizontalList(bcode.code39("12345678"), bcode.code39("12345678")
                                                              .setPattern("1")
                                                              .setModuleWidth(2d)
                                                              .setOrientation(BarcodeOrientation.LEFT)
                                                              .setTextPosition(BarcodeTextPosition.TOP)
                                                              .setQuietZone(100d)
                                                              .setVerticalQuietZone(50d)
                                                              .setChecksumMode(BarcodeChecksumMode.AUTO)
                                                              .setDisplayChecksum(true)
                                                              .setDisplayStartStop(true)
                                                              .setExtendedCharSetEnabled(true)
                                                              .setIntercharGapWidth(1.5)
                                                              .setWideFactor(2.5)),

            // interleaved2Of5
            cmp.horizontalList(bcode.interleaved2Of5("12345678"), bcode.interleaved2Of5("12345678")
                                                                       .setPattern("1")
                                                                       .setModuleWidth(2d)
                                                                       .setOrientation(BarcodeOrientation.LEFT)
                                                                       .setTextPosition(BarcodeTextPosition.TOP)
                                                                       .setQuietZone(100d)
                                                                       .setVerticalQuietZone(50d)
                                                                       .setChecksumMode(BarcodeChecksumMode.AUTO)
                                                                       .setDisplayChecksum(true)
                                                                       .setWideFactor(2.5)),

            // upca
            cmp.horizontalList(bcode.upca("11000000000"), bcode.upca("11000000000")
                                                               .setPattern("1")
                                                               .setModuleWidth(2d)
                                                               .setOrientation(BarcodeOrientation.LEFT)
                                                               .setTextPosition(BarcodeTextPosition.TOP)
                                                               .setQuietZone(100d)
                                                               .setVerticalQuietZone(50d)
                                                               .setChecksumMode(BarcodeChecksumMode.AUTO)),

            // upce
            cmp.horizontalList(bcode.upce("1100000"), bcode.upce("1100000")
                                                           .setPattern("1")
                                                           .setModuleWidth(2d)
                                                           .setOrientation(BarcodeOrientation.LEFT)
                                                           .setTextPosition(BarcodeTextPosition.TOP)
                                                           .setQuietZone(100d)
                                                           .setVerticalQuietZone(50d)
                                                           .setChecksumMode(BarcodeChecksumMode.AUTO)),

            // ean13
            cmp.horizontalList(bcode.ean13("110000000000"), bcode.ean13("110000000000")
                                                                 .setPattern("1")
                                                                 .setModuleWidth(2d)
                                                                 .setOrientation(BarcodeOrientation.LEFT)
                                                                 .setTextPosition(BarcodeTextPosition.TOP)
                                                                 .setQuietZone(100d)
                                                                 .setVerticalQuietZone(50d)
                                                                 .setChecksumMode(BarcodeChecksumMode.AUTO)),

            // ean8
            cmp.horizontalList(bcode.ean8("1100000"), bcode.ean8("1100000")
                                                           .setPattern("1")
                                                           .setModuleWidth(2d)
                                                           .setOrientation(BarcodeOrientation.LEFT)
                                                           .setTextPosition(BarcodeTextPosition.TOP)
                                                           .setQuietZone(100d)
                                                           .setVerticalQuietZone(50d)
                                                           .setChecksumMode(BarcodeChecksumMode.AUTO)),

            // uspsIntelligentMail
            cmp.horizontalList(bcode.uspsIntelligentMail("34160265194042788110"), bcode.uspsIntelligentMail("34160265194042788110")
                                                                                       .setPattern("1")
                                                                                       .setModuleWidth(2d)
                                                                                       .setOrientation(BarcodeOrientation.LEFT)
                                                                                       .setTextPosition(BarcodeTextPosition.TOP)
                                                                                       .setQuietZone(100d)
                                                                                       .setVerticalQuietZone(50d)
                                                                                       .setChecksumMode(BarcodeChecksumMode.AUTO)
                                                                                       .setAscenderHeight(2.6)
                                                                                       .setIntercharGapWidth(1.8)
                                                                                       .setTrackHeight(20.1)),

            // royalMailCustomer
            cmp.horizontalList(bcode.royalMailCustomer("34160265194042788110"), bcode.royalMailCustomer("34160265194042788110")
                                                                                     .setPattern("1")
                                                                                     .setModuleWidth(2d)
                                                                                     .setOrientation(BarcodeOrientation.LEFT)
                                                                                     .setQuietZone(100d)
                                                                                     .setVerticalQuietZone(50d)
                                                                                     .setChecksumMode(BarcodeChecksumMode.AUTO)
                                                                                     .setAscenderHeight(2.6)
                                                                                     .setIntercharGapWidth(1.8)
                                                                                     .setTrackHeight(20.1)),

            // postnet
            cmp.horizontalList(bcode.postnet("12345678"), bcode.postnet("12345678")
                                                               .setPattern("1")
                                                               .setModuleWidth(2d)
                                                               .setOrientation(BarcodeOrientation.LEFT)
                                                               .setTextPosition(BarcodeTextPosition.BOTTOM)
                                                               .setQuietZone(100d)
                                                               .setVerticalQuietZone(50d)
                                                               .setChecksumMode(BarcodeChecksumMode.AUTO)
                                                               .setDisplayChecksum(true)
                                                               .setShortBarHeight(2.5)
                                                               .setBaselinePosition(BarcodeBaselinePosition.TOP)
                                                               .setIntercharGapWidth(1.5)),

            // pdf417
            cmp.horizontalList(bcode.pdf417("12345678"), bcode.pdf417("12345678")
                                                              .setPattern("1")
                                                              .setModuleWidth(2d)
                                                              .setOrientation(BarcodeOrientation.LEFT)
                                                              .setTextPosition(BarcodeTextPosition.NONE)
                                                              .setQuietZone(100d)
                                                              .setVerticalQuietZone(50d)
                                                              .setMinColumns(2)
                                                              .setMaxColumns(30)
                                                              .setMinRows(3)
                                                              .setMaxRows(29)
                                                              .setWidthToHeightRatio(2.6)
                                                              .setErrorCorrectionLevel(8)),

            // qrcode
            bcode.qrCode("123456").setMargin(2).setErrorCorrectionLevel(QrCodeErrorCorrectionLevel.H));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        // codabar
        final CodabarComponent codabar = testBarcode("Codabar", CodabarComponent.class, TextPositionEnum.TOP);
        Assertions.assertEquals(Double.valueOf(2.5), codabar.getWideFactor(), "Codabar wide factor");
        testBarcode("Codabar", 2, 0, "12345678");
        testBarcode("Codabar", 2, 1, "112345678");

        // code128
        testBarcode("Code128", Code128Component.class, TextPositionEnum.TOP);
        testBarcode("Code128", 3, 0, "12345678");
        testBarcode("Code128", 3, 1, "112345678");

        // ean128
        final EAN128Component ean128 = testBarcode("EAN128", EAN128Component.class, TextPositionEnum.TOP);
        Assertions.assertEquals("EAN128 checksum mode", ChecksumMode.CP_AUTO.getName(), ean128.getChecksumMode());
        testBarcode("EAN128", 4, 0, "(12)345678");
        testBarcode("EAN128", 4, 1, "1(12)345678");

        // dataMatrix
        final DataMatrixComponent dataMatrix = testBarcode("DataMatrix", DataMatrixComponent.class, TextPositionEnum.TOP);
        Assertions.assertEquals("DataMatrix shape", SymbolShapeHint.FORCE_RECTANGLE.getName(), dataMatrix.getShape());

        // code39
        final Code39Component code39 = testBarcode("Code39", Code39Component.class, TextPositionEnum.TOP);
        Assertions.assertEquals(ChecksumMode.CP_AUTO.getName(), code39.getChecksumMode(), "Code39 checksum mode");
        Assertions.assertEquals(Boolean.TRUE, code39.isDisplayChecksum(), "Code39 display checksum");
        Assertions.assertEquals(Boolean.TRUE, code39.isDisplayStartStop(), "Code39 display start stop");
        Assertions.assertEquals(Boolean.TRUE, code39.isExtendedCharSetEnabled(), "Code39 extended charSet enabled");
        Assertions.assertEquals(Double.valueOf(1.5), code39.getIntercharGapWidth(), "Code39 interchar gap width");
        Assertions.assertEquals(Double.valueOf(2.5), code39.getWideFactor(), "Code39 wide factor");
        testBarcode("Code39", 6, 0, "12345678");
        testBarcode("Code39", 6, 1, "112345678");

        // interleaved2Of5
        final Interleaved2Of5Component interleaved2Of5 = testBarcode("Interleaved2Of5", Interleaved2Of5Component.class, TextPositionEnum.TOP);
        Assertions.assertEquals(ChecksumMode.CP_AUTO.getName(), interleaved2Of5.getChecksumMode(), "Interleaved2Of5 checksum mode");
        Assertions.assertEquals(Boolean.TRUE, interleaved2Of5.isDisplayChecksum(), "Interleaved2Of5 display checksum");
        Assertions.assertEquals(Double.valueOf(2.5), interleaved2Of5.getWideFactor(), "Interleaved2Of5 wide factor");
        testBarcode("Interleaved2Of5", 7, 0, "12345678");
        testBarcode("Interleaved2Of5", 7, 1, "112345678");

        // upca
        final UPCAComponent upca = testBarcode("UPCA", UPCAComponent.class, TextPositionEnum.TOP);
        Assertions.assertEquals("UPCA checksum mode", ChecksumMode.CP_AUTO.getName(), upca.getChecksumMode());
        testBarcode("UPCA", 8, 0, "1<.*>10000<.*>00000");
        testBarcode("UPCA", 8, 1, "1<.*>10000<.*>00000");

        // upce
        final UPCEComponent upce = testBarcode("UPCE", UPCEComponent.class, TextPositionEnum.TOP);
        Assertions.assertEquals("UPCE checksum mode", ChecksumMode.CP_AUTO.getName(), upce.getChecksumMode());
        testBarcode("UPCE", 9, 0, "1<.*>100000");
        testBarcode("UPCE", 9, 1, "1<.*>100000");

        // ean13
        final EAN13Component ean13 = testBarcode("EAN13", EAN13Component.class, TextPositionEnum.TOP);
        Assertions.assertEquals("EAN13 checksum mode", ChecksumMode.CP_AUTO.getName(), ean13.getChecksumMode());
        testBarcode("EAN13", 10, 0, "1<.*>100000<.*>000006");
        testBarcode("EAN13", 10, 1, "1<.*>100000<.*>000006");

        // ean8
        final EAN8Component ean8 = testBarcode("EAN8", EAN8Component.class, TextPositionEnum.TOP);
        Assertions.assertEquals("EAN8 checksum mode", ChecksumMode.CP_AUTO.getName(), ean8.getChecksumMode());
        testBarcode("EAN8", 11, 0, "1100<.*>0006");
        testBarcode("EAN8", 11, 1, "1100<.*>0006");

        // uspsIntelligentMail
        final USPSIntelligentMailComponent uspsIntelligentMail = testBarcode("USPSIntelligentMail", USPSIntelligentMailComponent.class, TextPositionEnum.TOP);
        Assertions.assertEquals(ChecksumMode.CP_AUTO.getName(), uspsIntelligentMail.getChecksumMode(), "USPSIntelligentMail checksum mode");
        Assertions.assertEquals(Double.valueOf(2.6), uspsIntelligentMail.getAscenderHeight(), "USPSIntelligentMail ascender height");
        Assertions.assertEquals(Double.valueOf(1.8), uspsIntelligentMail.getIntercharGapWidth(), "USPSIntelligentMail interchar gap width");
        Assertions.assertEquals(Double.valueOf(20.1), uspsIntelligentMail.getTrackHeight(), "USPSIntelligentMail track height");

        // royalMailCustomer
        final RoyalMailCustomerComponent royalMailCustomer = testBarcode("RoyalMailCustomer", RoyalMailCustomerComponent.class, null);
        Assertions.assertEquals(ChecksumMode.CP_AUTO.getName(), royalMailCustomer.getChecksumMode(), "RoyalMailCustomer checksum mode");
        Assertions.assertEquals(Double.valueOf(2.6), royalMailCustomer.getAscenderHeight(), "RoyalMailCustomer ascender height");
        Assertions.assertEquals(Double.valueOf(1.8), royalMailCustomer.getIntercharGapWidth(), "RoyalMailCustomer interchar gap width");
        Assertions.assertEquals(Double.valueOf(20.1), royalMailCustomer.getTrackHeight(), "RoyalMailCustomer track height");

        // postnet
        final POSTNETComponent postnet = testBarcode("POSTNET", POSTNETComponent.class, TextPositionEnum.BOTTOM);
        Assertions.assertEquals(ChecksumMode.CP_AUTO.getName(), postnet.getChecksumMode(), "POSTNET checksum mode");
        Assertions.assertEquals(Boolean.TRUE, postnet.getDisplayChecksum(), "POSTNET display checksum");
        Assertions.assertEquals(Double.valueOf(1.5), postnet.getIntercharGapWidth(), "POSTNET interchar gap width");
        Assertions.assertEquals(Double.valueOf(2.5), postnet.getShortBarHeight(), "POSTNET short bar height");
        Assertions.assertEquals(BaselineAlignment.ALIGN_TOP.getName(), postnet.getBaselinePosition(), "POSTNET baseline position");
        Assertions.assertEquals(Double.valueOf(1.5), postnet.getIntercharGapWidth(), "POSTNET interchar gap width");

        // pdf417
        final PDF417Component pdf417 = testBarcode("PDF417", PDF417Component.class, TextPositionEnum.NONE);
        Assertions.assertEquals(Integer.valueOf(2), pdf417.getMinColumns(), "PDF417 min columns");
        Assertions.assertEquals(Integer.valueOf(30), pdf417.getMaxColumns(), "PDF417 max columns");
        Assertions.assertEquals(Integer.valueOf(3), pdf417.getMinRows(), "PDF417 min rows");
        Assertions.assertEquals(Integer.valueOf(29), pdf417.getMaxRows(), "PDF417 max rows");
        Assertions.assertEquals(Double.valueOf(2.6), pdf417.getWidthToHeightRatio(), "PDF417 width to height ratio");
        Assertions.assertEquals(Integer.valueOf(8), pdf417.getErrorCorrectionLevel(), "PDF417 error correction level");

        // qrCode
        testQrCode();
    }

    private <T extends Barcode4jComponent> T testBarcode(final String name, final Class<T> componentClass, final TextPositionEnum textPosition) {
        final JRBaseComponentElement barcode = (JRBaseComponentElement) getJasperReport().getTitle().getElementByKey("title." + name + "2");
        final Component component = barcode.getComponent();
        Assertions.assertTrue(component.getClass().equals(componentClass), "Barcode is not instance of " + componentClass.getName());
        @SuppressWarnings("unchecked") final T barcodeComponent = (T) component;

        Assertions.assertEquals(Double.valueOf(2), barcodeComponent.getModuleWidth(), name + " module width");
        Assertions.assertEquals(OrientationEnum.LEFT, barcodeComponent.getOrientationValue(), name + " orientation");
        Assertions.assertEquals(textPosition, barcodeComponent.getTextPositionValue(), name + " text position");
        Assertions.assertEquals(Double.valueOf(100), barcodeComponent.getQuietZone(), name + " quiet zone");
        Assertions.assertEquals(Double.valueOf(50), barcodeComponent.getVerticalQuietZone(), name + " vertical quiet zone");
        return barcodeComponent;
    }

    private void testBarcode(final String name, final int groupIndex, final int index, final String code) {
        final JRPrintFrame printFrame = (JRPrintFrame) getElementAt("title.list" + groupIndex, 0);
        final JRPrintImage image = (JRPrintImage) printFrame.getElements().get(index);
        final SimpleRenderToImageAwareDataRenderer renderer = (SimpleRenderToImageAwareDataRenderer) image.getRenderer();
        try {
            final String barcode = new String(renderer.getData(DefaultJasperReportsContext.getInstance()));
            Assertions.assertTrue(barcode.matches(".*>" + code.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)") + "<.*"), name + " code");
        } catch (final JRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    private void testQrCode() {
        final JRBaseComponentElement barcode = (JRBaseComponentElement) getJasperReport().getTitle().getElementByKey("title.QRCode1");
        final Component component = barcode.getComponent();
        Assertions.assertTrue(component.getClass().equals(QRCodeComponent.class), "QRCode is not instance of " + QRCodeComponent.class.getName());
        final QRCodeComponent qrComponentComponent = (QRCodeComponent) component;

        Assertions.assertEquals(Integer.valueOf(2), qrComponentComponent.getMargin(), "QRCode margin");
        Assertions.assertEquals(ErrorCorrectionLevelEnum.H, qrComponentComponent.getErrorCorrectionLevel(), "QRCode error correction level");
    }
}
