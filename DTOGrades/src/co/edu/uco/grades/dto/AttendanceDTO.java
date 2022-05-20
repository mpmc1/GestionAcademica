package co.edu.uco.grades.dto;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class AttendanceDTO {
	
	private int id;
	private StudentCourseDTO studentCourse;
	private SessionDTO session;
	private Byte attended;
	
	public AttendanceDTO(int id, StudentCourseDTO studentCourse, SessionDTO session, Byte attended) {
		super();
		setId(id);
		setStudentCourse(studentCourse);
		setSession(session);
		setAttended(attended);
	}

	public AttendanceDTO() {
		super();
		Byte ZERO = 0;
		setSession(new SessionDTO());
		setStudentCourse(new StudentCourseDTO());
		setAttended(ZERO);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StudentCourseDTO getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(StudentCourseDTO studentCourse) {
		this.studentCourse = UtilObject.getUtilObject().getDefault(studentCourse, new StudentCourseDTO());
	}

	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = UtilObject.getUtilObject().getDefault(session, new SessionDTO());
	}

	public Byte getAttended() {
		return attended;
	}

	public void setAttended(Byte attended) {
		Byte ZERO = 0;
		this.attended = UtilNumeric.getUtilNumeric().getDefault(attended, ZERO);
	}
	
	

}
