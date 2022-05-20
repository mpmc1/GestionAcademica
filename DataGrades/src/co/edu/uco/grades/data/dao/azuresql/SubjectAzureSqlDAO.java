package co.edu.uco.grades.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.dao.SubjectDAO;
import co.edu.uco.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.dto.SubjectDTO;

public class SubjectAzureSqlDAO extends ConnectionSQL implements SubjectDAO {

	protected SubjectAzureSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static SubjectDAO build(Connection connection) {
		return new SubjectAzureSqlDAO(connection);
	}

	@Override
	public void create(SubjectDTO subject) {
		String sql = "INSERT INTO IdType(name) VALUES(?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, subject.getName());

		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to create a new subject registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new subject registry on sql server",
					exception);

		}		
	}

	@Override
	public void update(SubjectDTO subject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(SubjectDTO subject) {
		// TODO Auto-generated method stub
		
	}

}
