package Tema2POO;

import java.io.*;

/**
 * Programul implementeaza un mecanism de observare a temperaturilor 
 * dintr-o casa si simuleaza functionarea unei centrale termice 
 * inteligente care optimizeaza incalzirea locuintei in functie de datele 
 * pe care le primeste.<br>
 * Centrala tine cont si de umiditatile inregistrate.
 * @author Gherasie Stefania 323CB
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		
		/*deschiderea fisierului de intrare pentru citire*/
		File inputFile = new File("therm.in");
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		
		/*deschiderea fisierului de iesire pentru scriere*/
		File outputFile = new File("therm.out");
		FileWriter output = new FileWriter(outputFile);
		output.close();
		
		/*citirea si interpretarea primei linii din fisier*/
		String dataLine = input.readLine();
		String[] data = dataLine.split(" ", 0);
		int numberOfRooms = Integer.parseInt(data[0]);
		double globalTemp = Double.parseDouble(data[1]);
		
		/*ramane -1 daca nu se tine cont de umiditati*/
		double globalHumidity = -1;
		int timestamp;
		
		/*cazul in care sunt si umiditati*/		
		if(data.length == 4) {
			globalHumidity = Double.parseDouble(data[2]);
			timestamp = Integer.parseInt(data[3]);		
		}
		else {
			timestamp = Integer.parseInt(data[2]);
		}
		
		
		/*se contruieste o casa cu specificatiile obtinute mai sus*/
		House house = new House(numberOfRooms, globalTemp, globalHumidity, timestamp);
		
		/*se citesc informatiile pentru fiecare camera*/
		for(int i = 0; i < numberOfRooms; i++) {
			dataLine = input.readLine();
			data = dataLine.split(" ", 0);
			Room newRoom = new Room(data[0], data[1], Integer.parseInt(data[2]));
			house.addRoom(newRoom);
		}
		
		/*se citesc comenzile pe rand*/
		String command;
		while((command = input.readLine()) != null) {
			data = command.split(" ", 0);
			
			/*comanda OBSERVE prin care se inregistreaza temperaturi
			 *pentru camerele corespunzatoare ID-ului
			 */
			if(data[0].equals("OBSERVE") == true) {
				String roomID = new String(data[1]);
				int roomTimestamp = Integer.parseInt(data[2]);
				double roomTemp = Double.parseDouble(data[3]);
				
				if(timestamp - roomTimestamp > 0)
					house.observeRoom(roomID, roomTimestamp, roomTemp);			
			}
			
			/*comanda OBSERVEH prin care se inregistreaza umiditati
			 *pentru camerele corespunzatoare ID-ului
			 */			
			else if(data[0].equals("OBSERVEH") == true) {
				String roomID = new String(data[1]);
				int roomTimestamp = Integer.parseInt(data[2]);
				double roomHum = Double.parseDouble(data[3]);
				
				if(timestamp - roomTimestamp > 0)
					house.observeHRoom(roomID, roomTimestamp, roomHum);				
			}
			
			/*comanda TRIGGER HEAT prin care se decide pornirea centralei*/
			else if(command.equals("TRIGGER HEAT") == true)
				house.triggerHeat();
			
			/*comanda TEMPERATURE prin care se schimba temperatura*/
			else if(data[0].equals("TEMPERATURE") == true) {
				globalTemp = Double.parseDouble(data[1]);
				house.changeTemp(globalTemp);
			}
			
			/*comanda LIST care listeaza temperaturile fiecarei camere
			 *dintr-un interval dat
			 */
			else if(data[0].equals("LIST") == true) {
				String roomID = new String(data[1]);
				int startInterval = Integer.parseInt(data[2]);
				int stopInterval = Integer.parseInt(data[3]);
				
				house.listRoomTemp(roomID, startInterval, stopInterval);			
			}
		}		
		
		input.close();

	}
}
