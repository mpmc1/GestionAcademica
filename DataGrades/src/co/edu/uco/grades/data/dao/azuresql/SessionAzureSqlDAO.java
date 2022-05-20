package co.edu.uco.grades.data.dao.azuresql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.dao.SessionDAO;
import co.edu.uco.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.dto.SessionDTO;

public class SessionAzureSqlDAO extends ConnectionSQL implements SessionDAO {

	protected SessionAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static SessionDAO build(Connection connection) {
		return new SessionAzureSqlDAO(connection);
	}

	@Override
	public void create(SessionDTO session) {
		String sql = "INSERT INTO IdType(course, date) VALUES(?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, session.getCourse().getId());
			preparedStatement.setDate(2, new Date(session.getDate().getTime()));

		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to create a new session registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new session registry on sql server",
					exception);

		}

	}

	@Override
	public void update(SessionDTO session) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void find(SessionDTO session) {
		// TODO Auto-generated method stub

	}

}
