package co.edu.uco.grades.data.factory;

import co.edu.uco.grades.data.dao.StudentDAO;

public abstract class DAOFactory {
	
	public static DAOFactory getDaoFactory() {
		return null;
	}
	
	protected abstract void openConnection();
	
	public abstract void getConnection();
	
	public abstract void closeConnection();
	
	public abstract void rollback();
	
	public abstract void commit();
	
	public abstract StudentDAO getStudentDAO();
	
}
