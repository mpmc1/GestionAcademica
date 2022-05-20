package co.edu.uco.grades.dto;

import co.edu.uco.crosscutting.util.text.UtilText;

public class SubjectDTO {
	
	private int id;
	private String name;
	
	
	public SubjectDTO() {
		super();
		setName(UtilText.EMPTY);
	}
	
	public SubjectDTO(int id, String name) {
		super();
		setId(id);
		setName(name);
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
	private void setName(String name) {
		this.name = UtilText.trim(name);
	}

}
