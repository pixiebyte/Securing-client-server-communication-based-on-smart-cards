package jsr268gp.main;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.smartcardio.ATR;

import jsr268gp.cad.CAD;
import jsr268gp.cad.GPCAD;
import jsr268gp.loader.GPLoader;
import jsr268gp.util.Util;

public class Delete extends Main {

	public Delete() {
		super();
	}


	protected void execute() throws MainException {
		GPLoader loader = loaders.elementAt(activeCADIndex);	
//		String template = Resources.getTemplate();
		CAD cad = cads.elementAt(activeCADIndex);
//		CAPFile capfile = new CAPFile(template);
		ATR atr = null;
		
		try {
			// Connects to the built CAD.
			atr = cad.connect();
			
			System.out.println("ATR ; (byte)  " + Util.byteArrayToHexString(atr.getBytes(), "; (byte) "));
			loader.selectAndAuthenticate();
		} catch (Exception e) {
			throw new MainException(e);
		}
		try {
			for (int i = 0; i < applets.size(); i++) {
				System.out.println("> Deleting " + applets.size() + " applets...");
				delete(appletConfigs.get(i));
			}
		} catch (Exception e) {
			throw new MainException(e);
		}
	}
	

	/**
	 * Builds a new instance of the <tt>Manager</tt> tool, configures it and 
	 * executes each Applet found in the configuration.
	 *  
	 * @param args the options to be passed to the tool.
	 */
	public static void main(String[] args) {
		try {
			Delete manager = new Delete();
			manager.gpcads = new Vector<GPCAD>();
			manager.loaders = new Vector<GPLoader>();
		    manager.cads = new Vector<CAD>();
			
			System.out.println("Loads resources...");
			try {
				Resources.loadResources(".");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				System.exit(0);
			}
			
			
			System.out.println("Parses configuration file...");
			manager.parseConfigFile();
			String cardConfig = manager.config.getCardConfig();
			if (!new File(cardConfig).isAbsolute())
				cardConfig = Resources.getManagerConfig().getParent() + "/" + cardConfig;
			
			
			manager.buildCad(manager.config.getCad(),
					cardConfig);
			
			activeCADIndex = 0;
			
			manager.configureApplets();
			manager.getAppletConfig();
			
			manager.execute();
		} catch (MainException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
