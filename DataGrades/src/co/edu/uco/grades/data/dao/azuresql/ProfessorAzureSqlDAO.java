package co.edu.uco.grades.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.dao.ProfessorDAO;
import co.edu.uco.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.dto.ProfessorDTO;

public class ProfessorAzureSqlDAO extends ConnectionSQL implements ProfessorDAO {

	protected ProfessorAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static ProfessorDAO build(Connection connection) {
		return new ProfessorAzureSqlDAO(connection);
	}

	@Override
	public void create(ProfessorDTO professor) {
		String sql = "INSERT INTO IdType(idNumber, idType, name, email) VALUES(?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, professor.getIdNumber());
			preparedStatement.setInt(2, professor.getIdType().getId());
			preparedStatement.setString(3, professor.getName());
			preparedStatement.setString(4, professor.getEmail());

		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to create a new professor registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new professor registry on sql server",
					exception);

		}

	}

	@Override
	public void update(ProfessorDTO professor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void find(ProfessorDTO professor) {
		// TODO Auto-generated method stub

	}

}
