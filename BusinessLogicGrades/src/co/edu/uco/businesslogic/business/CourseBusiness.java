package co.edu.uco.businesslogic.business;

import co.edu.uco.grades.dto.CourseDTO;

public interface CourseBusiness {
	
	void create(CourseDTO course);
	void update(CourseDTO course);
	void delete(int id);
	void find(CourseDTO course);

}
