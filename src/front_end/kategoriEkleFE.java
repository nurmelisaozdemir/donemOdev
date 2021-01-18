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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


import dal.KategoriDal;
import interfaces.FeInterfaces;
import types.KategoriContract;

public class kategoriEkleFE extends JDialog implements FeInterfaces{

	public kategoriEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Kategori Ekle"));
		add(panel);
		setTitle("Kategori Ekle");
		pack();// boyutlar�n� otomatik versin
		setModalityType(DEFAULT_MODALITY_TYPE);//kaybolmas�n diye
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));//ka� sat�r ka� s�tun
		
		JLabel adiLabel = new JLabel("Kategori Ad�: ", JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		
		JLabel kategoriLabel = new JLabel("Kategori Se�: ", JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox=new JComboBox(new KategoriDal().GetAll().toArray());//GetAllParentId()
		panel.add(kategoriBox);
		kategoriBox.insertItemAt("---Kategori Se�iniz---", 0);
		kategoriBox.setSelectedIndex(0);
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KategoriContract contract=new KategoriContract();
				
				
				if(kategoriBox.getSelectedIndex()!=0) {
					KategoriContract casContract=(KategoriContract) kategoriBox.getSelectedItem();
					contract.setAdi(adiField.getText());
					contract.setParentId(casContract.getId());
					
					new KategoriDal().Insert(contract);
					JOptionPane.showMessageDialog(null,contract.getAdi()+ " adl� kategori ba�ar�l� bir �ekilde kay�t edilmi�tir.");
				}
				else {
					
					contract.setAdi(adiField.getText());
					contract.setParentId(kategoriBox.getSelectedIndex());
					
					new KategoriDal().Insert(contract);
					JOptionPane.showMessageDialog(null,contract.getAdi()+ " adl� kategori ba�ar�l� bir �ekilde kay�t edilmi�tir.");
					kategoriBox.addItem(new KategoriDal().GetAllParentId().toArray());
				}
			
			}
		});

		JButton iptalButton = new JButton("�ptal");
		panel.add(iptalButton);
		iptalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
