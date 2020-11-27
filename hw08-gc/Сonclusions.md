# Сравнение разных сборщиков мусора

## Параметры запуска JVM
```
-Xms256m
-Xmx256m
-Xlog:gc=debug:file=./logs/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./logs/dump
```

### G1
-XX:+UseG1GC

### Serial Collector
-XX:+UseSerialGC

### Parallel Collector
-XX:+UseParallelGC

### CMS
-XX:+UseConcMarkSweepGC

### ZGC
-XX:+UnlockExperimentalVMOptions
-XX:+UseZGC

## Результаты

### G1
time:280 sec    
number of [G1 Young Generation] is [4265]   
number of [G1 Old Generation] is [27]   
number of [G1 Young Generation] per minute is [913]  
number of [G1 Old Generation] per minute is [5] 

### Serial Collector
time:258 sec    
number of [Copy] is [1999]  // minor GC  
number of [MarkSweepCompact] is [999]   // major GC     
number of [Copy] per minute is [464]    // minor GC   
number of [MarkSweepCompact] per minute is [232]    // major GC      

### Parallel Collector
time:495 sec    
number of [PS MarkSweep] is [1001]  // major GC     
number of [PS Scavenge] is [3994]   // minor GC     
number of [PS MarkSweep] per minute is [121]  // major GC    
number of [PS Scavenge] per minute is [484]   // minor GC   

### CMS
time:649 sec    
number of [ParNew] is [1999]    // minor GC     
number of [ConcurrentMarkSweep] is [1980]   // major GC     
number of [ParNew] per minute is [184]    // minor GC   
number of [ConcurrentMarkSweep] per minute is [183] // major GC     

### ZGC
time:162 sec
number of [ZGC] is [1292]   // major GC     
number of [ZGC] per minute is [478] // major GC     

## Выводы. Какой gc лучше и почему
