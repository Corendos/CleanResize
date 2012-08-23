package fr.alexion.cleanresize;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {


	public static void main(String[] args) {
		
		try {
			
	       UIManager.setLookAndFeel(
	       UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       
	    }
	    catch (ClassNotFoundException e) {
	       
	    }
	    catch (InstantiationException e) {
	       
	    }
	    catch (IllegalAccessException e) {
	      
	    }

		MainWindow mainwindow = new MainWindow();
	}

}
