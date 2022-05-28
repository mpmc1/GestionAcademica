package co.edu.uco.grades.businesslogic.business;

import co.edu.uco.grades.dto.ProfessorDTO;

public interface ProfessorBusiness {
	
	void create(ProfessorDTO professor);
	void update(ProfessorDTO professor);
	void delete(int id);
	void find(ProfessorDTO professor);

}
