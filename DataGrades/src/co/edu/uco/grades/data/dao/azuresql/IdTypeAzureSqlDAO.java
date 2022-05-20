package co.edu.uco.grades.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.dao.IdTypeDAO;
import co.edu.uco.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.dto.IdTypeDTO;

public class IdTypeAzureSqlDAO extends ConnectionSQL implements IdTypeDAO{

	protected IdTypeAzureSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static IdTypeDAO build(Connection connection) {
		return new IdTypeAzureSqlDAO(connection);
	}

	@Override
	public void create(IdTypeDTO idType) {
		String sql = "INSERT INTO IdType(name) VALUES(?)";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, idType.getName());
		}catch (SQLException exception){
			
			throw GradesException.buildTechnicalDataException("There was a problem trying to create a new id type registry on sql server", exception);
			
		}catch (Exception exception) {
			
			throw GradesException.buildTechnicalDataException("There was an unexpected problem trying to create a new id type registry on sql server", exception);
			
		}
		
	}

	@Override
	public void update(IdTypeDTO idType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(IdTypeDTO idType) {
		// TODO Auto-generated method stub
		
	}

}
