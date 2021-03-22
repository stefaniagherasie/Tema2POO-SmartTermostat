package Tema2POO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Clasa House simuleaza o locuinta cu specifiicatiile cerute.
 * @author Gherasie Stefania 323CB
 */
public class House {
	/**
	 * ArrayList in care sunt salvate camerele din casa
	 */
	ArrayList<Room> rooms = new ArrayList<Room>();
	int numberOfRooms;
	double globalTemp;
	double globalHum;
	int timestamp;
	int totalArea;
	
	/**
	 * Constructor cu parametrii care realizeaza un obiect de tip House
	 * cu specificatiile dorite.
	 * @param numberOfRooms
	 * @param globalTemp
	 * @param globalHum
	 * @param timestamp
	 */
	House(int numberOfRooms, double globalTemp, double globalHum, int timestamp) {
		this.numberOfRooms = numberOfRooms;
		this.globalTemp = globalTemp;
		this.globalHum = globalHum;
		this.timestamp = timestamp;
		this.totalArea = 0;		
	}
	
	/**
	 * Adauga un element de tip Room in vectorul de camere.
	 * Calculeaza suprafata totala.
	 * @param newRoom
	 */
	public void addRoom(Room newRoom) {
		rooms.add(newRoom);
		totalArea += newRoom.area;
	}
	
	/**
	 * Gaseste indexul camerei in functie de ID.
	 * @param roomID
	 * @return
	 */
	public int findRoomIndex(String roomID) {
		
		for(int i = 0; i < numberOfRooms; i++) {
			if((rooms.get(i).roomID).equals(roomID) == true)
				return i;
		}
		return -1;	
	}
	
	/**
	 * Gaseste intervalul orar in care se inregistreaza temperatura
	 * in functie de timestamp.
	 * @param roomTimestamp
	 * @return
	 */
	public int findInterval(int roomTimestamp) {
		return 23 - (timestamp - roomTimestamp) / 3600;
	}
	
	/**
	 * Adauga informatiile inregistrate despre camera la un moment de timp.
	 * @param roomID
	 * @param roomTimestamp
	 * @param roomTemp
	 */
	public void observeRoom(String roomID, int roomTimestamp, double roomTemp) {
		int roomIndex = findRoomIndex(roomID);
		int interval = findInterval(roomTimestamp); 

		((rooms.get(roomIndex)).roomDevice).addTemperature(interval, roomTemp, roomTimestamp);
	}
	
	/**
	 * Adauga informatiile inregistrate despre umiditate la un moment de timp.
	 * @param roomID
	 * @param roomTimestamp
	 * @param roomHum
	 */
	public void observeHRoom(String roomID, int roomTimestamp, double roomHum) {
		int roomIndex = findRoomIndex(roomID);
		int interval = findInterval(roomTimestamp); 
		((rooms.get(roomIndex)).roomDevice).addHumidity(interval, roomHum, roomTimestamp);
	}	

	
	/**
	 * Calculeaza media temperaturilor si a umiditatilor si declanseaza
	 * centrala daca este nevoie.
	 * @throws IOException
	 */
	public void triggerHeat() throws IOException {
		
		File outputFile = new File("therm.out");
		FileWriter output = new FileWriter(outputFile, true);
		
		double sumOfTemp = 0;
		double sumOfHum = 0;
		
		for(int i = 0; i < numberOfRooms; i++) {
			sumOfTemp += rooms.get(i).tempPerArea();
			sumOfHum += rooms.get(i).humPerArea();
		}
		
		double medTemp = sumOfTemp / totalArea;
		double medHum = sumOfHum / totalArea;
		
		if(medTemp >= globalTemp)
			output.write("NO" + System.lineSeparator());
		else if(globalHum != -1 && medHum <= globalHum)
			output.write("NO" + System.lineSeparator());
		else 
			output.write("YES" + System.lineSeparator());
		
		output.close();	
	}
	
	/**
	 * Schimba temperatura globala dorita.
	 * @param newTemp
	 */
	public void changeTemp(double newTemp) {
		globalTemp = newTemp;
	}
	
	/**
	 * Listeaza temperaturile dintr-o camera intr-un interval de timp.
	 * @param roomID
	 * @param startInt
	 * @param stopInt
	 * @throws IOException
	 */
	public void listRoomTemp(String roomID, int startInt, int stopInt) throws IOException{
		for(int i = 0; i < rooms.size(); i++) {
			if(rooms.get(i).getName().equals(roomID)) {
				rooms.get(i).listTempInInterval(roomID, startInt, stopInt);
				break;
			}
		}
	}
	
}
