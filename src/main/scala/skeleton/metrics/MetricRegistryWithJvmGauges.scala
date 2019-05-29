package skeleton.metrics

import java.util.concurrent.TimeUnit

import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.jvm.{CachedThreadStatesGaugeSet, GarbageCollectorMetricSet, MemoryUsageGaugeSet}

object MetricRegistryWithJvmGauges {

  def apply: MetricRegistry = {
    val metricsRegistry = new MetricRegistry()
    metricsRegistry.register("gc", new GarbageCollectorMetricSet())
    metricsRegistry.register("threads", new CachedThreadStatesGaugeSet(10, TimeUnit.SECONDS))
    metricsRegistry.register("memory", new MemoryUsageGaugeSet())
    metricsRegistry
  }

}
