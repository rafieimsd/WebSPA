package main.java.net.seleucus.wsp.checker;

/**
 *
 * @author masoud
 */


import main.java.net.seleucus.wsp.main.WSGestalt;
import main.java.net.seleucus.wsp.main.WSVersion;
import main.java.net.seleucus.wsp.main.WebSpa;

public class WSChecker extends WSGestalt {

	public WSChecker(final WebSpa myWebSpa) {
		super(myWebSpa);		
	}
	
	@Override
	public void exitConsole() {
		printlnWithTimeStamp("Goodbye!\n");
	}
	
	@Override
	public void runConsole() {
		
		println("");
		println("WebSpa - Single HTTP/S Request Authorisation- ");
//		println("version " + WSVersion.getValue() + " (webspa@seleucus.net)"); 		
		println("-this is checker mode!");
                println("");		

	}

}



