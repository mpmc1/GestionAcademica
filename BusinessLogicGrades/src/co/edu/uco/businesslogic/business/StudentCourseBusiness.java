package co.edu.uco.businesslogic.business;

import co.edu.uco.grades.dto.StudentCourseDTO;

public interface StudentCourseBusiness {
	
	void create(StudentCourseDTO studentCourse);
	void update(StudentCourseDTO studentCourse);
	void delete(int id);
	void find(StudentCourseDTO studentCourse);

}
