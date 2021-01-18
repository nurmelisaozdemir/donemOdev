package front_end;

import java.awt.GridBagLayout;
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

import dal.SehirDal;
import interfaces.FeInterfaces;
import types.SehirContract;

public class sehirEkleFE extends JDialog implements FeInterfaces{

	public sehirEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel=initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Þehir Ekleme Alaný"));
		add(panel);
		setTitle("Þehir Ekle");
		pack();// boyutlarýný otomatik versin
		setModalityType(DEFAULT_MODALITY_TYPE);//kaybolmasýn diye
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);//pencere kapanmasýn
		
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel=new JPanel(new GridLayout(3,1));
		
		JLabel sehirAdiLabel = new JLabel("Þehir Adý: ", JLabel.RIGHT);
		panel.add(sehirAdiLabel);
		JTextField sehirAdiField = new JTextField(10);
		panel.add(sehirAdiField);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SehirContract contract=new SehirContract();
				contract.setSehirAdi(sehirAdiField.getText());
				
				new SehirDal().Insert(contract);
				
				JOptionPane.showMessageDialog(null,contract.getSehirAdi()+" adlý þehir baþarýyla kayýt edilmiþtir.");
				
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
