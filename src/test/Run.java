package test;

import javax.swing.SwingUtilities;

import dal.UrunlerDal;
import front_end.AnaPencereFE;
import front_end.loginFE;

public class Run {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new loginFE();
				//new UrunlerDal().GetAll();
			}
		});;

	}

}
