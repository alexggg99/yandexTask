package com.company.yandexTask;

public class EventListenerImpl implements EventListener {

    private HitCounter counterMinute = new HitCounter(60);
    private HitCounter counterHour = new HitCounter(60 * 60);
    private HitCounter counterDay = new HitCounter(60 * 60 * 24);

    @Override
    public void fireEvent() {
        counterMinute.hit();
        counterHour.hit();
        counterDay.hit();
    }

    @Override
    public long numberEventsLastMinute() {
        return counterMinute.getHits();
    }

    @Override
    public long numberEventsLastHour() {
        return counterHour.getHits();
    }

    @Override
    public long numberEventsLastDay() {
        return counterDay.getHits();
    }

    public static void main(String[] args) throws InterruptedException {
        EventListener eventListener = new EventListenerImpl();
        eventListener.fireEvent();
        Thread.sleep(61000);
        System.out.println(eventListener.numberEventsLastMinute());
        System.out.println(eventListener.numberEventsLastHour());
        System.out.println(eventListener.numberEventsLastDay());
        eventListener.fireEvent();
        eventListener.fireEvent();
        System.out.println(eventListener.numberEventsLastMinute());
        System.out.println(eventListener.numberEventsLastHour());
    }
}
