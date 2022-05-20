package co.edu.uco.grades.dto;

import co.edu.uco.crosscutting.util.text.UtilText;

public class IdTypeDTO {
	
	private int id;
	private String name;
	
	
	
	public IdTypeDTO(int id, String name) {
		super();
		setId(id);
		setName(name);
	}


	public IdTypeDTO() {
		super();
		setName(UtilText.EMPTY);
	}
	
	
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = UtilText.trim(name);
	}

}