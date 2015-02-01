package pl.skimina;

public enum MaterialPlyn {
	
	WODA(1, "Woda", 1000, 4187),
	OLEJ(2, "Olej", 920, 1885),
	INNY(0, "Inny...", 0, 0);
	
	private double ro;
	private double c;
	private String name;
	private int code;
	
	private MaterialPlyn(int code, String name, double ro, double c) {
		this.code = code;
		this.name = name;
		this.ro = ro;
		this.c = c;
	}
	
	public double getC() {
		return c;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public double getRo() {
		return ro;
	}

}
