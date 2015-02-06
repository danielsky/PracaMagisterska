package pl.skimina;

public enum MaterialRura {
	
	STAL(1, "Stal", 7500, 500),
	ALUMINIUM(2, "Aluminium", 2720, 900),
	INNY(0, "Inny...", 0, 0);
	
	private double ro;
	private double c;
	private String name;
	private int code;
	
	private MaterialRura(int code, String name, double ro, double c) {
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
	
	public static MaterialRura getByID(int id){
		for(MaterialRura mat : values()){
			if(mat.code == id) return mat;
		}
		
		return null;
	}

}
