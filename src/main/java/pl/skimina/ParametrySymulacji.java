package pl.skimina;

import javax.servlet.http.HttpServletRequest;

public class ParametrySymulacji {
	
	public static String PLYN_WEW = "plyn_wewn";
	public static String PLYN_ZEW = "plyn_zewn";
	public static String MAT_WEW = "material_wewn";
	public static String MAT_ZEW = "material_zewn";
	
	
	
	
	public static String RO_T = "ro_t";
	public static String C_T = "c_t";
	public static String TEMP_T = "t_t";
	
	public static String RO_S = "ro_s";
	public static String C_S = "c_s";
	public static String TEMP_S = "t_s";
	
	public static String RO_P = "ro_p";
	public static String C_P = "c_p";
	public static String TEMP_P = "t_p";
	
	public static String RO_Z = "ro_z";
	public static String C_Z = "c_z";
	public static String TEMP_Z = "t_z";
	
	public static String D1 = "d1";
	public static String D2 = "d2";
	public static String D3 = "d3";
	public static String D4 = "d4";
		
	public static String V_T = "v_t";
	public static String V_S = "v_s";
	
	public static String DLUGOSC_WYMIENNIKA = "dlugosc_wymiennika";
	public static String ILOSC_SEKCJI = "ilosc_sekcji";
	
	
	public ParametrySymulacji(HttpServletRequest req) throws NumberFormatException{
		ro_t = Double.parseDouble(req.getParameter(RO_T));
		c_t = Double.parseDouble(req.getParameter(C_T));
		t_t = Double.parseDouble(req.getParameter(TEMP_T));
		
		ro_s = Double.parseDouble(req.getParameter(RO_S));
		c_s = Double.parseDouble(req.getParameter(C_S));
		t_s = Double.parseDouble(req.getParameter(TEMP_S));
		
		ro_p = Double.parseDouble(req.getParameter(RO_P));
		c_p = Double.parseDouble(req.getParameter(C_P));
		t_p = Double.parseDouble(req.getParameter(TEMP_P));
		
		ro_z = Double.parseDouble(req.getParameter(RO_Z));
		c_z = Double.parseDouble(req.getParameter(C_Z));
		t_z = Double.parseDouble(req.getParameter(TEMP_Z));
		
		d1 = Double.parseDouble(req.getParameter(D1));
		d2 = Double.parseDouble(req.getParameter(D2));
		d3 = Double.parseDouble(req.getParameter(D3));
		d4 = Double.parseDouble(req.getParameter(D4));
		
		v_t = Double.parseDouble(req.getParameter(V_T));
		v_s = Double.parseDouble(req.getParameter(V_S));
		
		dlugosc_wymiennika = Double.parseDouble(req.getParameter(DLUGOSC_WYMIENNIKA));
		ilosc_sekcji = Integer.parseInt(req.getParameter(ILOSC_SEKCJI));
		
	}
	
	public ParametrySymulacji() {
		//empty object
	}
	
	public String validate(){
		if(ilosc_sekcji <=1){
			return "Ilość sekcji musi być większa od 1";
		}
		
		return null;
	}
	
	
	
	
	/**
	 * Plyn w rurze wewnętrznej
	 * indeks 't'
	 */
	public double ro_t;		//gestosc plynu
	public double c_t;		//ciepło właściwe płynu
	public double t_t;	//temperatura poczatkowa
	
	
	
	
	/**
	 * Plyn w rurze zewnętrznej
	 * indeks 's'
	 */
	public double ro_s;		//gestosc plynu
	public double c_s;		//ciepło właściwe płynu
	public double t_s;	//temperatura poczatkowa
	
	
	/**
	 * scianka rury wewnętrznej
	 * indeks 'p'
	 */
	public double ro_p;		//gestosc rury wew.
	public double c_p;		//ciepło właściwe rury wew.
	public double t_p;	//temperatura poczatkowa
	
	
	/**
	 * scianka rury zewnętrznej
	 * indeks 'z'
	 */
	public double ro_z;		//gestosc rury zew.
	public double c_z;		//ciepło właściwe rury zew.
	public double t_z;	//temperatura poczatkowa

	
	//Średnice
	public double d1;	//Średnica wewnetrzna rury wewnętrznej
	public double d2;	//Średnica zewnetrzna rury wewnętrznej
	public double d3;	//Średnica wewnetrzna rury zewnętrznej
	public double d4;	//Średnica zewnetrzna rury zewnętrznej
	
	
	//Prędkości plynów
	public double v_t;	//predkosc wewn
	public double v_s;	//predkosc zewn
	
	
	/**
	 * rozmiary
	 */
	public double dlugosc_wymiennika;
	public int ilosc_sekcji;
	public double dlugosc_sekcji;
	
	
	public void configure(){
		
		dlugosc_sekcji = dlugosc_wymiennika / ((double)ilosc_sekcji);
		
		
		//do wyliczenia
		alfa_t = 30;
		alfa_s = 30;
		alfa_3 = 30;
	}
	
	
	
	//Wyliczone z parametrow
	public double alfa_t;
	public double alfa_s;
	public double alfa_3;
	
}