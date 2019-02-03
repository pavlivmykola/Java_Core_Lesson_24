package ua.lviv.lgs;

import java.util.Scanner;

public enum Days {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);
    
    
    int number;
	
	Days(int number){
		this.number=number;
	}
	
	public static Days getDay(){
		System.out.println("¬ибер≥ть день в≥д 1 до 7");
		System.out.println("≤нше - вибран≥ вс≥ дн≥");
		for (Days day:Days.values()) {
			System.out.println(day.number+" - "+day.name());
		}
		Scanner sc = new Scanner(System.in);
		int i=0;
		if (sc.hasNextInt()) i = sc.nextInt();
		for (Days day:Days.values()) {
			if (day.number==i) {
				return day;				
			}
		}
		return null;
	}
}
