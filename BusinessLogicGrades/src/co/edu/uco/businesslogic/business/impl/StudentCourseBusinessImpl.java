package co.edu.uco.businesslogic.business.impl;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.businesslogic.business.StudentCourseBusiness;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.factory.DAOFactory;
import co.edu.uco.grades.dto.StudentCourseDTO;

public class StudentCourseBusinessImpl implements StudentCourseBusiness{
	
	private DAOFactory daoFactory;
	
	public StudentCourseBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(StudentCourseDTO studentCourse) {
		daoFactory.getStudentCourseDAO().create(studentCourse);
	}

	@Override
	public void update(StudentCourseDTO studentCourse) {
		daoFactory.getStudentCourseDAO().update(studentCourse);
	}

	@Override
	public void delete(int id) {
		daoFactory.getStudentCourseDAO().delete(id);
	}

	@Override
	public void find(StudentCourseDTO studentCourse) {
		daoFactory.getStudentCourseDAO().find(studentCourse);
	}

}
