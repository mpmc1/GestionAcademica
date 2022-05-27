package co.edu.uco.businesslogic.business.impl;

import co.edu.uco.businesslogic.business.SessionBusiness;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.factory.DAOFactory;
import co.edu.uco.grades.dto.SessionDTO;

public class SessionBusinessImpl implements SessionBusiness{
	
	private DAOFactory daoFactory;
	
	public SessionBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(SessionDTO session) {
		daoFactory.getSessionDAO().create(session);
	}

	@Override
	public void update(SessionDTO session) {
		daoFactory.getSessionDAO().update(session);
	}

	@Override
	public void delete(int id) {
		daoFactory.getSessionDAO().delete(id);
	}

	@Override
	public void find(SessionDTO session) {
		daoFactory.getSessionDAO().find(session);
	}

}
