package co.edu.uco.grades.api.controller.validators.idtype;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.grades.api.controller.validators.Validator;
import co.edu.uco.grades.businesslogic.facade.IdTypeFacade;
import co.edu.uco.grades.businesslogic.facade.impl.IdTypeFacadeImpl;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.grades.dto.IdTypeDTO;

public class DeleteIdTypeValidator implements Validator<IdTypeDTO>{
	
	private List<String> validationMessages = new ArrayList<>();
	IdTypeFacade facade = new IdTypeFacadeImpl();

	@Override
	public List<String> validate(IdTypeDTO dto) {
		if (validateIfExist(dto)) {
			validateIfIsUsed(dto);
		}

		return validationMessages;
	}
	
	private boolean validateIfExist(IdTypeDTO dto) {
		try {
			List<IdTypeDTO> existingIdType = facade.find(dto);
			if(existingIdType.isEmpty()) {
				validationMessages.add("id type to delete not found");
			}else {
				return true;
			}
			
		} catch (GradesException exception) {
			if (ExceptionType.TECHNICAL.equals(exception.getType())) {
				validationMessages.add("There was a problem trying to find the id type to delete. Please, try again");
				System.err.println(exception.getLocation());
				System.err.println(exception.getType());
				System.err.println(exception.getTechnicalMessage());
				exception.getRootException().printStackTrace();
			} else {
				validationMessages.add(exception.getMessage());
				System.err.println(exception.getLocation());
				System.err.println(exception.getType());
				System.err.println(exception.getUserMessage());
				exception.getRootException().printStackTrace();
			}
		} catch (Exception exception) {
			validationMessages.add("There was an unexpected problem trying to find the id type to delete");
			exception.printStackTrace();
		}
		return false;
	}
	
	private boolean validateIfIsUsed(IdTypeDTO dto) {
		var used = false;
		try {
			List<IdTypeDTO> usedIdTypes = facade.findUsedIdTypes();
			if(!usedIdTypes.isEmpty()) {
				for(int index = 0; index < usedIdTypes.size(); index++) {
					if(usedIdTypes.get(index).getId() == dto.getId()) {
						used = true;
					}
				}
				if(used) {
					validationMessages.add("Can't delete the id type because is already used");
				}
			}
			
		} catch (GradesException exception) {
			if (ExceptionType.TECHNICAL.equals(exception.getType())) {
				validationMessages.add("There was a problem trying to find if id type to delete is used. Please try again");
				System.err.println(exception.getLocation());
				System.err.println(exception.getType());
				System.err.println(exception.getTechnicalMessage());
				exception.getRootException().printStackTrace();
			} else {
				validationMessages.add(exception.getMessage());
				System.err.println(exception.getLocation());
				System.err.println(exception.getType());
				System.err.println(exception.getUserMessage());
				exception.getRootException().printStackTrace();
			}
		} catch (Exception exception) {
			validationMessages.add("There was an unexpected problem trying to find if the id type to delete is used");
			exception.printStackTrace();
		}
		return false;
	}

}
