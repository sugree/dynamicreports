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
package net.sf.dynamicreports.jasper.base;

import static net.sf.dynamicreports.jasper.base.JasperScriptletManager.USE_THREAD_SAFE_SCRIPLET_MANAGER_PROPERTY_KEY;

import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;




/**
 * Unit tests for {@link JasperCustomValues}.
 */
public class JasperCustomValuesTest {

  private final JasperScriptlet scriptlet = new JasperScriptlet();

  @Test
  public void shouldUseDefaultScriptletManager() {
    final JasperCustomValues cut = createClassUnderTest(false);
    Assertions.assertTrue(cut.getScriptletManager() instanceof DefaultJasperScriptletManager);
  }

  @Test
  public void shouldUseThreadSafeScriptleManagerIfPropertySet() {
    final JasperCustomValues cut = createClassUnderTest(true);
    Assertions.assertTrue(cut.getScriptletManager() instanceof ThreadSafeJasperScriptletManager);
  }

  @Test
  public void shouldSetScriptletWithDefaultManager() {
    final JasperCustomValues cut = createClassUnderTest(false);
    cut.setJasperScriptlet(scriptlet);
    Assertions.assertEquals(scriptlet, cut.getJasperScriptlet());
  }

  @Test
  public void shouldSetScriptletWithThreadSafeManager() {
    final JasperCustomValues cut = createClassUnderTest(true);
    cut.setJasperScriptlet(scriptlet);
    Assertions.assertEquals(scriptlet, cut.getJasperScriptlet());
  }

  private JasperCustomValues createClassUnderTest(final boolean useThreadSafeManager) {
    final Properties properties = new Properties();
    if (useThreadSafeManager) {
      properties.setProperty(USE_THREAD_SAFE_SCRIPLET_MANAGER_PROPERTY_KEY, "true");
    }
    return new JasperCustomValues(properties);
  }
}
