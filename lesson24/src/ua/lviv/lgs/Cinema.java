package ua.lviv.lgs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Cinema {

	public static Time timeOpen;
	public static Time timeClose;
	
	
	private TreeMap<Days, Schedule> scheduleMap;
	
	
	public Cinema(Time timeOpen, Time timeClose) {
		super();
		this.scheduleMap = new TreeMap<Days, Schedule>();
		for (Days day:Days.values()) this.scheduleMap.put(day, new Schedule());
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;		
	}
	
	public TreeMap<Days, Schedule> getScheduleMap() {
		return scheduleMap;
	}

	public static Time getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(Time timeOpen) {
		this.timeOpen = timeOpen;
	}

	public static Time getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(Time timeClose) {
		this.timeClose = timeClose;
	}


	public void init() throws TimeException{
		Movie movie1 = new Movie("one",new Time(4,1));
		Movie movie2 = new Movie("two",new Time(8,0));
		Movie movie3 = new Movie("three",new Time(5,2));
		Movie movie4 = new Movie("four",new Time(34,0));
		Movie movie5 = new Movie("five",new Time(48,0));
		Schedule schedule;
		for (Days day:Days.values()) {
			schedule = scheduleMap.get(day);
			schedule.seanceSet.add(new Seance(movie3, new Time(30,10)));
			schedule.seanceSet.add(new Seance(movie4, new Time(43,12)));
			schedule.seanceSet.add(new Seance(movie1, new Time(0,9)));
			schedule.seanceSet.add(new Seance(movie2, new Time(15,10)));
			schedule.seanceSet.add(new Seance(movie5, new Time(27,13)));
		}
		
	}



	public void addMovie() throws TimeException {
		Movie movie = new Movie();
		Days day = Days.getDay();
		Scanner sc = new Scanner(System.in);
		boolean addNewSeance = true;
		String yes = "";		
		while (addNewSeance){
			System.out.println("Ввести новий сеанс для даного фільму? (y/n)");
			yes=sc.next();
			if(yes.toUpperCase().equals("Y")){
				if (day==null) {
					addSeanceAllDays(movie);
				} else addSeance(movie,day);
			} else addNewSeance=false;
		}
	}
	
	public void addSeance(Movie movie, Days day) {
		System.out.println("Існуючі сеанси на "+day.name());
		Schedule schedule = scheduleMap.get(day);
		if (schedule.seanceSet.size()==0) {
			System.out.println("Ще немає сеансів на цей день");
		} else {
			Iterator<Seance> iterator = schedule.seanceSet.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
			}
		}
		if (movie==null) movie=Movie.chooseMovie();
		try {
			schedule.addSeance(new Seance(movie, new Time("Початок сеансу")));
		} catch (Exception ex) {
			System.out.println("Неправильний час");
			System.out.println(ex);
		}
	}
	
	public void addSeanceAllDays(Movie movie){
		if (movie==null) movie=Movie.chooseMovie();
		Schedule schedule;
		Time time=null;
		try {
			time=new Time("Початок сеансу");
			for (Days day:Days.values()) {
				schedule = scheduleMap.get(day);
				schedule.addSeance(new Seance(movie, time));
			}
		} catch (TimeException ex) {
			System.out.println("Неправильний час");
			System.out.println(ex);		
		}
	}
	
	public void removeMovie(Movie movie) {
		Iterator<Movie> iterator = Movie.movieSet.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(movie)) iterator.remove(); 
		}
		for (Days day:Days.values()){
			Schedule schedule = scheduleMap.get(day);
			Iterator<Seance> iterator1 = schedule.seanceSet.iterator();
			while (iterator1.hasNext()) {
				Seance next = iterator1.next();				
				if (next.getMovie().equals(movie)) iterator1.remove();
			}			
		}
	}
	
	public void removeSeance(Days day) {
		Schedule schedule = scheduleMap.get(day);
		if (schedule.seanceSet.size()==0) {
			System.out.println("Ще немає сеансів на цей день");
		} else {
			Iterator<Seance> iterator = schedule.seanceSet.iterator();
			int i=0;
			System.out.println("Виберіть сеанс для видалення");
			while (iterator.hasNext()) {
				i++;
				System.out.println(i+" - "+iterator.next().toString());
			}
			Scanner sc = new Scanner(System.in);
			Iterator<Seance> iterator1 = schedule.seanceSet.iterator();
			i=0;
			int number=0;
			if (sc.hasNextInt()) number=sc.nextInt();
			while (iterator1.hasNext()) {
				iterator1.next();
				i++;
				if (i==number) iterator1.remove();				
			}	
		}	
	}
	
	public void removeSeanceForAllDays() throws TimeException{
		int count=0;
		Movie movie=Movie.chooseMovie();
		Time time = new Time("Введіть початок сеансу");
		for (Days day:Days.values()){
			Schedule schedule = scheduleMap.get(day);
			Iterator<Seance> iterator = schedule.seanceSet.iterator();
			while (iterator.hasNext()) {
				Seance next = iterator.next();				
				if (next.getMovie().equals(movie) && next.getStartTime().getHour()==time.getHour() && next.getStartTime().getMin()==time.getMin()) {
					iterator.remove();
					count++;
				}
			}			
		}
		System.out.println("Видалено "+count+" сеансів");
	}
	
	public void getSchedule(Schedule schedule){
		if (schedule.seanceSet.size()==0) {
			System.out.println("Ще немає сеансів на цей день");
		} else {
			Iterator<Seance> iterator = schedule.seanceSet.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
			}
		}
	}
	

	
}
