package utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import dal.KullaniciDal;
import front_end.kategoriEkleFE;
import front_end.loginFE;
import front_end.musteriEkleFE;
import front_end.personelEkleFE;
import front_end.sehirEkleFE;
import front_end.sifreBelirleFE;
import front_end.urunEkleFE;
import front_end.yetkiEkleFE;
import types.PersonelContract;

public class MenulerCom {

	public static JMenuBar initBar() {
		JMenuBar bar=new JMenuBar();
		
		JMenu dosyaMenu=new JMenu("Dosya");
		bar.add(dosyaMenu);
		
		JMenuItem cikisItem=new JMenuItem("Çýkýþ");
		dosyaMenu.add(cikisItem);
		
		/*Ürünler Menüsü*/
		
		JMenu urunlerMenu=new JMenu("Ürünler");
		bar.add(urunlerMenu);
		
		JMenuItem urunEkleItem=new JMenuItem("Ürün Ekle");
		urunlerMenu.add(urunEkleItem);
		JMenuItem kategoriEkleItem=new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator();
		
		JMenuItem urunDuzenleItem=new JMenuItem("Ürünü Duzenle");
		urunlerMenu.add(urunDuzenleItem);
		JMenuItem kategoriDuzenleItem=new JMenuItem("Kategori Düzenle");
		urunlerMenu.add(kategoriDuzenleItem);
		
		/*Personeller Menüsü*/
		
		JMenu personellerMenu=new JMenu("Personel Ýþlemleri");
		bar.add(personellerMenu);
		
		JMenuItem personelEkleItem=new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem);
		JMenuItem yetkiEkleItem=new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);
		JMenuItem sifreBelirleItem=new JMenuItem("Þifre Belirle");
		personellerMenu.add(sifreBelirleItem);
		personellerMenu.addSeparator();
		
		JMenuItem personelDuzenleItem=new JMenuItem("Personel Düzenle");
		personellerMenu.add(personelDuzenleItem);
		JMenuItem yetkiDuzenleItem=new JMenuItem("Yetki Düzenle");
		personellerMenu.add(yetkiDuzenleItem);
		JMenuItem sifreDuzenleItem=new JMenuItem("Þifre Düzenle");
		personellerMenu.add(sifreDuzenleItem);
		
		/*Müþteriler Menüsü*/
		
		JMenu musterilerMenü=new JMenu("Müþteri Ýþlemleri");
		bar.add(musterilerMenü);
		
		JMenuItem musteriEkleItem=new JMenuItem("Müþteri Ekle:");
		musterilerMenü.add(musteriEkleItem);		
		JMenuItem sehirEkleItem=new JMenuItem("Þehir Ekle");
		musterilerMenü.add(sehirEkleItem);
		musterilerMenü.addSeparator();
		
		JMenuItem musteriDuzenleItem=new JMenuItem("Müþteri Düzenle");
		musterilerMenü.add(musteriDuzenleItem);
		JMenuItem sehirDuzenleItem=new JMenuItem("Þehir Düzenle");
		musterilerMenü.add(sehirDuzenleItem);
		PersonelContract contract=(PersonelContract) loginFE.emailBox.getSelectedItem();
		System.out.println(contract);
		
		//kýsýtlama için aç
		/*
		if(new KullaniciDal().GetYetkiId(contract.getId()).getYetkiId()==2) {
			personellerMenu.hide();
		}
		else if(new KullaniciDal().GetYetkiId(contract.getId()).getYetkiId()==3) {
			personellerMenu.hide();
			musterilerMenu.hide();
			urunlerMenu.hide();
		}
		*/
		
		cikisItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						bar.disable();
						//new loginFE();
						
					}
				});
				
			}
		});
		
		urunEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new urunEkleFE();
						
					}
				}); 
				
			}
		});
		kategoriEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new kategoriEkleFE();
				
			}
		});
		personelEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new personelEkleFE();
						
					}
				});
				
			}
		});
		yetkiEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new yetkiEkleFE();
						
					}
				});
				
			}
		});
		sifreBelirleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new sifreBelirleFE();
						
					}
				});
				
			}
		});
		musteriEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new musteriEkleFE();
						
					}
				});
				
			}
		});
		sehirEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new sehirEkleFE();
						
					}
				});
				
			}
		});
		
		return bar;
	}
}
