package co.edu.uco.businesslogic.business.impl;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.businesslogic.business.AttendanceBusiness;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.factory.DAOFactory;
import co.edu.uco.grades.dto.AttendanceDTO;

public class AttendanceBusinessImpl implements AttendanceBusiness {
	
	private DAOFactory daoFactory;
		
	public AttendanceBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(AttendanceDTO attendance) {
		daoFactory.getAttendanceDAO().create(attendance);
		
	}

	@Override
	public void update(AttendanceDTO attendance) {
		daoFactory.getAttendanceDAO().update(attendance);
		
	}

	@Override
	public void delete(int id) {
		daoFactory.getAttendanceDAO().delete(id);
		
	}

	@Override
	public void find(AttendanceDTO attendance) {
		daoFactory.getAttendanceDAO().find(attendance);
		
	}

}
