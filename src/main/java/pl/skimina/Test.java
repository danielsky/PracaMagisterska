package pl.skimina;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Test {
	
	public static void main(String[] args){
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
		
		ps.v_s = 0.2;
		ps.v_t = 0.3;
		
		ps.dlugosc_wymiennika = 0.7;
		ps.ilosc_sekcji = 10;
		
		ps.configure();
		
		Worker w = new Worker(ps);
		w.run();
		/*
		Data data = w.getCurrentData();
		Gson gson = new Gson();
		System.out.println(gson.toJson(data));*/
		BufferedImage image =  new BufferedImage(400, 100, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 400, 100);
		
		MinMax minmax = new MinMax();
		for(Sekcja s : w.getSekcje()){
			minmax.add(s.T_p);
			minmax.add(s.T_t);
			minmax.add(s.T_s);
			minmax.add(s.T_z);
		}
		
		double dt = minmax.getMax()-minmax.getMin();
		System.out.println("MIN: "+minmax.getMin());
		System.out.println("MAX: "+minmax.getMax());
		System.out.println("DT: "+dt);
		
		int pos=10,offset=38;
		for(Sekcja s : w.getSekcje()){
			
			System.out.println("POS: "+pos);
			double val = (s.T_z-minmax.getMin())/dt;
			//System.out.println(val);
			g.setColor(getColor(val));
			g.fillRect(pos, 10, offset, 20);
			
			
			val = (s.T_p-minmax.getMin())/dt;
			//System.out.println(val);
			g.setColor(getColor(val));
			g.fillRect(pos, 30, offset, 20);
			
			val = (s.T_s-minmax.getMin())/dt;
			//System.out.println(val);
			g.setColor(getColor(val));
			g.fillRect(pos, 50, offset, 20);
			
			val = (s.T_t-minmax.getMin())/dt;
			//System.out.println(val);
			g.setColor(getColor(val));
			g.fillRect(pos, 70, offset, 20);
			
			pos += offset;
			
		}
		
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
		
		g.setColor(Color.BLACK);
		g.drawRect(10, 10, 380, 80);
		
		//g.drawLine(10, 40, 390, 40);
		//g.drawLine(10, 60, 390, 60);
		
		JOptionPane.showMessageDialog(null, new ImageIcon(image));
		
		
	}
	
	public static Color getColor(double power)
	{
	    double H = power * 0.4; // Hue (note 0.4 = Green, see huge chart below)
	    double S = 0.9; // Saturation
	    double B = 0.9; // Brightness

	    return Color.getHSBColor((float)H, (float)S, (float)B);
	}
	
	private static class MinMax{
		
		private double min, max;
		private boolean firstTime;
		
		public MinMax() {
			firstTime = true;
		}
		
		public void add(double value){
			if(firstTime){
				min = value;
				max = value;
				firstTime = false;
			}else{
				if(value<min) min = value;
				if(value>max) max = value;
			}
		}
		
		public double getMin() {
			return min;
		}
		
		public double getMax() {
			return max;
		}
	}

}
