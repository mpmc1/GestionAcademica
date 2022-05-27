package co.edu.uco.grades.data.dao.azuresql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.dao.CourseDAO;
import co.edu.uco.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.dto.CourseDTO;

public class CourseAzureSqlDAO extends ConnectionSQL implements CourseDAO {

	protected CourseAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static CourseDAO build(Connection connection) {
		return new CourseAzureSqlDAO(connection);
	}

	@Override
	public void create(CourseDTO course) {
		String sql = "INSERT INTO Course(subject, professor, initialDate, finalDate) VALUES(?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, course.getSubject().getId());
			preparedStatement.setInt(2, course.getProfessor().getId());
			preparedStatement.setDate(3, new Date(course.getInitialDate().getTime()));
			preparedStatement.setDate(4, new Date(course.getFinalDate().getTime()));
		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to create a new course registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new course registry on sql server",
					exception);

		}

	}

	@Override
	public void update(CourseDTO course) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Course WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to delete a course registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a course registry on sql server", exception);

		}

	}

	@Override
	public void find(CourseDTO course) {
		// TODO Auto-generated method stub

	}

}
