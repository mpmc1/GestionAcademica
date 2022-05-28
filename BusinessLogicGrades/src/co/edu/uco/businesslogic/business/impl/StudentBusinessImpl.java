package co.edu.uco.businesslogic.business.impl;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.businesslogic.business.StudentBusiness;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.factory.DAOFactory;
import co.edu.uco.grades.dto.StudentDTO;

public class StudentBusinessImpl implements StudentBusiness{

	
	private DAOFactory daoFactory;
	
	public StudentBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(StudentDTO student) {
		daoFactory.getStudentDAO().create(student);
		
	}

	@Override
	public void update(StudentDTO student) {
		daoFactory.getStudentDAO().update(student);
	}

	@Override
	public void delete(int id) {
		daoFactory.getStudentDAO().delete(id);
	}

	@Override
	public void find(StudentDTO student) {
		daoFactory.getStudentDAO().find(student);
	}

}
