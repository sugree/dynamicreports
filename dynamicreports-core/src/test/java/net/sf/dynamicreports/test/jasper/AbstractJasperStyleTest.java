/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.test.jasper;

import java.awt.Color;

import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.group.GroupBuilder;
import net.sf.dynamicreports.report.builder.subtotal.SubtotalBuilder;
import net.sf.jasperreports.engine.JRImageAlignment;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTextAlignment;
import net.sf.jasperreports.engine.base.JRBoxPen;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

/**
 * Base class for jasper style tests.
 */
public abstract class AbstractJasperStyleTest extends AbstractJasperTest {

  protected static final String TEST_FONT_NAME = "Arimo";

  protected void styleTest(final String name, final int index, final Color foreColor, final Color backColor,
      final String fontName, final Float fontSize, final Boolean bold, final Boolean italic) {
    final JRStyle style = getElementAt(name, index).getStyle();
    styleTest(style, foreColor, backColor, fontName, fontSize, bold, italic);
  }

  protected void styleTest(final JRStyle style, final Color foreColor, final Color backColor, final String fontName,
      final Float fontSize, final Boolean bold, final Boolean italic) {
    Assertions.assertNotNull(style, "style is null");
    Assertions.assertEquals(foreColor, style.getForecolor(), "foreColor");
    Assertions.assertEquals(backColor, style.getBackcolor(), "backColor");
    Assertions.assertEquals(fontName, style.getFontName(), "fontName");
    Assertions.assertEquals(fontSize, style.getFontsize(),"fontSize");
    Assertions.assertEquals(bold, style.isBold(), "bold");
    Assertions.assertEquals(italic, style.isItalic(), "italic");
  }

  protected void borderTest(final String name, final int index, final Color topColor, final LineStyleEnum topLineStyle,
      final float top, final Color bottomColor, final LineStyleEnum bottomLineStyle, final float bottom, final Color leftColor,
      final LineStyleEnum leftLineStyle, final float left, final Color rightColor, final LineStyleEnum rightLineStyle,
      final float right) {
    final JRStyle style = getElementAt(name, index).getStyle();

    JRBoxPen pen = style.getLineBox().getTopPen();
    Assertions.assertEquals(top, pen.getLineWidth().floatValue(), 0);
    Assertions.assertEquals(topColor, pen.getLineColor());
    Assertions.assertEquals(topLineStyle, pen.getLineStyleValue());

    pen = style.getLineBox().getBottomPen();
    Assertions.assertEquals(bottom, pen.getLineWidth().floatValue(), 0);
    Assertions.assertEquals(bottomColor, pen.getLineColor());
    Assertions.assertEquals(bottomLineStyle, pen.getLineStyleValue());

    pen = style.getLineBox().getLeftPen();
    Assertions.assertEquals(left, pen.getLineWidth().floatValue(), 0);
    Assertions.assertEquals(leftColor, pen.getLineColor());
    Assertions.assertEquals(leftLineStyle, pen.getLineStyleValue());

    pen = style.getLineBox().getRightPen();
    Assertions.assertEquals(right, pen.getLineWidth().floatValue(), 0);
    Assertions.assertEquals(rightColor, pen.getLineColor());
    Assertions.assertEquals(rightLineStyle, pen.getLineStyleValue());
  }

  protected void paddingTest(final String name, final int index, final Integer top, final Integer bottom, final Integer left,
      final Integer right) {
    final JRStyle style = getElementAt(name, index).getStyle();
    Assertions.assertEquals(top, style.getLineBox().getTopPadding());
    Assertions.assertEquals(bottom, style.getLineBox().getBottomPadding());
    Assertions.assertEquals(left, style.getLineBox().getLeftPadding());
    Assertions.assertEquals(right, style.getLineBox().getRightPadding());
  }

  protected void horizontalAlignmentTest(final String name, final int index,
      final HorizontalImageAlignEnum horizontalAlignment) {
    final JRImageAlignment element = (JRImageAlignment) getElementAt(name, index);
    if (horizontalAlignment == null) {
      Assertions.assertEquals(HorizontalImageAlignEnum.LEFT,
          element.getHorizontalImageAlign(), "horizontalAlignment");
    }
    Assertions.assertEquals(horizontalAlignment,
        element.getHorizontalImageAlign(), "horizontalAlignment");
  }

