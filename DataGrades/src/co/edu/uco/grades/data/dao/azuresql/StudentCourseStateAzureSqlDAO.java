package co.edu.uco.grades.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.dao.StudentCourseStateDAO;
import co.edu.uco.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.dto.StudentCourseStateDTO;

public class StudentCourseStateAzureSqlDAO extends ConnectionSQL implements StudentCourseStateDAO {

	protected StudentCourseStateAzureSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static StudentCourseStateDAO build(Connection connection) {
		return new StudentCourseStateAzureSqlDAO(connection);
	}

	@Override
	public void create(StudentCourseStateDTO studentCourseState) {
		String sql = "INSERT INTO IdType(name) VALUES(?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, studentCourseState.getName());

		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to create a new student course state registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new student course state registry on sql server",
					exception);

		}
		
	}

	@Override
	public void update(StudentCourseStateDTO studentCourseState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(StudentCourseStateDTO studentCourseState) {
		// TODO Auto-generated method stub
		
	}

}
