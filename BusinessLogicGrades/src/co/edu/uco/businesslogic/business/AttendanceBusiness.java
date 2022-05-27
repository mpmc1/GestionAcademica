package co.edu.uco.businesslogic.business;

import co.edu.uco.grades.dto.AttendanceDTO;

public interface AttendanceBusiness {
	
	void create(AttendanceDTO attendance);
	void update(AttendanceDTO attendance);
	void delete(int id);
	void find(AttendanceDTO attendance);

}
