package co.edu.uco.grades.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.dao.AttendanceDAO;
import co.edu.uco.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.dto.AttendanceDTO;

public class AttendanceAzureSqlDAO extends ConnectionSQL implements AttendanceDAO {

	protected AttendanceAzureSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static AttendanceDAO build(Connection connection) {
		return new AttendanceAzureSqlDAO(connection);
	}

	@Override
	public void create(AttendanceDTO attendance) {
		String sql = "INSERT INTO Attendance(studentCourse, session, attended) VALUES(?,?,?)";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setInt(1, attendance.getStudentCourse().getId());
			preparedStatement.setInt(2, attendance.getSession().getId());
			preparedStatement.setBoolean(3, attendance.getAttended());
		}catch (SQLException exception){
			
			throw GradesException.buildTechnicalDataException("There was a problem trying to create a new attendance registry on sql server", exception);
			
		}catch (Exception exception) {
			
			throw GradesException.buildTechnicalDataException("There was an unexpected problem trying to create a new attendance registry on sql server", exception);
			
		}
		
	}

	@Override
	public void update(AttendanceDTO attendance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Attendance WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
		} catch (SQLException exception) {

			throw GradesException.buildTechnicalDataException(
					"There was a problem trying to delete an attendance registry on sql server", exception);

		} catch (Exception exception) {

			throw GradesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete an attendance registry on sql server", exception);

		}
	}

	@Override
	public void find(AttendanceDTO attendance) {
		// TODO Auto-generated method stub
		
	}

}
