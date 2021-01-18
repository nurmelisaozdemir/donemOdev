package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.ObjectHelper;
import interfaces.DALInterfaces;
import types.KullaniciContract;
import types.PersonelContract;

public class KullaniciDal extends ObjectHelper implements DALInterfaces<KullaniciContract>{

	@Override
	public void Insert(KullaniciContract entity) {
		Connection connection = getConnection();
		try {

			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Kullanici(PersonelId,YetkiId,Sifre) VALUES (" + entity.getPersonelId() + ","
					+ entity.getYetkiId() + ",'"+entity.getSifre()+"')");
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public KullaniciContract GetPersonelIdVeSifre(int personelId,String sifre){
		KullaniciContract contract=new KullaniciContract();
		List<KullaniciContract> listele=new ArrayList<KullaniciContract>();
		
		Connection baglanti=getConnection();
		
		try {
			Statement sorgu=baglanti.createStatement();
			
			ResultSet rs=sorgu.executeQuery("SELECT * FROM kullanici WHERE PersonelId="+personelId+" AND Sifre='"+sifre.trim()+"'");
			
			while(rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setYetkiId(rs.getInt("YetkiId"));
				contract.setSifre(rs.getString("Sifre"));
				
			}
			sorgu.close();
			baglanti.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return  contract;
	}
	public KullaniciContract GetYetkiId(int personelId){
		KullaniciContract contract=new KullaniciContract();
		List<KullaniciContract> listele=new ArrayList<KullaniciContract>();
		
		Connection baglanti=getConnection();
		
		try {
			Statement sorgu=baglanti.createStatement();
			
			ResultSet rs=sorgu.executeQuery("SELECT * FROM kullanici WHERE PersonelId="+personelId+"");
			
			while(rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setYetkiId(rs.getInt("YetkiId"));
				
			}
			sorgu.close();
			baglanti.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return  contract;
	}

	@Override
	public List<KullaniciContract> GetAll() {
		return null;
	}

	@Override
	public KullaniciContract Delete(KullaniciContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KullaniciContract Update(KullaniciContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KullaniciContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	} 

}
