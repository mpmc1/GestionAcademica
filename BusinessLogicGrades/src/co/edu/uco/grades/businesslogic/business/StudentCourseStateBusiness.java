package co.edu.uco.grades.businesslogic.business;

import co.edu.uco.grades.dto.StudentCourseStateDTO;

public interface StudentCourseStateBusiness {
	
	void create(StudentCourseStateDTO studentCourseState);
	void update(StudentCourseStateDTO studentCourseState);
	void delete(int id);
	void find(StudentCourseStateDTO studentCourseState);

}
