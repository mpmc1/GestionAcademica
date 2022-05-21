package co.edu.uco.grades.dto;

import co.edu.uco.crosscutting.util.object.UtilObject;

public class AttendanceDTO {
	
	private int id;
	private StudentCourseDTO studentCourse;
	private SessionDTO session;
	private boolean attended;
	
	public AttendanceDTO(int id, StudentCourseDTO studentCourse, SessionDTO session, boolean attended) {
		super();
		setId(id);
		setStudentCourse(studentCourse);
		setSession(session);
		setAttended(attended);
	}

	public AttendanceDTO() {
		super();
		setSession(new SessionDTO());
		setStudentCourse(new StudentCourseDTO());
		setAttended(false);
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

	public boolean getAttended() {
		return attended;
	}

	public void setAttended(boolean attended) {
		this.attended = UtilObject.getUtilObject().getDefault(attended, false);
	}
	
	

}
