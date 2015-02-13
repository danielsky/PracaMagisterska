package pl.skimina;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultVisualizer {
	
	
	private final double MAX_WIDTH = 1400;
	private final double MAX_HEIGHT = 1000;
	private final int MARGIN = 15;
	
	private int width,height,sections;
	
	private double d1,d2,d3,d4;
	//private double[] t_t, t_p, t_s, t_z;
	private Sekcja[] sekcje;
	
	private double min_temp, max_temp;
	
	private double section_size;
	
	
	public ResultVisualizer(double w, double h, int sections) {
		double s1 = MAX_WIDTH/w;
		double s2 = MAX_HEIGHT/h;
		double s = Math.min(s1, s2);
		
		width = (int)(w*s);
		height = (int)(h*s);
		
		this.sections = sections;
		
		section_size = (double)width/(double)sections;
	}
	
	public void setDiameters(double d1, double d2, double d3, double d4){
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
	}
	
	/*public void setResults(double[] t_t, double[] t_p, double[] t_s, double[] t_z){
		this.t_t = t_t; 
		this.t_p = t_p;
		this.t_s = t_s; 
		this.t_z = t_z;
	}*/
	
	public void setResults(Sekcja[] sekcje){
		this.sekcje = sekcje;
	}
	
	/*private void prepareColors(){
		List<Double> list = new ArrayList<Double>();
		for(double d : t_t) list.add(d);
		for(double d : t_p) list.add(d);
		for(double d : t_s) list.add(d);
		for(double d : t_z) list.add(d);
		
		min_temp = Collections.min(list);
		max_temp = Collections.max(list);
	}*/
	
	private void prepareColors(){
		List<Double> list = new ArrayList<Double>();
		for(Sekcja s : sekcje){
			list.add(s.T_t);
			list.add(s.T_p);
			list.add(s.T_s);
			list.add(s.T_z);
		}
		
		min_temp = Collections.min(list);
		max_temp = Collections.max(list);
	}
	
	private Color getColor(double temp){
		
		double s = (max_temp-temp)/(max_temp-min_temp);
		
		
		double H = (1-s) * 0.4; // Hue (note 0.4 = Green, see huge chart below)
	    double S = 0.9; // Saturation
	    double B = 0.9; // Brightness

	    return Color.getHSBColor((float)H, (float)S, (float)B);
	}
	
	public Image vizualize(){
		
		/*if(sections != t_t.length) throw new RuntimeException("Sizes not match");
		if(sections != t_p.length) throw new RuntimeException("Sizes not match");
		if(sections != t_s.length) throw new RuntimeException("Sizes not match");
		if(sections != t_z.length) throw new RuntimeException("Sizes not match");
		*/
		if(sections != sekcje.length) throw new RuntimeException("Sizes not match");
		
		prepareColors();
		
		int Y4 = height-2*MARGIN;
		
		double s = (double)Y4/(double)d4;
		
		
		
		int H1 = (int)(s*d1);
		int H2 = (int)(s*(d2-d1));
		int H3 = (int)(s*(d3-d2));
		int H4 = (int)(s*(d4-d3));
		
		int Y3 = (int)(s*d3);
		int Y2 = (int)(s*d2);
		int Y1 = (int)(s*d1);
		
		
		int y2,y3,y4,y5,y6,y7;
		
		int middleY = (int)(height/2);
		
		y2 = middleY-(Y3/2);
		y7 = middleY+(Y3/2);
		
		y3 = middleY-(Y2/2);
		y6 = middleY+(Y2/2);
		
		y4 = middleY-(Y1/2);
		y5 = middleY+(Y1/2);
		
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		for(int i=0;i<sections;i++){
			
			Sekcja ss = sekcje[i];
			
			Color c = getColor(ss.T_t);
			g.setColor(c);
			g.fillRect((int)(i*section_size), y4, (int)section_size, H1);
			
			c = getColor(ss.T_p);
			g.setColor(c);
			g.fillRect((int)(i*section_size), y3, (int)section_size, H2);
			g.fillRect((int)(i*section_size), y5, (int)section_size, H2);
			
			c = getColor(ss.T_s);
			g.setColor(c);
			g.fillRect((int)(i*section_size), y2, (int)section_size, H3);
			g.fillRect((int)(i*section_size), y6, (int)section_size, H3);
			
			c = getColor(ss.T_z);
			g.setColor(c);
			g.fillRect((int)(i*section_size), MARGIN, (int)section_size, H4);
			g.fillRect((int)(i*section_size), y7, (int)section_size, H4);
			
		}
		
		
		
		return image;
	}

}
