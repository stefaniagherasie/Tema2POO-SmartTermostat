package Tema2POO;

/**
 * Clasa TempState este folosita pentru a retine temperaturile 
 * si timestamp-ul asociat.<br>
 * Clasa implementeaza Interfata Comparable pentru a suprascrie metoda compareTo.
 * Aceasta este folosita pentru sortarea ulterioara a elementelor TempState.
 * @author Gherasie Stefania 323CB
 */
public class TempState implements Comparable <TempState>{
	private double temp;
	private int timestamp;
	
	/**
	 * Contructor cu parametrii.
	 * @param newTemp
	 * @param newTimeStamp
	 */
	public TempState(double newTemp, int newTimeStamp) {
		this.temp = newTemp;
		this.timestamp = newTimeStamp;
	}
	
	/**
	 * @return temperatura
	 */
	public double getTemp() {
		return temp;
	}
	
	/**
	 * Seteaza temperatura.
	 * @param newTemp
	 */
	public void setTemp(double newTemp) {
		this.temp = newTemp;
	}
	
	/**
	 * @return timestamp-ul
	 */
	public int getTimeStamp() {
		return timestamp;
	}

	
	/**
	 * Se suprascrie metoda compareTo pentru a putea ulterior sorta elemente
	 * de acest tip. 
	 */
	@Override
	public int compareTo(TempState comp) {
		double compTemp = ((TempState)comp).getTemp();
		if(this.temp > compTemp)
			return 1;
		else if(this.temp < compTemp)
			return -1;
		else return 0;
	}
	
}
