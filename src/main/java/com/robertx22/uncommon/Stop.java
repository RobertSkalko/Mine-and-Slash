package com.robertx22.uncommon;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

public class Stop {

    StopWatch stop = new StopWatch();
    int min = 0;
    TimeUnit unit = TimeUnit.MICROSECONDS;

    public Stop() {
	stop = new StopWatch();
	stop.start();
    }

    public Stop(TimeUnit unit, int min) {

	this.min = min;
	this.unit = unit;

	stop = new StopWatch();
	stop.start();

    }

    public void print() {

	stop.stop();
	Long time = stop.getTime(unit);
	if (time > min) {
	    System.out.println("Action took: " + time + " " + unit.name().toLowerCase());

	}
    }

}
