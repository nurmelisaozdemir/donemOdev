package front_end;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import complex.types.SatisContractComplex;
import complex.types.StokContrackTotalComplex;
import complex.types.StokContractComplex;
import dal.MusteriDal;
import dal.SatisDal;
import dal.StokDal;
import dal.UrunlerDal;
import interfaces.FeInterfaces;
import types.MusteriContract;
import types.PersonelContract;
import types.SatisContract;
import types.StokContract;
import types.UrunlerContract;
import utilities.MenulerCom;

public class AnaPencereFE extends JFrame implements FeInterfaces{

	public AnaPencereFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel=initPanel();
		
		//JTabbedPane tabs=initTabs();
		JMenuBar bar=initBar();
		
		add(panel);
		setJMenuBar(bar);
		setTitle("Satýþ ve Stok Programý");
		setSize(600, 250);
		//pack();//penceremizin içinde oluturudugumuz panel vs. otomatik sizi.
		setVisible(true);
		setLocationRelativeTo(null); //pencere oluþ.ortada dursun
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel=new JPanel(new BorderLayout());
		JTabbedPane pane=initTabs();
		panel.add(pane,BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar= MenulerCom.initBar();
		return bar;
		
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane=new JTabbedPane();
		
		ImageIcon icon=new ImageIcon("icons/stock.png");
		ImageIcon icon2=new ImageIcon("icons/stock.png");
		
		JPanel stokPanel=new JPanel(new BorderLayout());
		JPanel satisPanel=new JPanel(new BorderLayout());
		
		/*Stok Ýtemleri*/
		
		JPanel stokSolPanel=new JPanel(new BorderLayout());
		JPanel stokSolUstPanel=new JPanel(new GridLayout(5,2));
		JPanel stokSolAltPanel=new JPanel();		
		
		stokSolPanel.setBorder(BorderFactory.createTitledBorder("StokÝþlemleri"));
		Object [] stokKolonlar= {"Id","PersonelAdi","ÜrünAdi","Adeti","Tarihi","Toplam"};
		DefaultTableModel model=new DefaultTableModel(stokKolonlar,0);
		JTable table=new JTable(model);
		JScrollPane stokTablePane=new JScrollPane(table);
		
		for(StokContractComplex contract : new StokDal().GetAllStok()) {
			model.addRow(contract.getVeriler());
		}
		
		JLabel stokUrunAdiLabel=new JLabel("Ürün Adi:",JLabel.RIGHT);
		stokSolUstPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdiBox=new JComboBox(new UrunlerDal().GetAll().toArray());//ürünleri listeledik
		stokSolUstPanel.add(stokUrunAdiBox);
		JLabel stokAdetLabel=new JLabel("Ürün Adeti:",JLabel.RIGHT);
		stokSolUstPanel.add(stokAdetLabel);
		JTextField stokAdetField=new JTextField(10);
		stokSolUstPanel.add(stokAdetField);
		JLabel stokTarihiLabel=new JLabel("Stok Tarihi:",JLabel.RIGHT);
		stokSolUstPanel.add(stokTarihiLabel);
		JDateChooser stokTarihi=new JDateChooser();
		stokSolUstPanel.add(stokTarihi);
		
		JButton stokEkleButton=new JButton("Stok Ekle");
		stokSolUstPanel.add(stokEkleButton);
		JButton stokYenileButton=new JButton("Stok Yenile");
		stokSolUstPanel.add(stokYenileButton);
		JButton stokTotalButton=new JButton("Stok Toplam Ürün");
		stokSolUstPanel.add(stokTotalButton);
		
		stokTotalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int satir=model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);					
				}
				for(StokContrackTotalComplex total: new StokDal().GetTotalStok()) {
					model.addRow(total.getVeriler());
				}
				
			}
		});
		
		stokYenileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int satir=model.getRowCount();				
				for(int i=0;i<satir;i++) {
					model.removeRow(0);//yani 0.satýrdan siliyor sürekli
					JOptionPane.showMessageDialog(null, "Temizlendi");
				}	
				//burda getiriyoruz
				for(StokContractComplex compContract : new StokDal().GetAllStok()) {
					model.addRow(compContract.getVeriler());
				}
				
			}
		});
		
		stokEkleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StokContract contract=new StokContract();
				UrunlerContract uContract= (UrunlerContract) stokUrunAdiBox.getSelectedItem();
				PersonelContract pContract=(PersonelContract) loginFE.emailBox.getSelectedItem();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				
				String date=format.format(stokTarihi.getDate());
				
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setTarih(date.toString());
				contract.setAdet(Integer.parseInt(stokAdetField.getText()));
				new StokDal().Insert(contract);
				JOptionPane.showMessageDialog(null,uContract.getAdi()+" adlý ürün eklenmiþtir.");
				//stok eklediðimiz zaman tabloya otomatik gelmesi için yaptýðým iþlemler
				//removerow u 0 yaptýk üst üste binmesin eklenen sonda görünsün diye
				int satir=model.getRowCount();
				//burda temizliyoruz güncellemek için
				for(int i=0;i<satir;i++) {
					model.removeRow(0);//yani 0.satýrdan siliyor sürekli
					JOptionPane.showMessageDialog(null, "Temizlendi");
				}	
				//burda getiriyoruz
				for(StokContractComplex compContract : new StokDal().GetAllStok()) {
					model.addRow(compContract.getVeriler());
				}
				
			}
		});
		/*Satýþ Ýtemleri*/
		JPanel satisSagPanel=new JPanel(new BorderLayout());
		JPanel satisSagUstPanel=new JPanel(new GridLayout(6,2));
		JPanel satisSagAltPanel=new JPanel();
		
		Object [] satisKolonlar= {"Id","PersonelAdi","Müþteri Adi","ÜrünAdi","Adeti","Tarihi"};
		DefaultTableModel satisModel=new DefaultTableModel(satisKolonlar,0);
		JTable satisTable=new JTable(satisModel);
		JScrollPane satisTablePane=new JScrollPane(satisTable);
		
		JLabel musteriAdiLabel=new JLabel("Müþteri Adý:", JLabel.RIGHT);
		satisSagUstPanel.add(musteriAdiLabel);
		JComboBox musteriAdiBox=new JComboBox(new MusteriDal().GetAll().toArray());
		satisSagUstPanel.add(musteriAdiBox);
		JLabel satisUrunAdiLabel=new JLabel("Ürün Adi:",JLabel.RIGHT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdiBox=new JComboBox(new UrunlerDal().GetAll().toArray());
		satisSagUstPanel.add(satisUrunAdiBox);
		JLabel satisAdetLabel=new JLabel("Ürün Adeti:",JLabel.RIGHT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisAdetField=new JTextField(10);
		satisSagUstPanel.add(satisAdetField);
		JLabel satisTarihiLabel=new JLabel("Satýþ Tarihi:",JLabel.RIGHT);
		satisSagUstPanel.add(satisTarihiLabel);
		JDateChooser satisTarihi=new JDateChooser();
		satisSagUstPanel.add(satisTarihi);
		
		JButton satisEkleButton=new JButton("Satýþ Yap");
		satisSagUstPanel.add(satisEkleButton);
		
		/*JButton satisYenileButton=new JButton("Satýþ Yenile");
		satisSagUstPanel.add(satisYenileButton);*/
		
		for(SatisContractComplex yenileContract : new SatisDal().GetAllSatis()) {
			satisModel.addRow(yenileContract.getVeriler());
		}
		satisEkleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract pContract=(PersonelContract) loginFE.emailBox.getSelectedItem();
				UrunlerContract uContract=(UrunlerContract) satisUrunAdiBox.getSelectedItem(); // ürünler tablosundaki verileri ürünlercontract sayesinde listelediði için tekrardan ürünler contracta dönüþüm yapýyorum.
				MusteriContract mContract=(MusteriContract) musteriAdiBox.getSelectedItem();
				//böylelikle dönüþtürdüðüm veriyi satis tablosundan da eriþim yapabiliyorum
				//yukarýda dönüþtürdükten sonra tekrar eski haline döndürmek için cas iþlemi gerçekleþtirdik.
				SatisContract contract=new SatisContract();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				
				String date=format.format(satisTarihi.getDate());
				
				contract.setMusteriId(mContract.getId());
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setAdet(Integer.parseInt(satisAdetField.getText()));
				contract.setTarih(date);
				
				new SatisDal().Insert(contract);
				
				StokContract stokContract=new StokContract();
				stokContract.setPersonelId(pContract.getId());
				stokContract.setUrunId(uContract.getId());
				stokContract.setAdet(-Integer.parseInt(satisAdetField.getText()));
				stokContract.setTarih(date);
				
				new StokDal().Insert(stokContract);
				
				JOptionPane.showMessageDialog(null, pContract.getAdiSoyadi()+" adlý kiþiye satýþ gerçekleþtirilmiþtir ve "+
				uContract.getAdi()+" adlý üründen stoktan "+contract.getAdet()+" adet ürün eksiltilmiþtir. ");
				
				int satir=satisModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);					
				}
				for(SatisContractComplex yenileContract : new SatisDal().GetAllSatis()) {
					satisModel.addRow(yenileContract.getVeriler());
				}
			}
		});
		
		JButton satisYenileButton=new JButton("Satýþ Yenile");
		satisSagUstPanel.add(satisYenileButton);
		
		satisYenileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int satir=satisModel.getRowCount();
				for (int i = 0; i < satir; i++) {
				model.removeRow(0);					
				}
				
				for(SatisContractComplex contract : new SatisDal().GetAllSatis()) {
					satisModel.addRow(contract.getVeriler());
				}
				
			}
		});
		
		stokPanel.add(stokSolPanel,BorderLayout.WEST);
		stokPanel.add(stokTablePane,BorderLayout.CENTER);
		
		
		satisPanel.add(satisSagPanel,BorderLayout.EAST);
		satisPanel.add(satisTablePane,BorderLayout.CENTER);
		
		satisSagPanel.add(satisSagUstPanel,BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel,BorderLayout.SOUTH);
		
		
		stokSolPanel.add(stokSolUstPanel,BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel,BorderLayout.SOUTH);
		
		pane.addTab("Stoklar ", icon, stokPanel,"");
		pane.addTab("Satýþlar",icon2, satisPanel,"");
		
		return pane;
	}

}
