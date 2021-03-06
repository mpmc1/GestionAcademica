package co.edu.uco.grades.dto;

import static co.edu.uco.crosscutting.util.object.UtilObject.getUtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class StudentDTO {

	private int id;
	private String idNumber;
	private IdTypeDTO idType;
	private String name;
	private String email;
	
	
	
	public StudentDTO(int id, String idNumber, IdTypeDTO idType, String name, String email) {
		super();
		setId(id);
		setIdNumber(idNumber);
		setIdType(idType);
		setName(name);
		setEmail(email);
	}

	public StudentDTO() {
		setEmail(UtilText.EMPTY);
		setIdNumber(UtilText.EMPTY);
		setIdType(idType);
		setName(UtilText.EMPTY);
		
	}

	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = UtilText.trim(idNumber);
	}

	public IdTypeDTO getIdType() {
		return idType;
	}

	public void setIdType(IdTypeDTO idType) {
		this.idType = getUtilObject().getDefault(idType, new IdTypeDTO());
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = UtilText.trim(name);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = UtilText.trim(email);
	}
	
	
	
}
