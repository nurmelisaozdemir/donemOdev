package front_end;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import dal.PersonelDal;
import interfaces.FeInterfaces;
import types.PersonelContract;

public class personelEkleFE extends JDialog implements FeInterfaces {

	public personelEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel=initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Personel Kayýt Alaný"));
		add(panel);
		setTitle("Personel Ekle");
		pack();// boyutlarýný otomatik versin
		setModalityType(DEFAULT_MODALITY_TYPE);//kaybolmasýn diye
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);//pencere kapanmasýn
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel=new JPanel(new GridLayout(3, 2));
		
		JLabel adiSoyadiLabel = new JLabel("Personel Adý: ", JLabel.RIGHT);
		panel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(10);
		panel.add(adiSoyadiField);
		
		JLabel eMailLabel = new JLabel("Email: ", JLabel.RIGHT);
		panel.add(eMailLabel);
		JTextField eMailField = new JTextField(10);
		panel.add(eMailField);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract=new PersonelContract();
				contract.setAdiSoyadi(adiSoyadiField.getText());
				contract.setEmail(eMailField.getText());
				
				new PersonelDal().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+" adlý personel eklenmiþtir.");
				
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
