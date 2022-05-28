package co.edu.uco.grades.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.api.controller.response.Response;
import co.edu.uco.grades.api.controller.validators.Validator;
import co.edu.uco.grades.api.controller.validators.idtype.CreateIdTypeValidator;
import co.edu.uco.grades.businesslogic.facade.IdTypeFacade;
import co.edu.uco.grades.businesslogic.facade.impl.IdTypeFacadeImpl;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.grades.dto.IdTypeDTO;

@RestController
@RequestMapping("/api/v1/idtype")
public class IdTypeController {

	@PostMapping()
	public ResponseEntity<Response<IdTypeDTO>> create(@RequestBody IdTypeDTO dto) {
		Validator<IdTypeDTO> validator = new CreateIdTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		if (messages.isEmpty()) {
			try {
				IdTypeFacade facade = new IdTypeFacadeImpl();
				facade.create(dto);
				messages.add("IdType was created successfully");
				statusCode = HttpStatus.OK;
			} catch (GradesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new id type: Please, try again");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getUserMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				}
			} catch (Exception exception) {
				messages.add("There was an unexpected problem trying to create the new id type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<IdTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@PutMapping("/{id}")
	public void update(@PathVariable("id") int id, @RequestBody IdTypeDTO dto) {
		System.out.println("actualizando...");
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		System.out.println("Eliminando...");
	}

	@GetMapping("/{id}")
	public void findById(@PathVariable("id") int id) {
		System.out.println("Buscando...");
	}

	@GetMapping()
	public ResponseEntity<Response<IdTypeDTO>> find() {
		List<String> messages = new ArrayList<>();
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		try {
			IdTypeFacade facade = new IdTypeFacadeImpl();
			response.setData(facade.find(new IdTypeDTO()));
			messages.add("IdTypes were found successfully");
			statusCode = HttpStatus.OK;
		} catch (GradesException exception) {
			if (ExceptionType.TECHNICAL.equals(exception.getType())) {
				messages.add("There was a problem trying to find the id types Please, try again");
				System.err.println(exception.getLocation());
				System.err.println(exception.getType());
				System.err.println(exception.getMessage());
				exception.getRootException().printStackTrace();
			} else {
				messages.add(exception.getMessage());
				System.err.println(exception.getLocation());
				System.err.println(exception.getType());
				System.err.println(exception.getUserMessage());
				exception.getRootException().printStackTrace();
			}
		} catch (Exception exception) {
			messages.add("There was an unexpected problem trying to find the id types");
			exception.printStackTrace();
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<IdTypeDTO>>(response, statusCode);

		return responseEntity;
	}

}
