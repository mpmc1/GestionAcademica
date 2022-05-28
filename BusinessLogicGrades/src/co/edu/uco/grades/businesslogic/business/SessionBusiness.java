package co.edu.uco.grades.businesslogic.business;

import co.edu.uco.grades.dto.SessionDTO;

public interface SessionBusiness {
	
	void create(SessionDTO session);
	void update(SessionDTO session);
	void delete(int id);
	void find(SessionDTO session);

}
