package front_end;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.JulianFields;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dal.MusteriDal;
import dal.PersonelDal;
import dal.SehirDal;
import interfaces.FeInterfaces;
import types.MusteriContract;
import types.PersonelContract;
import types.SehirContract;

public class musteriEkleFE extends JDialog implements FeInterfaces {

	public musteriEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel=initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Müþteri Kayýt Alaný"));
		add(panel);
		setTitle("Müþteri Ekle");
		pack();// boyutlarýný otomatik versin
		setModalityType(DEFAULT_MODALITY_TYPE);//kaybolmasýn diye
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);//pencere kapanmasýn
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel=new JPanel(new BorderLayout());
		JPanel fieldPanel=new JPanel(new GridLayout(5,2));
		JPanel buttonPanel=new JPanel(new GridLayout(1,2));
		
		JLabel adiSoyadiLabel = new JLabel("Müþteri Adý: ", JLabel.RIGHT);
		fieldPanel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(15);
		fieldPanel.add(adiSoyadiField);
		JLabel telefonLabel = new JLabel("Telefon: ", JLabel.RIGHT);
		fieldPanel.add(telefonLabel);
		JTextField telefonField = new JTextField(15);
		fieldPanel.add(telefonField);
		JLabel sehirSecLabel = new JLabel("Þehir Seç: ", JLabel.RIGHT);
		fieldPanel.add(sehirSecLabel);
		JComboBox sehirlerBox = new JComboBox(new SehirDal().GetAll().toArray());
		fieldPanel.add(sehirlerBox);
		sehirlerBox.insertItemAt("", 0);
		sehirlerBox.setSelectedIndex(0);
		
		
		JTextArea adresArea=new JTextArea(5,1);
		JScrollPane pane=new JScrollPane(adresArea);
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));
		
		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		
		JButton iptalButton = new JButton("Ýptal");
		buttonPanel.add(iptalButton);
		
		panel.add(fieldPanel,BorderLayout.NORTH);
		panel.add(pane,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusteriContract contract=new MusteriContract();
				SehirContract sContract=(SehirContract) sehirlerBox.getSelectedItem();
				
				contract.setAdiSoyadi(adiSoyadiField.getText());
				contract.setTelefon(telefonField.getText());
				contract.setAdres(adresArea.getText());
				contract.setSehirId(sehirlerBox.getSelectedIndex());
				
				new MusteriDal().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+" adlý müþteri eklenmiþtir.");
				
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
