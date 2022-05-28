package co.edu.uco.grades.businesslogic.facade.impl;

import java.util.List;

import co.edu.uco.businesslogic.business.impl.IdTypeBussinesImpl;
import co.edu.uco.grades.businesslogic.business.IdTypeBusiness;
import co.edu.uco.grades.businesslogic.facade.IdTypeFacade;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.factory.DAOFactory;
import co.edu.uco.grades.dto.IdTypeDTO;

public class IdTypeFacadeImpl  implements IdTypeFacade{
	

	@Override
	public void create(IdTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBussinesImpl(daoFactory);
			idTypeBusiness.create(dto);
			
			daoFactory.commitTransaction();
			
		} catch (GradesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to create the new IdType on 'create' method of IdTypeFacadeImpl";
			throw GradesException.buildTechnicalBussinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
		
	}

	@Override
	public void update(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBussinesImpl(daoFactory);
			idTypeBusiness.update(dto);
			
			daoFactory.commitTransaction();
			
		} catch (GradesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to update the IdType on 'update' method of IdTypeFacadeImpl";
			throw GradesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
		
		
	}

	@Override
	public void delete(int id) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBussinesImpl(daoFactory);
			idTypeBusiness.delete(id);
			
			daoFactory.commitTransaction();
			
		} catch (GradesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to delete the IdType on 'delete' method of IdTypeFacadeImpl";
			throw GradesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
		
		
	}

	@Override
	public List<IdTypeDTO> find(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBussinesImpl(daoFactory);
			return idTypeBusiness.find(dto);
			
		} catch (GradesException exception){
			throw exception;
			
		} catch (Exception exception){
			var message = "There was an unexpected problem trying to find the new IdType on 'find' method of IdTypeFacadeImpl";
			throw GradesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
		
	}


}
