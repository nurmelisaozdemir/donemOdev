package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.ObjectHelper;
import interfaces.DALInterfaces;
import types.KategoriContract;
import types.UrunlerContract;

public class UrunlerDal extends ObjectHelper implements DALInterfaces<UrunlerContract> {

	@Override
	public void Insert(UrunlerContract entity) {
		Connection connection=getConnection();
		try {
			
			Statement statement=connection.createStatement();
			statement.executeUpdate("INSERT INTO Urunler(Adi,KategoriId,Tarih,Fiyat) VALUES ('"+entity.getAdi()+"','"
			+entity.getKategoriId()+"','"+entity.getTarih()+"',"+entity.getFiyat()+")");
			statement.close();
			statement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<UrunlerContract> GetAll() { // giriþ yapýldýðýnda önceden eklediðimz ürünleri getirdik
		List<UrunlerContract> datacontract = new ArrayList<UrunlerContract>();
		Connection connection = getConnection();
		UrunlerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Urunler");
			while(resultSet.next()) {
				contract=new UrunlerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setKategoriId(resultSet.getInt("KategoriId"));
				contract.setTarih(resultSet.getString("Tarih"));
				
				datacontract.add(contract);
				
				System.out.println(resultSet.getString("Adi"));
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public UrunlerContract Delete(UrunlerContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UrunlerContract Update(UrunlerContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UrunlerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