  protected void horizontalAlignmentTest(final String name, final int index,
      final HorizontalTextAlignEnum horizontalAlignment) {
    final JRTextAlignment element = (JRTextAlignment) getElementAt(name, index);
    if (horizontalAlignment == null) {
      Assertions.assertEquals(HorizontalTextAlignEnum.LEFT,
          element.getHorizontalTextAlign(), "horizontalAlignment");
    }
    Assertions.assertEquals(horizontalAlignment,
        element.getHorizontalTextAlign(), "horizontalAlignment");
  }

  protected void verticalAlignmentTest(final String name, final int index,
      final VerticalImageAlignEnum verticalAlignment) {
    final JRImageAlignment element = (JRImageAlignment) getElementAt(name, index);
    if (verticalAlignment == null) {
      Assertions.assertEquals(VerticalTextAlignEnum.TOP,
          element.getVerticalImageAlign(), "verticalAlignment");
    }
    Assertions.assertEquals(verticalAlignment, element.getVerticalImageAlign(), "verticalAlignment");
  }

  protected void verticalAlignmentTest(final String name, final int index,
      final VerticalTextAlignEnum verticalAlignment) {
    final JRTextAlignment element = (JRTextAlignment) getElementAt(name, index);
    if (verticalAlignment == null) {
      Assertions.assertEquals(VerticalTextAlignEnum.TOP,
          element.getVerticalTextAlign(), "verticalAlignment");
    }
    Assertions.assertEquals(verticalAlignment, element.getVerticalTextAlign(), "verticalAlignment");
  }

  // column detail
  protected void columnDetailStyleTest(final ColumnBuilder<?, ?> column, final int index, final Color foreColor,
      final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic) {
    styleTest(JasperTestUtils.getColumnDetailName(column), index, foreColor, backColor, fontName,
        fontSize, bold, italic);
  }

  protected void columnDetailPaddingTest(final ColumnBuilder<?, ?> column, final int index, final Integer top,
      final Integer bottom, final Integer left, final Integer right) {
    paddingTest(JasperTestUtils.getColumnDetailName(column), index, top, bottom, left, right);
  }

  protected void columnDetailAlignmentTest(final ColumnBuilder<?, ?> column, final int index,
      final HorizontalImageAlignEnum horizontalAlignment) {
    horizontalAlignmentTest(JasperTestUtils.getColumnDetailName(column), index,
        horizontalAlignment);
  }

  protected void columnDetailAlignmentTest(final ColumnBuilder<?, ?> column, final int index,
      final VerticalImageAlignEnum verticalAlignment) {
    verticalAlignmentTest(JasperTestUtils.getColumnDetailName(column), index, verticalAlignment);
  }

  protected void columnDetailAlignmentTest(final ColumnBuilder<?, ?> column, final int index,
      final HorizontalTextAlignEnum horizontalAlignment) {
    horizontalAlignmentTest(JasperTestUtils.getColumnDetailName(column), index,
        horizontalAlignment);
  }

  protected void columnDetailAlignmentTest(final ColumnBuilder<?, ?> column, final int index,
      final VerticalTextAlignEnum verticalAlignment) {
    verticalAlignmentTest(JasperTestUtils.getColumnDetailName(column), index, verticalAlignment);
  }

  protected void columnDetailBorderTest(final ColumnBuilder<?, ?> column, final int index, final Color topColor,
      final LineStyleEnum topLineStyle, final float top, final Color bottomColor, final LineStyleEnum bottomLineStyle,
      final float bottom, final Color leftColor, final LineStyleEnum leftLineStyle, final float left, final Color rightColor,
      final LineStyleEnum rightLineStyle, final float right) {
    borderTest(JasperTestUtils.getColumnDetailName(column), index, topColor, topLineStyle, top,
        bottomColor, bottomLineStyle, bottom, leftColor, leftLineStyle, left, rightColor,
        rightLineStyle, right);
  }

  // column title
  protected void columnTitleBorderTest(final ColumnBuilder<?, ?> column, final int index, final Color topColor,
      final LineStyleEnum topLineStyle, final float top, final Color bottomColor, final LineStyleEnum bottomLineStyle,
      final float bottom, final Color leftColor, final LineStyleEnum leftLineStyle, final float left, final Color rightColor,
      final LineStyleEnum rightLineStyle, final float right) {
    borderTest(JasperTestUtils.getColumnTitleName(column), index, topColor, topLineStyle, top,
        bottomColor, bottomLineStyle, bottom, leftColor, leftLineStyle, left, rightColor,
        rightLineStyle, right);
  }

