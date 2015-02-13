package pl.skimina;

public class Worker implements Runnable {
	
	private Sekcja[] sekcje;
	private Sekcja sekcjaINIT;
	
	
	private double czas;
	private double wsp_Beta = 0.2;
	

	private double sumaCzasu = 0.0;
	
	private ParametrySymulacji params;
	private boolean finished = false;
	
	public Worker(ParametrySymulacji ps) {
		params = ps;
		
		
		
		sekcjaINIT = new Sekcja(ps.t_t, ps.t_s, ps.t_p, ps.t_z);
		
		
		
		sekcje = new Sekcja[ps.ilosc_sekcji];
		for(int i=0;i<ps.ilosc_sekcji;i++){
			sekcje[i] = new Sekcja(ps.t_t, ps.t_s, ps.t_p, ps.t_z);
		}
		
		
		double czas1 = ps.dlugosc_sekcji/ps.v_t;	//[s]
		double czas2 = ps.dlugosc_sekcji/ps.v_s;	//[s]
		czas = Math.abs(czas1 < czas2 ? czas1 : czas2);
		czas /= 3.0;
		
		
	}

	public boolean isFinished() {
		return finished;
	}
	
	private double error;
	
	public void run() {
		
		//error = 200;
		int licznik = 0;
		//double error = 0.0;
		do{
			
			//obliczenie delty temperatur
			for(int i=0;i<sekcje.length;i++){
				Sekcja s = sekcje[i];
				Sekcja sPrev = (i==0 ? sekcjaINIT : sekcje[i-1]);
				
				double temp1;
				double temp2;
				double temp3;
				
				temp1 = 4*params.alfa_t*(s.T_p-s.T_t)/(params.ro_t*params.c_t*params.d1);
				temp2 = params.v_t*(s.T_t-sPrev.T_t)/params.dlugosc_sekcji;
				s.dT_t = (temp1-temp2) * czas;
				
				
				temp1 = 4*params.d1*params.alfa_t*(s.T_t-s.T_p)/(params.ro_p*params.c_p*(params.d2*params.d2-params.d1*params.d1));
				temp2 = 4*params.d2*params.alfa_s*(s.T_s-s.T_p)/(params.ro_p*params.c_p*(params.d2*params.d2-params.d1*params.d1));
				s.dT_p = (temp1 + temp2) * czas;
				
				temp1 = 4*params.d2*params.alfa_s*(s.T_p-s.T_s)/(params.ro_s*params.c_s*(params.d3*params.d3-params.d2*params.d2));
				temp2 = 4*params.d3*params.alfa_3*(s.T_z-s.T_s)/(params.ro_s*params.c_s*(params.d3*params.d3-params.d2*params.d2));
				temp3 = params.v_s*(s.T_s-sPrev.T_s)/params.dlugosc_sekcji;
				s.dT_s = (temp1+temp2-temp3) * czas;
				
				
				temp1 = 4*params.d3*params.alfa_3*(s.T_s-s.T_z)/(params.ro_z*params.c_z*(params.d4*params.d4-params.d3*params.d3));
				s.dT_z = (temp1) * czas;
				
			}
			
			
			error = 0;
			//zaktualizowanie temperatur w sekcjach
			for(Sekcja s : sekcje){
				s.T_t += s.dT_t*wsp_Beta;
				s.T_p += s.dT_p*wsp_Beta;
				s.T_s += s.dT_s*wsp_Beta;
				s.T_z += s.dT_z*wsp_Beta;
				
				error += (s.dT_t + s.dT_p + s.dT_s + s.dT_z)*wsp_Beta;
				
			}
			
			
			licznik++;
			sumaCzasu += czas;
			//error = error-1;
			if(licznik > 1000000) break;
		}while(Math.abs(error)>0.00001);
		
		System.out.println("==========================");
		System.out.println("Ilość przebiegów pętli:" +licznik);		
		System.out.println("Suma czasu:" +sumaCzasu);
		System.out.println("==========================");
		
		finished = true;
	}
	
	
	public Data getCurrentData(){
		
		double[] t_t = new double[params.ilosc_sekcji];
		double[] t_p = new double[params.ilosc_sekcji];
		double[] t_s = new double[params.ilosc_sekcji];
		double[] t_z = new double[params.ilosc_sekcji];
		
		for(int i=0;i<params.ilosc_sekcji;i++){
			Sekcja s = sekcje[i];
			t_t[i]= s.T_t-273.0;
			t_p[i]= s.T_p-273.0;
			t_s[i]= s.T_s-273.0;
			t_z[i]= s.T_z-273.0;
		}
		
		return new Data(params.ilosc_sekcji, t_t, t_p, t_s, t_z);
	}
	
	public Sekcja[] getSekcje(){
		return sekcje;
	}

}
