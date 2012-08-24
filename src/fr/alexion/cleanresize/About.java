package fr.alexion.cleanresize;

import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JDialog {

	private static final long serialVersionUID = 3608465276024246005L;
	private Locale currentLocale = Locale.getDefault();
	private ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

	public About() {
		
		this.setIconImage(getToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		setTitle(messages.getString("about"));
		setBounds(100, 100, 350, 193);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel(messages.getString("aut_info"));
		label.setBounds(112, 73, 212, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("v 1.0 Beta");
		label_1.setBounds(150, 98, 132, 14);
		getContentPane().add(label_1);
		
		JButton button = new JButton("Ok");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		button.setBounds(279, 134, 53, 23);
		getContentPane().add(button);
		
		JLabel alexion = new JLabel(new ImageIcon(getClass().getResource("/images/alexion.png"))); 
		alexion.setBounds(10, 11, 314, 52);
		getContentPane().add(alexion);
	}
}
