package Tema2POO;

import java.util.*;

/**
 * Clasa Device simuleaza dispozitivul dintr-o camera care inregistreaza
 * toate datele.
 * @author Gherasie Stefania 323CB
 */
public class Device {
	String roomName;
	
	/**
	 * Se retin temperaturile inregistate la timestamp-ul corespunzator,
	 * fiind introdu-se in functie de intervalul orar.
	 */
	ArrayList<ArrayList<TempState>> tempInterval = 
			new ArrayList<ArrayList<TempState>>(24);
	/**
	 * Se retin umiditatile inregistate la timestamp-ul corespunzator,
	 * fiind introdu-se in functie de intervalul orar.
	 */
	ArrayList<ArrayList<HumState>> humInterval = 
			new ArrayList<ArrayList<HumState>>(24);
	
	
	/**
	 * Contructor cu parametrii care care initializeaza un obiect de tip
	 * Device cu atributele coresunzatoare.
	 * @param roomName
	 */
	Device(String roomName) {
		this.roomName = roomName;
		
		for(int i = 0; i < 24; i++) {
			ArrayList<TempState> tempList = new ArrayList<TempState>();
			(tempInterval).add(tempList);
			ArrayList<HumState> humList = new ArrayList<HumState>();
			(humInterval).add(humList);
		}
	}
	
	/**
	 * Adauga temperatura in ArrayList-ul corespunzator, in functie de interval.
	 * Se sorteaza ArrayList-ul dupa fiecare adaugare.
	 * @param index
	 * @param temp
	 * @param time
	 */
	public void addTemperature(int index, double temp, int time) {
		TempState data = new TempState(temp, time);
		(tempInterval.get(index)).add(data);
		Collections.sort(tempInterval.get(index));
	}

	/**
	 * Adauga umiditatea in ArrayList-ul corespunzator, in functie de interval.
	 * Se sorteaza ArrayList-ul dupa fiecare adaugare.
	 * @param index
	 * @param hum
	 * @param time
	 */
	public void addHumidity(int index, double hum, int time) {
		HumState data = new HumState(hum, time);
		(humInterval.get(index)).add(data);
		Collections.sort(humInterval.get(index));
	}

	/**
	 * @return cea mai mica temperatura din ultima ora din 
	 * camera dispozitivului.
	 */
	public double lastHumidity() {
		for(int i = 23; i >= 0; i--)
			if(humInterval.get(i).size() != 0) 
				return humInterval.get(i).get(0).getHumidity();
		return 0;
	}
	
	
	/**
	 * @return cea mai mica umiditate din ultima ora din 
	 * camera dispozitivului.
	 */
	public double lastTemp() {
		for(int i = 23; i >= 0; i--)
			if(tempInterval.get(i).size() != 0) 
				return tempInterval.get(i).get(0).getTemp();
		return 0;
	}
	
	
}
