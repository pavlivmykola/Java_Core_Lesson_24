package ua.lviv.lgs;

import java.util.Iterator;
import java.util.TreeSet;

public class Schedule {

	public TreeSet<Seance> seanceSet;

	public Schedule() {
		super();
		this.seanceSet = new TreeSet<Seance>();
	}
	
	public void addSeance(Seance seance) throws TimeException {
		// check for valid time of begining and ending
		Iterator<Seance> iterator = seanceSet.iterator();
		while (iterator.hasNext()){
			Seance next = iterator.next();
			if (!Time.checkTimeInterval(seance.getStartTime(), seance.getEndTime(), next.getStartTime(), next.getEndTime())) throw new TimeException("Сеанси накладаються один на оден");
		}
		seanceSet.add(seance);
	}
	
	public void removeSeance(Seance seance) {		
		seanceSet.remove(seance);
	}
	
	
	
}
