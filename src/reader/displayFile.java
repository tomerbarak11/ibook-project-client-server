package reader;

import java.net.URI;
import java.awt.Desktop;

public class displayFile {
	
	public displayFile(String s){
	  try {
		  Desktop d=Desktop.getDesktop();
		  d.browse(new URI(s));
  	  } catch (Exception ex) {
		ex.printStackTrace();
	  }
	}
}