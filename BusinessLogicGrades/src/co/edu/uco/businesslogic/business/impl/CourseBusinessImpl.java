package co.edu.uco.businesslogic.business.impl;

import co.edu.uco.businesslogic.business.CourseBusiness;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.factory.DAOFactory;
import co.edu.uco.grades.dto.CourseDTO;

public class CourseBusinessImpl implements CourseBusiness{
	
	private DAOFactory daoFactory;
	
	public CourseBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}
	

	@Override
	public void create(CourseDTO course) {
		daoFactory.getCourseDAO().create(course);
		
	}

	@Override
	public void update(CourseDTO course) {
		daoFactory.getCourseDAO().update(course);
		
	}

	@Override
	public void delete(int id) {
		daoFactory.getCourseDAO().delete(id);
		
	}

	@Override
	public void find(CourseDTO course) {
		daoFactory.getCourseDAO().find(course);
		
	}

}
