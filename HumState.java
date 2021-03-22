package Tema2POO;

/**
 * Clasa HumState este folosita pentru a retine umiditatile
 * si timestamp-ul asociat.<br>
 * Clasa implementeaza Interfata Comparable pentru a suprascrie metoda compareTo.
 * Aceasta este folosita pentru sortarea ulterioara a elementelor HumState.
 * @author Gherasie Stefania 323CB
 *
 */
public class HumState implements Comparable <HumState>{
	private double humidity;
	
	/**
	 * Constructor cu parametrii.
	 * @param newHum
	 * @param newTimeStamp
	 */
	public HumState(double newHum, int newTimeStamp) {
		this.humidity = newHum;
	}
	
	/**
	 * @return umiditatea
	 */
	public double getHumidity() {
		return humidity;
	}
	
	/**
	 * Seteaza umiditatea.
	 * @param newhumidity
	 */
	public void setHumidity(double newhumidity) {
		this.humidity = newhumidity;
	}
	
	/**
	 * Se suprascrie metoda compareTo pentru a putea ulterior sorta elemente
	 * de acest tip. 
	 */
	@Override
	public int compareTo(HumState compState) {
		double comphumidity = compState.getHumidity();
		if(this.humidity > comphumidity)
			return 1;
		else if(this.humidity < comphumidity)
			return -1;
		else return 0;
	}
	
}
