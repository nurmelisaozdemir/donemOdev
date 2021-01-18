package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import complex.types.StokContrackTotalComplex;
import complex.types.StokContractComplex;
import core.ObjectHelper;
import interfaces.DALInterfaces;
import types.KategoriContract;
import types.StokContract;

public class StokDal extends ObjectHelper implements DALInterfaces<StokContract>{

	@Override
	public void Insert(StokContract entity) {
		Connection connection = getConnection();
		try {

			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Stok(PersonelId,UrunId,Tarih,Adet) VALUES ("+entity.getPersonelId()
			+","+entity.getPersonelId()+",'"+entity.getTarih()+"',"+entity.getAdet()+")");
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*SELECT stok.Id, AdiSoyadi,Adi Adet, stok.Tarih FROM stok
	 * LEFT JOIN urunler ON stok.UrunId=urunler.Id
	 * LEFT JOIN personel ON stok.PersonelId=personel.Id*/
	
	public List<StokContractComplex> GetAllStok() {
		
		List<StokContractComplex> datacontract = new ArrayList<StokContractComplex>();
		Connection connection = getConnection();
		StokContractComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT stok.Id, AdiSoyadi,Adi, stok.Tarih, Adet FROM stok " 
					+ " LEFT JOIN urunler ON stok.UrunId=urunler.Id" 
					+ " LEFT JOIN personel ON stok.PersonelId=personel.Id ORDER BY stok.Id DESC");//son eklenen üstte
			
				while(resultSet.next()) {
					contract=new StokContractComplex();
					contract.setId(resultSet.getInt("Id"));
					contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
					contract.setUrunAdi(resultSet.getString("urunler.Adi"));
					contract.setTarih(resultSet.getString("stok.Tarih"));
					contract.setAdet(resultSet.getInt("Adet"));
			
			datacontract.add(contract);			
		}

	   } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
		return datacontract;
	}
	//toplam ürün adetini getiriyoruz
	public List<StokContrackTotalComplex> GetTotalStok() {
		
		List<StokContrackTotalComplex> datacontract = new ArrayList<StokContrackTotalComplex>();
		Connection connection = getConnection();
		StokContrackTotalComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT sum(Adet) as toplam, stok.Id, AdiSoyadi, Adi, Adet, stok.Tarih FROM stok LEFT JOIN urunler on stok.UrunId=urunler.Id LEFT JOIN personel on stok.PersonelId=personel.Id GROUP BY urunId");
			
				while(resultSet.next()) {
					contract=new StokContrackTotalComplex();
					contract.setId(resultSet.getInt("Id"));
					contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
					contract.setUrunAdi(resultSet.getString("urunler.Adi"));
					contract.setTarih(resultSet.getString("stok.Tarih"));
					contract.setAdet(resultSet.getInt("Adet"));
					contract.setToplam(resultSet.getInt("toplam"));
			
			datacontract.add(contract);			
		}

	   } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
		return datacontract;
	}
	
	@Override
	public List<StokContract> GetAll() {
		return null;
	}

	@Override
	public StokContract Delete(StokContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StokContract Update(StokContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StokContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
