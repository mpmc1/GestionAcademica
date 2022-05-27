package co.edu.uco.businesslogic.business;

import co.edu.uco.grades.dto.SubjectDTO;

public interface SubjectBusiness {
	
	void create(SubjectDTO subject);
	void update(SubjectDTO subject);
	void delete(int id);
	void find(SubjectDTO subject);

}
