package co.edu.uco.grades.businesslogic.business;

import co.edu.uco.grades.dto.StudentDTO;

public interface StudentBusiness {
	
	void create(StudentDTO student);
	void update(StudentDTO student);
	void delete(int id);
	void find(StudentDTO student);


}