  protected void columnTitlePaddingTest(final ColumnBuilder<?, ?> column, final int index, final Integer top,
      final Integer bottom, final Integer left, final Integer right) {
    paddingTest(JasperTestUtils.getColumnTitleName(column), index, top, bottom, left, right);
  }

  protected void columnTitleStyleTest(final ColumnBuilder<?, ?> column, final int index, final Color foreColor,
      final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic) {
    styleTest(JasperTestUtils.getColumnTitleName(column), index, foreColor, backColor, fontName,
        fontSize, bold, italic);
  }

  protected void columnTitleAlignmentTest(final ColumnBuilder<?, ?> column, final int index,
      final HorizontalTextAlignEnum horizontalAlignment) {
    horizontalAlignmentTest(JasperTestUtils.getColumnTitleName(column), index, horizontalAlignment);
  }

  // subtotal label
  protected void subtotalLabelBorderTest(final SubtotalBuilder<?, ?> subtotal, final int index, final Color topColor,
      final LineStyleEnum topLineStyle, final float top, final Color bottomColor, final LineStyleEnum bottomLineStyle,
      final float bottom, final Color leftColor, final LineStyleEnum leftLineStyle, final float left, final Color rightColor,
      final LineStyleEnum rightLineStyle, final float right) {
    borderTest(JasperTestUtils.getSubtotalLabelName(subtotal, 1), index, topColor, topLineStyle,
        top, bottomColor, bottomLineStyle, bottom, leftColor, leftLineStyle, left, rightColor,
        rightLineStyle, right);
  }

  protected void subtotalLabelStyleTest(final SubtotalBuilder<?, ?> subtotal, final int index, final Color foreColor,
      final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic) {
    styleTest(JasperTestUtils.getSubtotalLabelName(subtotal, 1), index, foreColor, backColor,
        fontName, fontSize, bold, italic);
  }

  // subtotal
  protected void subtotalBorderTest(final SubtotalBuilder<?, ?> subtotal, final int index, final Color topColor,
      final LineStyleEnum topLineStyle, final float top, final Color bottomColor, final LineStyleEnum bottomLineStyle,
      final float bottom, final Color leftColor, final LineStyleEnum leftLineStyle, final float left, final Color rightColor,
      final LineStyleEnum rightLineStyle, final float right) {
    borderTest(JasperTestUtils.getSubtotalName(subtotal, 1), index, topColor, topLineStyle, top,
        bottomColor, bottomLineStyle, bottom, leftColor, leftLineStyle, left, rightColor,
        rightLineStyle, right);
  }

  protected void subtotalPaddingTest(final SubtotalBuilder<?, ?> subtotal, final int index, final Integer top,
      final Integer bottom, final Integer left, final Integer right) {
    paddingTest(JasperTestUtils.getSubtotalName(subtotal, 1), index, top, bottom, left, right);
  }

  protected void subtotalStyleTest(final SubtotalBuilder<?, ?> subtotal, final int index, final Color foreColor,
      final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic) {
    styleTest(JasperTestUtils.getSubtotalName(subtotal, 1), index, foreColor, backColor, fontName,
        fontSize, bold, italic);
  }

  // group header title
  protected void groupHeaderTitleStyleTest(final GroupBuilder<?> group, final int index, final Color foreColor,
      final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic) {
    styleTest(JasperTestUtils.getHeaderTitleGroupName(group), index, foreColor, backColor, fontName,
        fontSize, bold, italic);
  }

  // group header
  protected void groupHeaderStyleTest(final GroupBuilder<?> group, final int index, final Color foreColor,
      final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic) {
    styleTest(JasperTestUtils.getHeaderGroupName(group), index, foreColor, backColor, fontName,
        fontSize, bold, italic);
  }

  protected void groupHeaderAlignmentTest(final GroupBuilder<?> group, final int index,
      final HorizontalTextAlignEnum horizontalAlignment) {
    horizontalAlignmentTest(JasperTestUtils.getHeaderGroupName(group), index, horizontalAlignment);
  }
}
