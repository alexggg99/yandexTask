package com.company.yandexTask;

public interface EventListener {
    void fireEvent();
    long numberEventsLastMinute();
    long numberEventsLastHour();
    long numberEventsLastDay();
}
