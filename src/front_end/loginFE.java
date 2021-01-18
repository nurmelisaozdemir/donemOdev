package front_end;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.border.Border;

import dal.KullaniciDal;
import dal.PersonelDal;
import interfaces.FeInterfaces;
import types.PersonelContract;

public class loginFE extends JDialog implements FeInterfaces{
	
	public static JComboBox emailBox;//bunu yapmam�n sebebi heryerden ula�abilmem i�in
	
	public loginFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel=initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Login Ekran�"));
		add(panel);
		setTitle("L�tfen Giri� Yap�n�z.");
		pack();// boyutlar�n� otomatik versin
		//setModalityType(DEFAULT_MODALITY_TYPE);//kaybolmas�n diye
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);//pencere kapanmas�n
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel=new JPanel(new GridLayout(3,2));
		JLabel emailLabel = new JLabel("Email: ", JLabel.RIGHT);
		panel.add(emailLabel);
		emailBox=new JComboBox(new PersonelDal().GetAll().toArray());
		panel.add(emailBox);
		JLabel passwordLabel = new JLabel("�ifre: ", JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField=new JPasswordField(15);
		panel.add(passwordField);
		JButton loginButton = new JButton("Giri�");
		panel.add(loginButton);
		JButton iptalButton = new JButton("�ptal");
		panel.add(iptalButton);
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract=(PersonelContract) emailBox.getSelectedItem();
				
				if(new KullaniciDal().GetPersonelIdVeSifre(contract.getId(), passwordField.getText()).getId()!=0) {
					
					new AnaPencereFE();
					
				}else {
					JOptionPane.showMessageDialog(null, "Giri� ba�ar�s�z");
				}
				
			}
		});
		iptalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				//new AnaPencereFE();
				
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
