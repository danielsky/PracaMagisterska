package pl.skimina;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

public class Dataset implements Serializable{
	
	public Dataset(String label, int size, double initTemp, Color c) {
		
		this.label = label;
		this.fillColor = colorConverter(c, 0.2);
		this.strokeColor = colorConverter(c, 1);
		this.pointColor = colorConverter(c, 1);
		this.pointHighlightStroke = colorConverter(c, 1);
		
		this.data = new double[size];
		Arrays.fill(data, initTemp);
	}
	
	public Dataset(String label, double[] temp, Color c) {
		
		this.label = label;
		this.fillColor = colorConverter(c, 0.2);
		this.strokeColor = colorConverter(c, 1);
		this.pointColor = colorConverter(c, 1);
		this.pointHighlightStroke = colorConverter(c, 1);
		
		this.data = temp;
	}
	
	public String label;
	public String fillColor;
	public String strokeColor;
	public String pointColor;
	public String pointStrokeColor = "#fff";
	public String pointHighlightFill = "#fff";
	public String pointHighlightStroke;
	public double data[];
	
	private static String colorConverter(Color c, double alpha){
		//return "#"+Integer.toHexString(c.getRGB()).substring(2);
		return String.format(Locale.US, "rgba(%d,%d,%d,%f)", c.getRed(), c.getGreen(), c.getBlue(), alpha);
	}
}
