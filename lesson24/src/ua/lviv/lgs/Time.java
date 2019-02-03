package ua.lviv.lgs;

import java.util.Scanner;

public class Time {
	private int min;
	private int hour;
	
	public Time(int min, int hour) throws TimeException {
		super();
		if (0>min || min>=60) throw new TimeException("Хвилини поза межами 0..60");		
		if (0>hour || hour>=24) throw new TimeException("Години поза межами 0..24");		
		this.min = min;
		this.hour = hour;
	}

	public Time(String message)  throws TimeException  {
		super();
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		int hour=0;
		int min=0;
		System.out.println("години");
		if (sc.hasNextInt()) {
			hour = sc.nextInt();
		}
		System.out.println("хвилини");
		if (sc.hasNextInt()) {
			min = sc.nextInt();
		}
		if (0>min || min>=60) throw new TimeException("Хвилини поза межами 0..60");		
		if (0>hour || hour>=24) throw new TimeException("Години поза межами 0..24");		
		this.min = min;
		this.hour = hour;		
	}
	
	
	public int getMin() {
		return min;
	}



	public int getHour() {
		return hour;
	}



	@Override
	public String toString() {
		return  Integer.toString(hour)+"год "+Integer.toString(min)+"хв";
	}

	public static boolean compareTime(Time time1, Time time2){
		if (time1.hour>time2.hour) return true;
			else if (time1.hour>=time2.hour && time1.min>time2.min) return true;
		return false;
	}
	
	public static boolean checkTimeInterval(Time timeOneBegin, Time timeOneEnd, Time timeTwoBegin, Time timeTwoEnd){
			if (compareTime(timeOneBegin,timeTwoBegin) && compareTime(timeOneBegin,timeTwoEnd) && compareTime(timeOneEnd,timeTwoBegin) && compareTime(timeOneEnd,timeTwoEnd)) return true;
			if (!compareTime(timeOneBegin,timeTwoBegin) && !compareTime(timeOneBegin,timeTwoEnd) && !compareTime(timeOneEnd,timeTwoBegin) && !compareTime(timeOneEnd,timeTwoEnd)) return true;
		return false;
	}
	
	
	public Time addTime(Time time1, Time time2) throws TimeException {
		int min=time1.getMin()+time2.getMin();		
		int hour=0;
		if (min>=60) {
			int i=(int) Math.floor(min/60);
			min=min%60;
			hour=i*60;
		}
		hour+=time1.getHour()+time2.getHour();
		return new Time(min,hour);
	}

	
	
}


class TimeException extends Exception {
	public TimeException(String message) {
		super(message);
	}
}