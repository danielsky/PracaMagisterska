package pl.skimina;

import java.awt.Color;
import java.io.Serializable;

public class Data implements Serializable{
	public String[] labels;
	public Dataset[] datasets;
	
	
	public Data(ParametrySymulacji ps) {
		labels = new String[ps.ilosc_sekcji];
		for(int i=0;i<ps.ilosc_sekcji;i++){
			labels[i] = Integer.toString(i+1);
		}
		
		datasets = new Dataset[4];
		datasets[0] = new Dataset("Płyn wew", ps.ilosc_sekcji, ps.t_t, new Color(220, 220, 220));
		datasets[1] = new Dataset("scianka wewn", ps.ilosc_sekcji, ps.t_p, new Color(200, 200, 200));
		datasets[2] = new Dataset("plyn zew", ps.ilosc_sekcji, ps.t_s, new Color(180, 180, 180));
		datasets[3] = new Dataset("scianka zewn", ps.ilosc_sekcji, ps.t_z, new Color(160, 160, 160));
	}
	
	public Data(int size, double[] t_t, double[] t_p, double[] t_s, double[] t_z) {
		labels = new String[size];
		for(int i=0;i<size;i++){
			labels[i] = Integer.toString(i+1);
		}
		
		datasets = new Dataset[4];
		datasets[0] = new Dataset("Płyn wew", t_t, new Color(220, 220, 220));
		datasets[1] = new Dataset("scianka wewn", t_p, new Color(200, 200, 200));
		datasets[2] = new Dataset("plyn zew", t_s, new Color(180, 180, 180));
		datasets[3] = new Dataset("scianka zewn", t_z, new Color(160, 160, 160));
	}
	
	
}
