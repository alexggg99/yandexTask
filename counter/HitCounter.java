package com.company.yandexTask;

import java.util.Deque;
import java.util.LinkedList;

public class HitCounter {

    private final int TIME_GAP;

    private volatile long hits;
    private volatile long time;

    private volatile Deque<Long> slots = new LinkedList<>();

    public HitCounter(int window_size /*seconds*/) {
        TIME_GAP = window_size * 1000;
        time = System.currentTimeMillis();
    }

    private synchronized void resize(long current) {
        long before = current - TIME_GAP;
        while (slots.size() > 0 && slots.getFirst() < before) {
            slots.removeFirst();
            hits--;
        }
        if (slots.size() == 0) {
            slots.add(current);
        }
        time = slots.getFirst();
    }

    public synchronized void hit() {
        long current = System.currentTimeMillis();
        if (time < current - TIME_GAP)  {
            resize(current);
        } else {
            slots.add(current);
        }
        hits++;
    }

    public synchronized long getHits() {
        long current = System.currentTimeMillis();
        if (time < current - TIME_GAP)
            resize(current);
        return hits;
    }
}
