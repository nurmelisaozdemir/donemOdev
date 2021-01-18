package complex.types;

public class StokContrackTotalComplex {

	private int id;
	private String personelAdi;
	private String urunAdi;
	private int adet;
	private String tarih;
	private int toplam;
	
	public int getToplam() {
		return toplam;
	}
	public void setToplam(int toplam) {
		this.toplam = toplam;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPersonelAdi() {
		return personelAdi;
	}
	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}
	public String getUrunAdi() {
		return urunAdi;
	}
	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}
	public int getAdet() {
		return adet;
	}
	public void setAdet(int adet) {
		this.adet = adet;
	}
	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	//tabloya girilen veriler bu sýrayla
	public Object[] getVeriler() {
		Object[] veriler= {id, personelAdi, urunAdi, adet, tarih, toplam };
		return veriler;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return personelAdi+"urunAdi";
	}
	

}
