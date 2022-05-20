package co.edu.uco.grades.dto;

import co.edu.uco.crosscutting.util.object.UtilObject;

public class StudentCourseDTO {
	
	private int id;
	private StudentDTO student;
	private CourseDTO course;
	private StudentCourseStateDTO state;
	
	public StudentCourseDTO(int id, StudentDTO student, CourseDTO course, StudentCourseStateDTO state) {
		super();
		setId(id);
		setStudent(student);
		setCourse(course);
		setState(state);
	}

	public StudentCourseDTO() {
		super();
		setStudent(new StudentDTO());
		setCourse(new CourseDTO());
		setState(new StudentCourseStateDTO());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = UtilObject.getUtilObject().getDefault(student, new StudentDTO());
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = UtilObject.getUtilObject().getDefault(course, new CourseDTO());
	}

	public StudentCourseStateDTO getState() {
		return state;
	}

	public void setState(StudentCourseStateDTO state) {
		this.state = UtilObject.getUtilObject().getDefault(state, new StudentCourseStateDTO());;
	}
	
	

}
