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
import co.edu.uco.grades.data.dao.ProfessorDAO;
import co.edu.uco.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.dto.IdTypeDTO;
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
		String sql = "INSERT INTO Professor(idNumber, idType, name, email) VALUES(?,?,?,?)";

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
		String sql = "DELETE FROM Professor WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to delete a professor registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a professor registry on sql server", exception);

		}

	}

	@Override
	public List<ProfessorDTO> find(ProfessorDTO professor) {
		
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<ProfessorDTO> results = new ArrayList<ProfessorDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, idNumber, email, idType").append(SPACE);
		sb.append("From Professor ");

		if (!UtilObject.getUtilObject().isNull(professor)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(professor.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(professor.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(professor.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(professor.getName()));
			}
			
			if (!UtilText.isEmpty(professor.getIdNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idNumber = ? ");
				parameters.add(UtilText.trim(professor.getIdNumber()));
			}
			
			if (!UtilText.isEmpty(professor.getEmail())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("email = ? ");
				parameters.add(UtilText.trim(professor.getEmail()));
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(professor.getIdType().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idType = ? ");
				parameters.add(professor.getIdType().getId());
			}

		}

		sb.append("ORDER BY name ASC");
		
		System.out.println(sb.toString());

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
	
	private List<ProfessorDTO> executeQuery(PreparedStatement preparedStatement) {

		List<ProfessorDTO> results = new ArrayList<>();

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

	private List<ProfessorDTO> assembleResults(ResultSet resultSet) {
		List<ProfessorDTO> results = new ArrayList<>();

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

	private ProfessorDTO assembleDTO(ResultSet resultSet) {

		ProfessorDTO dto = new ProfessorDTO();

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
