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
		
		JMenuItem cikisItem=new JMenuItem("��k��");
		dosyaMenu.add(cikisItem);
		
		/*�r�nler Men�s�*/
		
		JMenu urunlerMenu=new JMenu("�r�nler");
		bar.add(urunlerMenu);
		
		JMenuItem urunEkleItem=new JMenuItem("�r�n Ekle");
		urunlerMenu.add(urunEkleItem);
		JMenuItem kategoriEkleItem=new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator();
		
		JMenuItem urunDuzenleItem=new JMenuItem("�r�n� Duzenle");
		urunlerMenu.add(urunDuzenleItem);
		JMenuItem kategoriDuzenleItem=new JMenuItem("Kategori D�zenle");
		urunlerMenu.add(kategoriDuzenleItem);
		
		/*Personeller Men�s�*/
		
		JMenu personellerMenu=new JMenu("Personel ��lemleri");
		bar.add(personellerMenu);
		
		JMenuItem personelEkleItem=new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem);
		JMenuItem yetkiEkleItem=new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);
		JMenuItem sifreBelirleItem=new JMenuItem("�ifre Belirle");
		personellerMenu.add(sifreBelirleItem);
		personellerMenu.addSeparator();
		
		JMenuItem personelDuzenleItem=new JMenuItem("Personel D�zenle");
		personellerMenu.add(personelDuzenleItem);
		JMenuItem yetkiDuzenleItem=new JMenuItem("Yetki D�zenle");
		personellerMenu.add(yetkiDuzenleItem);
		JMenuItem sifreDuzenleItem=new JMenuItem("�ifre D�zenle");
		personellerMenu.add(sifreDuzenleItem);
		
		/*M��teriler Men�s�*/
		
		JMenu musterilerMen�=new JMenu("M��teri ��lemleri");
		bar.add(musterilerMen�);
		
		JMenuItem musteriEkleItem=new JMenuItem("M��teri Ekle:");
		musterilerMen�.add(musteriEkleItem);		
		JMenuItem sehirEkleItem=new JMenuItem("�ehir Ekle");
		musterilerMen�.add(sehirEkleItem);
		musterilerMen�.addSeparator();
		
		JMenuItem musteriDuzenleItem=new JMenuItem("M��teri D�zenle");
		musterilerMen�.add(musteriDuzenleItem);
		JMenuItem sehirDuzenleItem=new JMenuItem("�ehir D�zenle");
		musterilerMen�.add(sehirDuzenleItem);
		PersonelContract contract=(PersonelContract) loginFE.emailBox.getSelectedItem();
		System.out.println(contract);
		
		//k�s�tlama i�in a�
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
