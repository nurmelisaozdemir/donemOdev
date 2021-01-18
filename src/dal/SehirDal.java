package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.ObjectHelper;
import interfaces.DALInterfaces;
import types.MusteriContract;
import types.SehirContract;

public class SehirDal extends ObjectHelper implements DALInterfaces<SehirContract>{

	@Override
	public void Insert(SehirContract entity) {
		Connection connection = getConnection();
		try {

			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Sehirler( Id,SehirAdi) VALUES ("+entity.getId()+",'" + entity.getSehirAdi() + "')");
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<SehirContract> GetAll() {
		List<SehirContract> datacontract = new ArrayList<SehirContract>();
		Connection connection = getConnection();
		SehirContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Sehirler");
			while(resultSet.next()) {
				contract=new SehirContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setSehirAdi(resultSet.getString("SehirAdi"));
				
				datacontract.add(contract);
			}
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			return datacontract;
	}

	@Override
	public SehirContract Delete(SehirContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SehirContract Update(SehirContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SehirContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
