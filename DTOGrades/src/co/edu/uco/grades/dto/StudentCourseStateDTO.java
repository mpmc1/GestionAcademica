package co.edu.uco.grades.dto;

import co.edu.uco.crosscutting.util.text.UtilText;

public class StudentCourseStateDTO {
	
	private int id;
	private String name;
	
	public StudentCourseStateDTO(int id, String name) {
		super();
		setId(id);
		setName(name);
	}
	
	public StudentCourseStateDTO() {
		super();
		setName(UtilText.EMPTY);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = UtilText.trim(name);
	}
	
	

}
