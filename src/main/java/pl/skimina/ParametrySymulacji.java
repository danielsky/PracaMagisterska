package pl.skimina;

import java.util.HashMap;
import java.util.Map;

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
	
	
	public ParametrySymulacji(HttpServletRequest req) throws NumberFormatException, Exception{
		
		
		t_t = Double.parseDouble(req.getParameter(TEMP_T));
		int id = Integer.parseInt(req.getParameter(PLYN_WEW));
		if(id == 0){
			ro_t = Double.parseDouble(req.getParameter(RO_T));
			c_t = Double.parseDouble(req.getParameter(C_T));
		}else{
			MaterialPlyn plyn = MaterialPlyn.getByID(id);
			ro_t = plyn.getRo();
			c_t = plyn.getC();
		}
		
		
		
		t_p = Double.parseDouble(req.getParameter(TEMP_P));
		id = Integer.parseInt(req.getParameter(MAT_WEW));
		if(id == 0){
			ro_p = Double.parseDouble(req.getParameter(RO_P));
			c_p = Double.parseDouble(req.getParameter(C_P));
		}else{
			MaterialRura rura = MaterialRura.getByID(id);
			ro_p = rura.getRo();
			c_p = rura.getC();
			
		}
		
		
		
		
		
		
		t_s = Double.parseDouble(req.getParameter(TEMP_S));
		id = Integer.parseInt(req.getParameter(PLYN_ZEW));
		if(id == 0){
			ro_s = Double.parseDouble(req.getParameter(RO_S));
			c_s = Double.parseDouble(req.getParameter(C_S));
		}else{
			MaterialPlyn plyn = MaterialPlyn.getByID(id);
			ro_s = plyn.getRo();
			c_s = plyn.getC();
		}
		
		
		t_z = Double.parseDouble(req.getParameter(TEMP_Z));
		id = Integer.parseInt(req.getParameter(MAT_ZEW));
		if(id == 0){
			ro_z = Double.parseDouble(req.getParameter(RO_Z));
			c_z = Double.parseDouble(req.getParameter(C_Z));
		}else{
			MaterialRura rura = MaterialRura.getByID(id);
			ro_z = rura.getRo();
			c_z = rura.getC();
		}
		
		
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
	
	//Wyliczone z parametrow
	public double alfa_t;
	public double alfa_s;
	public double alfa_3;
	
	
	public void configure(){
		
		dlugosc_sekcji = dlugosc_wymiennika / ((double)ilosc_sekcji);
		
		
		//do wyliczenia
		alfa_t = 30000;
		alfa_s = 30000;
		alfa_3 = 30000;
	}
	
	public void configure(double alfa){
		
		dlugosc_sekcji = dlugosc_wymiennika / ((double)ilosc_sekcji);
		
		
		//do wyliczenia
		alfa_t = alfa;
		alfa_s = alfa;
		alfa_3 = alfa;
	}
	
	public Map<String, String> getValues(){
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("ro_t", Double.toString(ro_t));
		map.put("c_t", Double.toString(c_t));
		map.put("t_t", Double.toString(t_t));
		
		map.put("ro_p", Double.toString(ro_p));
		map.put("c_p", Double.toString(c_p));
		map.put("t_p", Double.toString(t_p));
		
		map.put("ro_s", Double.toString(ro_s));
		map.put("c_s", Double.toString(c_s));
		map.put("t_s", Double.toString(t_s));
		
		map.put("ro_z", Double.toString(ro_z));
		map.put("c_z", Double.toString(c_z));
		map.put("t_z", Double.toString(t_z));
		
		map.put("d1", Double.toString(d1));
		map.put("d2", Double.toString(d2));
		map.put("d3", Double.toString(d3));
		map.put("d4", Double.toString(d4));
		
		map.put("v_t", Double.toString(v_t));
		map.put("v_s", Double.toString(v_s));
		
		map.put("dlugosc_wymiennika", Double.toString(dlugosc_wymiennika));
		map.put("dlugosc_sekcji", Double.toString(dlugosc_sekcji));
		map.put("ilosc_sekcji", Integer.toString(ilosc_sekcji));
		map.put("alfa_t", Double.toString(alfa_t));
		map.put("alfa_s", Double.toString(alfa_s));
		map.put("alfa_3", Double.toString(alfa_3));
		
		
		return map;
	}
	
	
	
	
	
}