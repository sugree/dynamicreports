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
package net.sf.dynamicreports.report.builder.style;

import static net.sf.dynamicreports.report.constant.FontName.ARIAL;
import static net.sf.dynamicreports.report.constant.FontName.COURIER_NEW;
import static net.sf.dynamicreports.report.constant.FontName.TIMES_NEW_ROMAN;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



/**
 * Integration tests for {@link StyleBuilders}.
 */
public class StyleBuildersTest {

  private final StyleBuilders cut = new StyleBuilders();

  @Test
  public void shouldCreateFontBuilderWithArial() {
    final FontBuilder fontBuilder = cut.fontArial();
    Assertions.assertEquals(ARIAL, fontBuilder.getFont().getFontName());
    Assertions.assertFalse(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithArialBold() {
    final FontBuilder fontBuilder = cut.fontArialBold();
    Assertions.assertEquals(ARIAL, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithArialBoldItalic() {
    final FontBuilder fontBuilder = cut.fontArialBold().italic();
    Assertions.assertEquals(ARIAL, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertTrue(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithTimesNewRoman() {
    final FontBuilder fontBuilder = cut.fontTimesNewRoman();
    Assertions.assertEquals(TIMES_NEW_ROMAN, fontBuilder.getFont().getFontName());
    Assertions.assertFalse(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithTimesNewRomanBold() {
    final FontBuilder fontBuilder = cut.fontTimesNewRomanBold();
    Assertions.assertEquals(TIMES_NEW_ROMAN, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithTimesNewRomanBoldItalic() {
    final FontBuilder fontBuilder = cut.fontTimesNewRomanBold().italic();
    Assertions.assertEquals(TIMES_NEW_ROMAN, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertTrue(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithCourierNew() {
    final FontBuilder fontBuilder = cut.fontCourierNew();
    Assertions.assertEquals(COURIER_NEW, fontBuilder.getFont().getFontName());
    Assertions.assertFalse(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithCourierNewBold() {
    final FontBuilder fontBuilder = cut.fontCourierNewBold();
    Assertions.assertEquals(COURIER_NEW, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithCourierNewBoldItalic() {
    final FontBuilder fontBuilder = cut.fontCourierNewBold().italic();
    Assertions.assertEquals(COURIER_NEW, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertTrue(fontBuilder.getFont().getItalic());
  }

}
