package Tema2POO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Clasa Room simuleaza o camera din casa cu centrala inteligenta.
 * @author Gherasie Stefania 323CB
 */
public class Room {
	String roomName;
	String roomID;
	int area;
	/**
	 * Fiecare camera detine un dispozitiv prin care se retin datele
	 * de temperatura si umiditate.
	 */
	Device roomDevice;
	/**
	 * Variabila statica care contorizeaza cate temperaturi s-au afisat.<br>
	 * Folosita in metoda listTempInInterval() pentru afisarea controlata a separatorului de linie.
	 */
	static int numList = 0;
	
	/**
	 * Contructor cu parametrii care care initializeaza un obiect de tip
	 * Room cu atributele coresunzatoare.
	 * @param name
	 * @param ID
	 * @param area
	 */
	Room(String name, String ID,int area) {
		this.roomName = name;
		this.roomID = ID;
		this.area = area;
		roomDevice = new Device(roomID);
	}
	
	/**
	 * @return numele camerei.
	 */
	String getName() {
		return roomName;
	}

	/**
	 * Listeaza temperaturile din camera dintr-un interval dat.
	 * @param roomID
	 * @param startInt
	 * @param stopInt
	 * @throws IOException
	 */
	void listTempInInterval(String roomID, int startInt, int stopInt) throws IOException{
		
		File outputFile = new File("therm.out");
		FileWriter output = new FileWriter(outputFile, true);
		
		output.write(roomID);
		
		for(int i = 23; i >=0 ; i--) {
			/*se utilizeaza pentru salvarea temperaturilor valide,
			 * tinand cont sa nu se afiseze de mai multe ori aceeasi temperatura.
			*/
			ArrayList<Double> tempList = new ArrayList<Double>();
			for(int j = 0; j < roomDevice.tempInterval.get(i).size(); j++) {
				int timestamp = roomDevice.tempInterval.get(i).get(j).getTimeStamp();
				double temp = roomDevice.tempInterval.get(i).get(j).getTemp();
				
				if(startInt <= timestamp && timestamp <= stopInt) {
					if(tempList.contains(temp) == false)
						tempList.add(temp);
				}		
			}	
			
			/*afisarea controlata a temperaturilor si a spatiilor*/
			if(numList != 0) {
				for(int j = 0; j < tempList.size(); j++)
					output.write(" " + String.format ("%.2f",tempList.get(j)));
			}
			else {
				for(int j = 0; j < tempList.size(); j++)
					output.write(String.format ("%.2f",tempList.get(j)) + " ");
				numList++;
			}
			
		}
		
		output.write(System.lineSeparator());
		output.close();
	}
	
	/**
	 * @return Cea mai mica temperatura din camera din ultima ora.
	 */
	public double lastTempRoom() {
		return roomDevice.lastTemp();
	}
	
	/**
	 * @return Cea mai mica umiditate din camera din ultima ora.
	 */
	public double lastHumRoom() {
		return roomDevice.lastHumidity();
	}
	
	/**
	 * @return Temperatura minima din camera in functie de suprafata.
	 */
	public double tempPerArea() {
		return lastTempRoom() * area;
	}
	
	/**
	 * @return Umiditatea minima din camera in functie de suprafata.
	 */
	public double humPerArea() {
		return lastHumRoom() * area;
	}
	
}