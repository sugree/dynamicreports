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
package net.sf.dynamicreports.report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Unit tests for {@link ReportUtils}.
 */
public class ReportUtilsTest {

  private static final String BASE_NAME = "any name";
  private static final int NUMBER_OF_THREADS = 20;
  private static final int NUMBER_OF_NAMES = 1000;

  @BeforeEach
  public void setUp() {
    ReportUtils.setCounter(1);
  }

  @Test
  public void shouldGenerateUniqueNamesConcurrently() throws Exception {
    final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    try {
      final List<Future<String>> futureNames = submitTasks(executor);
      assertUniqueNames(futureNames);
    } finally {
      executor.shutdown();
    }
  }

  @Test
  public void shouldResetCounter() {
    ReportUtils.setCounter(Integer.MAX_VALUE);
    final String name = ReportUtils.generateUniqueName(BASE_NAME);
    Assertions.assertTrue(name.endsWith("0_"));
  }

  private List<Future<String>> submitTasks(final ExecutorService executor) {
    final Callable<String> task = () -> ReportUtils.generateUniqueName(BASE_NAME);
    final List<Future<String>> futureNames = new ArrayList<>();
    IntStream.range(0, NUMBER_OF_NAMES).forEach(i -> futureNames.add(executor.submit(task)));
    return futureNames;
  }

  private void assertUniqueNames(final List<Future<String>> futureNames) throws Exception {
    final Set<String> generatedNames = new HashSet<>();
    for (final Future<String> futureName : futureNames) {
      generatedNames.add(futureName.get());
    }
    Assertions.assertEquals(NUMBER_OF_NAMES, generatedNames.size());
  }
}
