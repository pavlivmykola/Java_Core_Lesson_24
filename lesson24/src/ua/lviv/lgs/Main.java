package ua.lviv.lgs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Main {


	public static void main(String[] args) {
		Cinema cinema=null;
		Days day;
		Schedule schedule;
		
	
		try {
			cinema = new Cinema(new Time(0,9), new Time(0,23));
		}
		catch (TimeException ex) {
				System.out.println(ex);
				System.exit(0);
			}
		
		try{
			cinema.init();
		} catch (TimeException ex){
			System.out.println(ex);
			System.exit(0);
		}
		while (true) {		
			System.out.println();
			System.out.println("¬вед≥ть 1 щоб додати ф≥льм");
			System.out.println("¬вед≥ть 2 щоб додати сеанс");
			System.out.println("¬вед≥ть 3 щоб видалити ф≥льм");
			System.out.println("¬вед≥ть 4 щоб видалити сеанс");
			System.out.println("¬вед≥ть 5 щоб вивести список ф≥льм≥в");
			System.out.println("¬вед≥ть 6 щоб вивести список сеанс≥в на день");
			System.out.println("¬вед≥ть 7 щоб вивести розклад");
			System.out.println("¬вед≥ть 8 ≥ б≥льше щоб вийти з програми");
			
			Scanner sc = new Scanner(System.in);
			int i = sc.nextInt();
			
			switch (i) {
			case 1:
				try {
					cinema.addMovie();				
				} catch (TimeException ex) {
					System.out.println("Ќеправильний час");
					System.out.println(ex);
				}				
				break;
			case 2:
				day = Days.getDay();
				if (day==null) {
					cinema.addSeanceAllDays(null);
				} else cinema.addSeance(null,day);
				break;
			case 3:
				Movie movie=Movie.chooseMovie();
				cinema.removeMovie(movie);
				break;
			case 4:
				day = Days.getDay();
				if (day==null) {
					try {
						cinema.removeSeanceForAllDays(); 
					} catch (TimeException ex) {
						System.out.println("Ќеправильний час");
						System.out.println(ex);
					}
				}
				else cinema.removeSeance(day);
				break;
			case 5:
				Movie.getMovieSet().stream().forEach(System.out::println);
				break;
			case 6:		
				day = Days.getDay();
				if (day==null) {
					for (Days day1:Days.values()) {
						System.out.println();
						System.out.println(day1.name());
						schedule = cinema.getScheduleMap().get(day1);
						cinema.getSchedule(schedule);
					}					
				} else {
					System.out.println("¬водимо розклад сеанс≥в на "+day.name());
					schedule = cinema.getScheduleMap().get(day);
					cinema.getSchedule(schedule);
				}	
				break;
			case 7:
				for (Days day1:Days.values()) {
					System.out.println();
					System.out.println(day1.name());
					schedule = cinema.getScheduleMap().get(day1);
					cinema.getSchedule(schedule);
				}
				break;
			default: System.exit(0);
			}
		}

		}
	
	

	
	
	
}
