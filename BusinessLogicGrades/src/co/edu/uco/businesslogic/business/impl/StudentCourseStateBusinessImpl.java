package co.edu.uco.businesslogic.business.impl;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.businesslogic.business.StudentCourseStateBusiness;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.factory.DAOFactory;
import co.edu.uco.grades.dto.StudentCourseStateDTO;

public class StudentCourseStateBusinessImpl implements StudentCourseStateBusiness{
	
	
	private DAOFactory daoFactory;
	
	public StudentCourseStateBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(StudentCourseStateDTO studentCourseState) {
		daoFactory.getStudentCourseStateDAO().create(studentCourseState);
	}

	@Override
	public void update(StudentCourseStateDTO studentCourseState) {
		daoFactory.getStudentCourseStateDAO().update(studentCourseState);
	}

	@Override
	public void delete(int id) {
		daoFactory.getStudentCourseStateDAO().delete(id);		
	}

	@Override
	public void find(StudentCourseStateDTO studentCourseState) {
		daoFactory.getStudentCourseStateDAO().find(studentCourseState);		
	}

}
