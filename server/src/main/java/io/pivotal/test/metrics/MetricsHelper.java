package io.pivotal.test.metrics;

import org.apache.geode.StatisticDescriptor;
import org.apache.geode.Statistics;
import org.apache.geode.StatisticsType;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.distributed.internal.InternalDistributedSystem;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MetricsHelper {

  private static final InternalDistributedSystem system =
    (InternalDistributedSystem) CacheFactory.getAnyInstance().getDistributedSystem();

  private static final String[] typeNames = new String[] {
    "LinuxSystemStats", "VMStats", "CacheServerStats", "DistributionStats"
  };

  private static final List<StatisticsType> types =
    Arrays.stream(typeNames).map(name -> system.findType(name)).collect(Collectors.toList());

  public static void addStatisticsMetrics(Map allMetrics) {
    for (StatisticsType type : types) {
      addMetrics(allMetrics, type);
    }
  }

  public static void addMetrics(Map allMetrics, StatisticsType type) {
    Map osMetrics = new TreeMap();
    allMetrics.put(type.getName(), osMetrics);
    Statistics[] systemStatisticsArr = system.findStatisticsByType(type);
    if (systemStatisticsArr.length > 0) {
      Statistics systemStatistics = systemStatisticsArr[0];
      for (StatisticDescriptor descriptor : type.getStatistics()) {
        String statName = descriptor.getName();
        Number statValue = systemStatistics.get(statName);
        osMetrics.put(statName, statValue);
      }
    }
  }

  public static void addGCMetrics(Map allMetrics) {
    Map gcMetrics = new TreeMap();
    allMetrics.put("gc", gcMetrics);
    List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
    for (GarbageCollectorMXBean gcBean : gcBeans) {
      gcMetrics.put(gcBean.getName() + "_collections", gcBean.getCollectionCount());
      gcMetrics.put(gcBean.getName() + "_collectionTime", gcBean.getCollectionTime());
    }
  }

  public static void addMemoryMetrics(Map allMetrics) {
    Map memoryMetrics = new TreeMap();
    allMetrics.put("memory", memoryMetrics);
    List<MemoryPoolMXBean> memoryBeans = ManagementFactory.getMemoryPoolMXBeans();
    for (MemoryPoolMXBean memoryBean : memoryBeans) {
      if (memoryBean.getName().contains("Old") || memoryBean.getName().contains("Eden")) {
        String memoryBeanName = memoryBean.getName().replaceAll(" ", "_");
        MemoryUsage usage = memoryBean.getUsage();
        memoryMetrics.put(memoryBeanName + "_init", usage.getInit());
        memoryMetrics.put(memoryBeanName + "_used", usage.getUsed());
        memoryMetrics.put(memoryBeanName + "_max", usage.getMax());
      }
    }
  }

  public static void addStatisticsMetrics(List<Metric> allMetrics) {
    for (StatisticsType type : types) {
      addMetrics(allMetrics, type);
    }
  }

  public static void addMetrics(List<Metric> allMetrics, StatisticsType type) {
    Statistics[] systemStatsArr = system.findStatisticsByType(type);
    if (systemStatsArr.length > 0) {
      Statistics systemStatistics = systemStatsArr[0];
      for (StatisticDescriptor descriptor : type.getStatistics()) {
        String statName = descriptor.getName();
        Number statValue = systemStatistics.get(statName);
        allMetrics.add(new Metric(statName, statValue, type.getName()));
      }
    }
  }

  public static void addGCMetrics(List<Metric> allMetrics) {
    List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
    for (GarbageCollectorMXBean gcBean : gcBeans) {
      allMetrics.add(new Metric("server.jvm.gc.collections", gcBean.getCollectionCount(), "VMGCStats", gcBean.getName()));
      allMetrics.add(new Metric("server.jvm.gc.collection.time", gcBean.getCollectionTime(), "VMGCStats", gcBean.getName()));
    }
  }

  public static void addMemoryMetrics(List<Metric> allMetrics) {
    List<MemoryPoolMXBean> memoryBeans = ManagementFactory.getMemoryPoolMXBeans();
    for (MemoryPoolMXBean memoryBean : memoryBeans) {
      if (memoryBean.getType().equals(MemoryType.HEAP)) {
        String memoryBeanName = memoryBean.getName().replaceAll(" ", "");
        MemoryUsage usage = memoryBean.getUsage();
        allMetrics.add(new Metric("server.jvm.memory.used", usage.getUsed(), "VMMemoryPoolStats", memoryBeanName));
        allMetrics.add(new Metric("server.jvm.memory.max", usage.getMax(), "VMMemoryPoolStats", memoryBeanName));
      }
    }
  }
}
