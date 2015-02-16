package pl.skimina;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Test {
	
	public static void main(String[] args){
		ParametrySymulacji ps = getParam2();
		
		System.out.println("Długość sekcji: "+ps.dlugosc_sekcji);
		
		Worker w = new Worker(ps);
		w.run();
		/*
		Data data = w.getCurrentData();
		Gson gson = new Gson();
		System.out.println(gson.toJson(data));*/
		
		
		
		ResultVisualizer res = new ResultVisualizer(ps.dlugosc_wymiennika, ps.d4, ps.ilosc_sekcji);
		res.setDiameters(ps.d1, ps.d2, ps.d3, ps.d4);
		res.setResults(w.getSekcje());
		
		BufferedImage image = res.vizualize();
		
		
		
		System.out.println("T: ");
		for(Sekcja s : w.getSekcje()){
			System.out.println(s.T_t);
		}
		System.out.println("P: ");
		for(Sekcja s : w.getSekcje()){
			System.out.println(s.T_p);
		}
		System.out.println("S: ");
		for(Sekcja s : w.getSekcje()){
			System.out.println(s.T_s);
		}
		System.out.println("Z: ");
		for(Sekcja s : w.getSekcje()){
			System.out.println(s.T_z);
		}
		
		try{
			
			ImageIO.write(image, "png", new File("/home/daniel/vis.png"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, new ImageIcon(image));
		
		
	}
	
	
	private static ParametrySymulacji getParam1(){
		ParametrySymulacji ps = new ParametrySymulacji();
		
		//plyn wewn
		ps.c_t = 4187;
		ps.t_t = 300;
		ps.ro_t = 1000;
		
		//rura wewn
		ps.c_p = 500;
		ps.t_p = 320;
		ps.ro_p = 7500;
		
		//plyn zew
		ps.c_s = 4187;
		ps.t_s = 340;
		ps.ro_s = 1000;
		
		//rura zewn
		ps.c_z = 500;
		ps.t_z = 330;
		ps.ro_z = 7500;
		
		ps.d1 = 0.1;
		ps.d2 = 0.11;
		ps.d3 = 0.20;
		ps.d4 = 0.21;
		
		ps.v_s = 0.05;
		ps.v_t = 0.07;
		
		ps.dlugosc_wymiennika = 0.7;
		ps.ilosc_sekcji = 20;
		
		ps.configure();
		
		return ps;
	}
	
	private static ParametrySymulacji getParam2(){
		ParametrySymulacji ps = new ParametrySymulacji();
		
		//plyn wewn
		ps.c_t = 1000;
		ps.t_t = 50+273;
		ps.ro_t = 2;
		
		//rura wewn
		ps.c_p = 380;
		ps.t_p = 25+273;
		ps.ro_p = 8920;
		
		//plyn zew
		ps.c_s = 1000;
		ps.t_s = 15+273;
		ps.ro_s = 1.2;
		
		//rura zewn
		ps.c_z = 380;
		ps.t_z = 10+273;
		ps.ro_z = 8920;
		
		ps.d1 = 0.05;
		ps.d2 = 0.052;
		ps.d3 = 0.1;
		ps.d4 = 0.105;
		
		ps.v_s = 0.1;
		ps.v_t = 0.05;
		
		ps.dlugosc_wymiennika = 2;
		ps.ilosc_sekcji = 20;
		
		ps.configure(30);
		
		return ps;
	}
}
