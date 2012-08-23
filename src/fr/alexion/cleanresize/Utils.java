package fr.alexion.cleanresize;

import java.io.File;


public class Utils {
	
	private final static String jpeg = "jpeg";
	private final static String jpg = "jpg";
	private final static String gif = "gif";
	private final static String png = "png";
    
    
    
    /*
     * Get file extension
     * 
     * @param f File to get file extension
     * @return File extension
     */
    public static String getExt(File f)
    {
    	String ext = "";
    	String s = f.getName();
    	int i = s.lastIndexOf('.');
   		ext = s.substring(i+1, s.length()).toLowerCase();
    	return ext;
    }
    
    
    /*
     * Valid extension file
     * 
     * @param f File to valid extension
     * @return <code>true</code> if is an image file or <code>false</code> if isn't an image file. 
     */
    public static boolean validExt(File f) {
    	String ext = getExt(f);    	
    	if(ext.equals(png) || ext.equals(jpg) || ext.equals(jpeg) || ext.equals(gif))
    		return true;
    	else
    		return false;
    }


    
}
