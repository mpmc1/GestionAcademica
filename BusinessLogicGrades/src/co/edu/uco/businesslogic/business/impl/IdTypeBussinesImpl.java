package co.edu.uco.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.businesslogic.business.IdTypeBusiness;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.factory.DAOFactory;
import co.edu.uco.grades.dto.IdTypeDTO;
import co.edu.uco.grades.dto.ProfessorDTO;
import co.edu.uco.grades.dto.StudentDTO;

public class IdTypeBussinesImpl implements IdTypeBusiness {
	
	private DAOFactory daoFactory;
	
	public IdTypeBussinesImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(IdTypeDTO dto) {
		validateIdTypeDeosNotExistWithSameName(dto);
		daoFactory.getIdtypeDAO().create(dto);
		
		
	}
	
	private void validateIdTypeDeosNotExistWithSameName(IdTypeDTO dto) {
		IdTypeDTO dtoValidator = new IdTypeDTO();
		dtoValidator.setName(dto.getName());
		
		List<IdTypeDTO> list = daoFactory.getIdtypeDAO().find(dtoValidator);
		
		if(!list.isEmpty()) {
			var message = "An id type with the same name already exist";
			throw GradesException.buildBussinessLogicException(message);
		}
		
	}
	
	private void validateIfExist(IdTypeDTO dto) {
		List<IdTypeDTO> existingIdType = daoFactory.getIdtypeDAO().find(dto);
		if(existingIdType.isEmpty()) {
			throw GradesException.buildBussinessLogicException("id type to not found");
		}
		
	}
	
	private void validateIfIsUsed(IdTypeDTO dto) {
		var used = false;
		List<StudentDTO> students = daoFactory.getStudentDAO().find(new StudentDTO());
		List<ProfessorDTO> professors = daoFactory.getProfessorDAO().find(new ProfessorDTO());
		
		if(!students.isEmpty()) {
			for (int index = 0; index < students.size(); index++) {
				if(students.get(index).getIdType().getId() == dto.getId()) {
					used = true;
				}
			}
		}
		
		if(!used && !professors.isEmpty()) {
			for (int index = 0; index < professors.size(); index++) {
				if(professors.get(index).getIdType().getId() == dto.getId()) {
					used = true;
				}
			}
		}
		
		if(used) {
			throw GradesException.buildBussinessLogicException("id type is used by other(s) entity(ies)");
		}
		
	}

	@Override
	public void update(IdTypeDTO dto) {
		validateIdTypeDeosNotExistWithSameName(dto);
		daoFactory.getIdtypeDAO().update(dto);
		
	}

	@Override
	public void delete(int id) {
		IdTypeDTO dto = new IdTypeDTO(id, "");
		validateIfExist(dto);
		validateIfIsUsed(dto);
		daoFactory.getIdtypeDAO().delete(id);
		
	}

	@Override
	public List<IdTypeDTO> find(IdTypeDTO dto) {	
		return daoFactory.getIdtypeDAO().find(dto);
	}

}
