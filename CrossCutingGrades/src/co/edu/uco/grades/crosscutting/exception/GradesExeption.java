package co.edu.uco.grades.crosscutting.exception;

import co.edu.uco.crosscutting.exception.GeneralException;
import co.edu.uco.grades.crosscutting.exception.enumeration.ExceptionLocation;
import co.edu.uco.grades.crosscutting.exception.enumeration.ExceptionType;
import static co.edu.uco.crosscutting.util.object.UtilObject.getUtilObject;

public class GradesExeption extends GeneralException {

	private GradesExeption(String userMessage, String technicalMessage, Exception rootException, ExceptionType type,
			ExceptionLocation location) {
		super(userMessage, technicalMessage, rootException);
		setType(type);
		setLocation(location);
	}

	public static GradesExeption buildUserException(String userMessage) {
		return new GradesExeption(userMessage, userMessage, null, null, null);
	}
	public static GradesExeption buildTechnicalException(String technicalMessage) {
		return new GradesExeption(null, technicalMessage, null, null, null);
	}

	public static GradesExeption build(String userMessage, String technicalMessage) {
		return new GradesExeption(userMessage, technicalMessage, null, null, null);
	}

	public static GradesExeption build(String userMessage, String technicalMessage, Exception rootException) {
		return new GradesExeption(userMessage, technicalMessage, rootException, null, null);
	}

	private static final long serialVersionUID = 625249639280789375L;

	private ExceptionType type;
	private ExceptionLocation location;

	private void setType(ExceptionType type) {
		this.type = getUtilObject().getDefault(type, ExceptionType.GENERAL);
	}

	private void setLocation(ExceptionLocation location) {
		this.location = location;
	}

	public ExceptionType getType() {
		return type;
	}

}
