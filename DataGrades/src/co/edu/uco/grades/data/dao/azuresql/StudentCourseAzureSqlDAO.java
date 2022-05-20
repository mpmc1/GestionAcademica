package co.edu.uco.grades.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.dao.StudentCourseDAO;
import co.edu.uco.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.dto.StudentCourseDTO;

public class StudentCourseAzureSqlDAO extends ConnectionSQL implements StudentCourseDAO {

	protected StudentCourseAzureSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static StudentCourseDAO build(Connection connection) {
		return new StudentCourseAzureSqlDAO(connection);
	}

	@Override
	public void create(StudentCourseDTO studentCourse) {
		String sql = "INSERT INTO IdType(student, course, state) VALUES(?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, studentCourse.getStudent().getId());
			preparedStatement.setInt(2, studentCourse.getCourse().getId());
			preparedStatement.setInt(2, studentCourse.getState().getId());

		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to create a new student course registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new student course registry on sql server",
					exception);

		}
		
	}

	@Override
	public void update(StudentCourseDTO studentCourse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(StudentCourseDTO studentCourse) {
		// TODO Auto-generated method stub
		
	}

}
