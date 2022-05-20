package co.edu.uco.grades.data.dao;

import co.edu.uco.grades.dto.AttendanceDTO;

public interface AttendanceDAO {
	
	void create(AttendanceDTO attendance);
	void update(AttendanceDTO attendance);
	void delete(int id);
	void find(AttendanceDTO attendance);

}
