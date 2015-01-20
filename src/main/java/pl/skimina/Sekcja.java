package pl.skimina;

public class Sekcja {
	public Sekcja(double t, double s, double p, double z) {
		T_t = t;
		T_s = s;
		T_p = p;
		T_z = z;
	}
	
	/**
	 * Plyn w rurze wewnętrznej
	 * indeks 't'
	 */
	public double T_t;		//temperatura płynu
	
	
	/**
	 * Plyn w rurze zewnętrznej
	 * indeks 's'
	 */
	public double T_s;		//temperatura płynu
	
	
	/**
	 * scianka rury wewnętrznej
	 * indeks 'p'
	 */
	public double T_p;		//temperatura rury wew.
	
	
	
	/**
	 * scianka rury zewnętrznej
	 * indeks 'p'
	 */
	public double T_z;		//temperatura rury zew.
	
	
	/**
	 * Zmiana temperatury w sekcji
	 */
	public double dT_t;
	public double dT_s;
	public double dT_p;
	public double dT_z;
	
}
