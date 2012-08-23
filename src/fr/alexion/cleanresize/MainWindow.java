package fr.alexion.cleanresize;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame {


	private static final long serialVersionUID = -6885511291475064114L;
	
	private Locale currentLocale = Locale.getDefault();
	private ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

	private final JFileChooser fc = new JFileChooser();
	private JPanel container = new JPanel();
	private Dimension text_field = new Dimension(170, 25);
	private JButton submit = new JButton(messages.getString("calculate"));
	private JButton openImage = new JButton(messages.getString("open_image"));
	private JLabel lbl_a_w = new JLabel(messages.getString("width")+" (px)");
	private JTextField tf_a_w = new JTextField();
	private JLabel lbl_a_h = new JLabel(messages.getString("height") +" (px)");
	private JTextField tf_a_h = new JTextField();
	private JMenuBar menuBar = new JMenuBar();
	
	private JLabel lbl_b_w = new JLabel(messages.getString("width")+ "(px)");
	private JTextField tf_b_w = new JTextField();
	private JLabel lbl_b_h = new JLabel(messages.getString("height")+ " (px)");
	private JTextField tf_b_h = new JTextField();
	private JMenuItem about = new JMenuItem(messages.getString("about"));
	private JMenu fichier = new JMenu(messages.getString("file"));

	
	private int bh, bw;
	private int i;
	private final JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/images/cleanresize.png")));
	
	public MainWindow() {
		this.setSize(400,360);
		this.setTitle("CleanResize");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(getToolkit().getImage(getClass().getResource("/images/icon.png")));
		initComposant();
		
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	private void initComposant(){
		

		submit.setPreferredSize(new Dimension(110, 25));
		openImage.setPreferredSize(new Dimension(180, 25));
		JPanel origine = new JPanel();
		origine.setPreferredSize(new Dimension(380, 100));
		origine.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), messages.getString("current_image_txt")));
		tf_a_w.setBounds(146, 21, 170, 25);
		
		tf_a_w.setPreferredSize(text_field);
		tf_a_h.setBounds(146, 51, 170, 25);
		tf_a_h.setPreferredSize(text_field);
		origine.setLayout(null);
		lbl_a_w.setBounds(45, 26, 78, 14);
		origine.add(lbl_a_w);
		origine.add(tf_a_w);
		lbl_a_h.setBounds(45, 56, 78, 14);
		origine.add(lbl_a_h);
		origine.add(tf_a_h);
		
		JPanel after = new JPanel();
		after.setPreferredSize(new Dimension(380, 100));
		after.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), messages.getString("after_resize_txt")));
		tf_b_w.setBounds(147, 21, 170, 25);
		
		tf_b_w.setPreferredSize(text_field);
		tf_b_h.setBounds(147, 51, 170, 25);
		tf_b_h.setPreferredSize(text_field);
		after.setLayout(null);
		lbl_b_w.setBounds(48, 26, 75, 14);
		after.add(lbl_b_w);
		after.add(tf_b_w);
		lbl_b_h.setBounds(48, 56, 78, 14);
		after.add(lbl_b_h);
		

		after.add(tf_b_h);
		
		this.fichier.add(about);
		this.menuBar.add(fichier);
		this.setJMenuBar(menuBar);
		
		container.add(logo);
		
		container.add(origine);
		container.add(after);
		container.add(submit);
		container.add(openImage);
		
		listener();
	}
	
	 public void listener(){
		// ACTION LISTENER
			
		submit.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) {
				
				if(!tf_a_h.getText().isEmpty() && !tf_a_w.getText().isEmpty())
				{
				
					if(tf_b_h.getText().isEmpty() && i==0)
					{
						tf_b_h.setText("0");
					}
					if(tf_b_w.getText().isEmpty() && i==0)
					{
						tf_b_w.setText("0");
					}
					i++;
					if(checkIfNumber(tf_b_h.getText()) || checkIfNumber(tf_b_w.getText()))
					{
						if(!tf_b_h.getText().isEmpty() && bh!=Double.parseDouble(tf_b_h.getText()))
						{

							bh = Integer.parseInt(tf_b_h.getText());
							int bh = Integer.parseInt(tf_b_h.getText());
							int ah = Integer.parseInt(tf_a_h.getText());
							int aw = Integer.parseInt(tf_a_w.getText());
							int res = bh / ah * aw;
				            tf_b_w.setText(res+"");


						}
						else if(!tf_b_w.getText().isEmpty() && bw!=Double.parseDouble(tf_b_w.getText()))
						{
							bw = Integer.parseInt(tf_b_w.getText());
							int bw = Integer.parseInt(tf_b_w.getText());
							int ah = Integer.parseInt(tf_a_h.getText());
							int aw = Integer.parseInt(tf_a_w.getText());
							int res = ah * bw / aw;
				            tf_b_h.setText(res+"");

						}

					}

				}
			}
		}); 
		
		openImage.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(MainWindow.this);
				boolean isOk = false;
				File f = null;
				if (returnVal ==JFileChooser.APPROVE_OPTION) {   
						f = fc.getSelectedFile();
						if(Utils.validExt(f)){
							isOk = true;
						}
						else {
							messageError(messages.getString("error"), messages.getString("not_authorized_err"));
						}
				}
				if(isOk && !tf_b_h.getText().isEmpty() && !tf_b_w.getText().isEmpty())
				{
					try {
						BufferedImage img = ImageIO.read(f);
						BufferedImage imgNew = scale(img, Integer.parseInt(tf_b_h.getText()), Integer.parseInt(tf_b_w.getText()), Utils.getExt(f));						
						ImageIO.write(imgNew, Utils.getExt(f), f);
						messageInfo(messages.getString("success"), messages.getString("image_resize"));
					} catch (IOException ex)
					{
						Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
					} catch(OutOfMemoryError id)
					{
						messageError(messages.getString("java_error"), messages.getString("out_of_memory"));
					}
				}
				else {
					messageError(messages.getString("error"), messages.getString("put_value_before_resize_err"));
				}

				
			}});
		
		about.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setLocationRelativeTo(null);
				about.setVisible(true);
			}
			
		});

	 }
	 
	 /*
	  * Checks if the input numbers are numbers
	  * 
	  * @param in String to check
	  * @return <code>true</code> if String is a number or <code>false</code> if String isn't a number.
	  */	 
	 public boolean checkIfNumber(String in) {
	        
	        try {

	            Double.parseDouble(in);
	        
	        } catch (NumberFormatException ex) {
	        	messageError(messages.getString("error"), messages.getString("only_num_err"));
	            return false;
	        }
	        
	        return true;
	    }
	 
	/*
	 * Resize image 
	 * 
	 * @param bImage source image to resize/scale
	 * @param h Image Height
	 * @param w Image Width
	 * @return Resized image
	 */	 
	public static BufferedImage scale(BufferedImage bImage, int h, int w, String ext){
	
		int destWidth = w;
		int destHeight = h;
		BufferedImage bImageNew;
		
		GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		if( ext.equals("jpg") || ext.equals("jpeg"))
			bImageNew = config.createCompatibleImage(destWidth, destHeight);
		else
			bImageNew = config.createCompatibleImage(destWidth, destHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bImageNew.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.drawImage(bImage, 0, 0, destWidth, destHeight, 0, 0, bImage.getWidth(), bImage.getHeight(), null);
		graphics.dispose();
		
		return bImageNew;
	}

	public void messageError(String t, String m)
	{
		JOptionPane.showMessageDialog(null, m, t, JOptionPane.ERROR_MESSAGE);
	}
	public void messageInfo(String t, String m)
	{
		JOptionPane.showMessageDialog(null, m, t, JOptionPane.INFORMATION_MESSAGE);
	}



}
