package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import interfaces.CoreInterfaces;

public class ObjectHelper extends CoreFields implements CoreInterfaces {
	
	static {
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); //mysql e baðlanmamý saðlayacak properties
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Connection getConnection() {
		Connection connection= null;
		
		try {
			connection= DriverManager.getConnection(getUrl(),getUserName(),getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}

}
