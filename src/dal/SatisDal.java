package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import complex.types.SatisContractComplex;
import complex.types.StokContractComplex;
import core.ObjectHelper;
import interfaces.DALInterfaces;
import types.SatisContract;

public class SatisDal extends ObjectHelper implements DALInterfaces<SatisContract>{

	@Override
	public void Insert(SatisContract entity) {
		Connection connection = getConnection();
		try {

			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Satis(MusteriId, UrunId, Tarih,PersonelId, Adet) values("+entity.getMusteriId()
			+","+entity.getUrunId()+",'"+entity.getTarih()+"',"+entity.getPersonelId()+","+entity.getAdet()+")");
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<SatisContractComplex> GetAllSatis(){
		List<SatisContractComplex> dataContract=new ArrayList<SatisContractComplex>();
		Connection conn=getConnection();
		SatisContractComplex contract;
		
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT satis.Id, personel.AdiSoyadi,musteri.AdiSoyadi, Adi,satis.Tarih, Adet FROM satis "
			+ " LEFT JOIN musteri on satis.MusteriId=musteri.Id" 
			+" LEFT JOIN urunler ON satis.UrunId=urunler.Id" 
			+ " LEFT JOIN personel ON satis.PersonelId=personel.Id ORDER BY satis.Id DESC");//son eklenen üstte
			
				while(resultSet.next()) {
					contract=new SatisContractComplex();//dýþarda new leseydim cont. en son hangi deðeri aldýysa onu verecekti.
					contract.setId(resultSet.getInt("satis.Id"));
					contract.setPersonelAdi(resultSet.getString("personel.AdiSoyadi"));
					contract.setMusteriAdi(resultSet.getString("musteri.AdiSoyadi"));
					contract.setUrunAdi(resultSet.getString("Adi"));
					contract.setTarih(resultSet.getString("satis.Tarih"));
					contract.setAdet(resultSet.getInt("Adet"));
			
			dataContract.add(contract);			
		}
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return  dataContract;
	}

	@Override
	public List<SatisContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SatisContract Delete(SatisContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SatisContract Update(SatisContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SatisContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
