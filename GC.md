# GC
___

## ZGC

> Java 11 has introduced a low latency GC. This is an experimental feature.

The Z Garbage Collector (ZGC) is a scalable low latency garbage collector. ZGC performs all expensive work concurrently, without stopping the execution of application threads for more than 10ms, which makes is suitable for applications which require low latency and/or use a very large heap (multi-terabytes).

The Z Garbage Collector is available as an experimental feature, and is enabled with the command-line options -XX:+UnlockExperimentalVMOptions -XX:+UseZGC.


## Epsilon: A No-Op Garbage Collector
JEP 318: Epsilon: A No-Op Garbage Collector
Unlike the JVM GC which is responsible for allocating memory and releasing it, ## Epsilon only allocates memory. ##
It allocates memory for the following things:

- Performance testing.
- Memory pressure testing.
- VM interface testing.
- Extremely short lived jobs.
- Last-drop latency improvements.
- Last-drop throughput improvements.
Now Elipson is good only for test environments. ## It will lead to OutOfMemoryError in production and crash the applications. ##

The benefit of Elipson is no memory clearance overhead. Hence it’ll give an accurate test result of performance and we can no longer GC for stopping it.

>Note: This is an experimental feature.


## 参考文献
- [Epsilon](http://openjdk.java.net/jeps/318)
- [ZGC](https://docs.oracle.com/en/java/javase/11/gctuning/z-garbage-collector1.html#GUID-A5A42691-095E-47BA-B6DC-FB4E5FAA43D0)
- [garbage collection tuning guide](https://docs.oracle.com/en/java/javase/11/gctuning/hotspot-virtual-machine-garbage-collection-tuning-guide.pdf)