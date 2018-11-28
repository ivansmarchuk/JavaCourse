package com.example.Benchmark;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.ListenerNotFoundException;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BlockingGC {
    private final CountDownLatch doneSgn = new CountDownLatch(2);
    private List<Runnable> reg = new ArrayList<>();

    private BlockingGC(){
        installGCMonitoring();
    }
    static void collect() {
        BlockingGC bgc = new BlockingGC();
        try {
            System.gc();
            bgc.doneSgn.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bgc.reg.forEach(Runnable::run);
        }
    }

    private void installGCMonitoring() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcBean: gcBeans) {

            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            NotificationListener listener = (notification, handback) ->{
              if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)){
                  GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                  long duration = info.getGcInfo().getDuration();

                  String gcType = info.getGcAction();
                  System.out.println(gcType + ": - "
                          + info.getGcInfo().getId() + ", "
                          + info.getGcName()
                          + " (from " + info.getGcCause() + ") " + duration + " milliseconds");
              }
                GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                if (info.getGcCause().equals("System.gc()")) {
                    doneSgn.countDown();
                }
            };

            emitter.addNotificationListener(listener, null, null);

            reg.add(() -> {
                try {
                    emitter.removeNotificationListener(listener);
                } catch (ListenerNotFoundException e) {
                    e.printStackTrace();
                }
            });

        }

    }


}
