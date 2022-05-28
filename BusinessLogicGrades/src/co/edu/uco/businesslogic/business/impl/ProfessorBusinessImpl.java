package co.edu.uco.businesslogic.business.impl;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.businesslogic.business.ProfessorBusiness;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.factory.DAOFactory;
import co.edu.uco.grades.dto.ProfessorDTO;

public class ProfessorBusinessImpl implements ProfessorBusiness{
	
	private DAOFactory daoFactory;
	
	public ProfessorBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}
	
	

	@Override
	public void create(ProfessorDTO professor) {
		daoFactory.getProfessorDAO().create(professor);
		
	}

	@Override
	public void update(ProfessorDTO professor) {
		daoFactory.getProfessorDAO().update(professor);
		
	}

	@Override
	public void delete(int id) {
		daoFactory.getProfessorDAO().delete(id);
		
	}

	@Override
	public void find(ProfessorDTO professor) {
		daoFactory.getProfessorDAO().find(professor);
		
	}

}
