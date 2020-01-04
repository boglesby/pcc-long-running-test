package io.pivotal.test.server.metrics;

import org.apache.geode.StatisticDescriptor;
import org.apache.geode.Statistics;
import org.apache.geode.StatisticsType;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.distributed.internal.InternalDistributedSystem;
import org.apache.geode.internal.statistics.platform.LinuxSystemStats;
import org.apache.geode.management.ManagementService;
import org.apache.geode.management.OSMetrics;
import org.apache.geode.management.internal.beans.MemberMBean;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MetricsHelper {

  public static void addOSMetrics(Map allMetrics) {
    Cache cache = CacheFactory.getAnyInstance();
    InternalDistributedSystem system = (InternalDistributedSystem) cache.getDistributedSystem();

    Map osMetrics = new TreeMap();
    allMetrics.put("os", osMetrics);
    ManagementService managementService = ManagementService.getManagementService(cache);
    MemberMBean bean = (MemberMBean) managementService.getMemberMXBean();
    OSMetrics osm = bean.showOSMetrics();
    osMetrics.put("fdsOpen", osm.getOpenFileDescriptorCount());
    osMetrics.put("fdLimit", osm.getMaxFileDescriptorCount());
    osMetrics.put("processCpuTime", osm.getProcessCpuTime());
    osMetrics.put("name", osm.getName());
    osMetrics.put("version", osm.getVersion());
    osMetrics.put("architecture", osm.getArch());

    // If the linux system statistics exist, add them to the os metrics map.
    // Otherwise add the available stats from the osmetrics.
    Statistics[] systemStatisticsArr = system.findStatisticsByType(LinuxSystemStats.getType());
    if (systemStatisticsArr.length > 0) {
      Statistics systemStatistics = systemStatisticsArr[0];
      StatisticsType type = systemStatistics.getType();
      for (StatisticDescriptor descriptor : type.getStatistics()) {
        String statName = descriptor.getName();
        Number statValue = systemStatistics.get(statName);
        osMetrics.put(statName, statValue);
      }
    } else {
      osMetrics.put("totalMemory", osm.getTotalPhysicalMemorySize());
      osMetrics.put("freeMemory", osm.getFreePhysicalMemorySize());
      osMetrics.put("cpus", osm.getAvailableProcessors());
      osMetrics.put("loadAverage1", osm.getSystemLoadAverage());
      osMetrics.put("allocatedSwap", osm.getTotalSwapSpaceSize());
      osMetrics.put("freeSwap", osm.getFreeSwapSpaceSize());
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
}
