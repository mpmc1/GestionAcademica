package co.edu.uco.grades.api.controller.validators.idtype;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.api.controller.validators.Validator;
import co.edu.uco.grades.businesslogic.facade.IdTypeFacade;
import co.edu.uco.grades.businesslogic.facade.impl.IdTypeFacadeImpl;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.grades.dto.IdTypeDTO;

public class FindByIdIdTypeValidator implements Validator<IdTypeDTO>{
	
	private List<String> validationMessages = new ArrayList<>();
	IdTypeFacade facade = new IdTypeFacadeImpl();

	@Override
	public List<String> validate(IdTypeDTO dto) {
		if (UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("Is not possible to validate Id Type data");
		}
		dto.validateId(validationMessages);
		validateIfExist(dto);
		return validationMessages;
	}
	
	private void validateIfExist(IdTypeDTO dto) {
		try {
			List<IdTypeDTO> existingIdType = facade.find(dto);
			if(existingIdType.isEmpty()) {
				validationMessages.add("id type not found");
			}
			
		} catch (GradesException exception) {
			if (ExceptionType.TECHNICAL.equals(exception.getType())) {
				validationMessages.add("There was a problem trying to find the id type. Please, try again");
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
			validationMessages.add("There was an unexpected problem trying to find the id type");
			exception.printStackTrace();
		}
	}

}
