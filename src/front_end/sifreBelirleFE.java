package front_end;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import dal.KategoriDal;
import dal.KullaniciDal;
import dal.PersonelDal;
import dal.YetkilerDal;
import interfaces.FeInterfaces;
import types.KullaniciContract;
import types.PersonelContract;
import types.YetkilerContract;

public class sifreBelirleFE extends JDialog implements FeInterfaces{

	public sifreBelirleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("�ifre Belirleme Alan�"));
		add(panel);
		setTitle("�ifre Belirle");
		pack();// boyutlar�n� otomatik versin
		setModalityType(DEFAULT_MODALITY_TYPE);//kaybolmas�n diye
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel=new JPanel(new GridLayout(5, 2));
		
		JLabel personelLabel = new JLabel("Personel Se�: ", JLabel.RIGHT);
		panel.add(personelLabel);		
		JComboBox personelBox=new JComboBox(new PersonelDal().GetAll().toArray());
		panel.add(personelBox);
		
		
		JLabel yetkiLabel = new JLabel("Yetki Se�: ", JLabel.RIGHT);
		panel.add(yetkiLabel);		
		JComboBox yetkiBox=new JComboBox(new YetkilerDal().GetAll().toArray());
		panel.add(yetkiBox);
		
		
		JLabel passwordLabel=new JLabel("�ifre Belirle", JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passField=new JPasswordField(10);
		panel.add(passField);
		
		JLabel passTekrarLabel=new JLabel("�ifre Tekrar", JLabel.RIGHT);
		panel.add(passTekrarLabel);
		JPasswordField passTekrar=new JPasswordField(10);
		panel.add(passTekrar);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);

		JButton iptalButton = new JButton("�ptal");
		panel.add(iptalButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KullaniciContract contract=new KullaniciContract();
				PersonelContract pContract=(PersonelContract) personelBox.getSelectedItem();
				YetkilerContract yContract=(YetkilerContract) yetkiBox.getSelectedItem();
				
				if(passField.getText().equals(passTekrar.getText())) {
					
					contract.setPersonelId(pContract.getId());
					contract.setYetkiId(yContract.getId());
					contract.setSifre(passField.getText());
					new KullaniciDal().Insert(contract);
					JOptionPane.showMessageDialog(null, pContract.getAdiSoyadi()+" adl� ki�iye"+yContract.getAdi()+" adl� yetki ba�ar�l� bir �ekilde atanm��t�r.");
					
				}
				else {
					JOptionPane.showMessageDialog(null, "�ifreler uyu�muyor tekrar kontrol ediniz.");
				}
				
			}
		});
		iptalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AnaPencereFE();
				
			}
		});
		
		
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
