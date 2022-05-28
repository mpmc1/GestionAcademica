package co.edu.uco.grades.data.dao.azuresql;

import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.dao.StudentDAO;
import co.edu.uco.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.dto.IdTypeDTO;
import co.edu.uco.grades.dto.StudentDTO;

public class StudentAzureSqlDAO extends ConnectionSQL implements StudentDAO {

	private StudentAzureSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static StudentDAO build(Connection connection) {
		return new StudentAzureSqlDAO(connection);
	}

	@Override
	public void create(StudentDTO student) {
		String sql = "INSERT INTO Stundent(idNumber, idType, name, email) VALUES(?,?,?,?)";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, student.getIdNumber());
			preparedStatement.setInt(2, student.getIdType().getId());
		}catch (SQLException exception){
			
			throw GradesException.buildTechnicalDataException("There was a problem trying to create a new studend registry on sql server", exception);
			
		}catch (Exception exception) {
			
			throw GradesException.buildTechnicalDataException("There was an unexpected problem trying to create a new studend registry on sql server", exception);
			
		}
		
	}

	@Override
	public void update(StudentDTO student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Student WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to delete a student registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a student registry on sql server", exception);

		}
		
	}

	@Override
	public List<StudentDTO> find(StudentDTO student) {
		
		/*private int id;
		private String idNumber;
		private IdTypeDTO idType;
		private String name;
		private String email;*/
		
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<StudentDTO> results = new ArrayList<StudentDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, idNumber, idType, email").append(SPACE);
		sb.append("From Student ");

		if (!UtilObject.getUtilObject().isNull(student)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(student.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(student.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(student.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(student.getName()));
			}
			
			if (!UtilText.isEmpty(student.getIdNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idNumber = ? ");
				parameters.add(UtilText.trim(student.getIdNumber()));
			}
			
			if (!UtilText.isEmpty(student.getEmail())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("email = ? ");
				parameters.add(UtilText.trim(student.getEmail()));
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(student.getIdType().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idType = ? ");
				parameters.add(student.getIdType().getId());
			}

		}

		sb.append("ORDER BY name ASC");

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sb.toString())) {

			for (int index = 0; index < parameters.size(); index++) {
				preparedStatement.setObject(index + 1, parameters.get(index));
			}

			results = executeQuery(preparedStatement);

		} catch (GradesException exception) {
			throw exception;

		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to find id type registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an id type registry on sql server", exception);

		}

		return results;

	}
	
	private List<StudentDTO> executeQuery(PreparedStatement preparedStatement) {

		List<StudentDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover id type registry on sql server",
					exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover id type registry on sql server",
					exception);

		}

		return results;

	}

	private List<StudentDTO> assembleResults(ResultSet resultSet) {
		List<StudentDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (GradesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException("There was a problem trying to recover the id types",
					exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the id types registry on sql server", exception);

		}

		return results;

	}

	private StudentDTO assembleDTO(ResultSet resultSet) {

		StudentDTO dto = new StudentDTO();

		try {
			
			IdTypeDTO idType = new IdTypeDTO(resultSet.getInt("idType"), "");

			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
			dto.setEmail(resultSet.getString("email"));
			dto.setIdNumber(resultSet.getString("idNumber"));
			dto.setIdType(idType);

		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException("There was a problem trying to assemble the id types",
					exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the id types on sql server", exception);

		}

		return dto;

	}

}
