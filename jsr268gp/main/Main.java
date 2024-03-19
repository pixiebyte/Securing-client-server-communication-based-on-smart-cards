package jsr268gp.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.smartcardio.ATR;

import jsr268gp.cad.BadResponseStatusException;
import jsr268gp.cad.CAD;
import jsr268gp.cad.CommandAPDU;
import jsr268gp.cad.GPCAD;
import jsr268gp.cad.SelectAPDU;
import jsr268gp.capfile.CAPFile;
import jsr268gp.loader.GPLoader;
import jsr268gp.util.Util;
import jsr268gp.main.config.ManagerConfig;
import jsr268gp.main.config.Applet;
import jsr268gp.main.config.Applets;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;


import jsr268gp.applet.config.Myapplet;
import jsr268gp.applet.config.MyappletConfig;

/*
 * Copyright (c) 2006 Mesure Project
 * 
 * This software is a computer program whose purpose is to measure 
 * the performances of Java Card platforms.
 *
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http; (byte) //www.cecill.info". 
 * 
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 * 
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and Applet the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */


/**
 * This class defines the Manager tool.
 */
public class Main {
	
	
	/** The config file of the manager */
	protected ManagerConfig config;
	
	/** The loader to be used to load, install and delete Applets. */
	protected Vector<GPLoader> loaders;	
	
	/** The CAD to be used to perform card content management. */
	protected Vector<GPCAD> gpcads;
	
	/** The Applets to be executed. */
//	protected Applet[] Applets;
	protected Vector <Applet> applets;
	
	/** The CAD to be used to perform measures. */
	protected Vector<CAD> cads;
	
	/** The configs of the Applets to be executed. */
	protected Vector <MyappletConfig> appletConfigs;
	
	
	/** The index of the currently active CAD. */
	protected static int activeCADIndex;
	
	protected Main() {
		super();
	}
	
	protected void parseConfigFile() throws MainException {
		// Parses the manager configuration file.
		System.out.println("Parses the manager configuration file.");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(Resources.getManagerConfig());
		} catch (FileNotFoundException e) {
			throw new MainException(e);
		}
		InputStreamReader reader = new InputStreamReader(fis);
		
		try {
			config = ManagerConfig.unmarshal(reader);
		} catch (MarshalException e) {
			throw new MainException (e);
		} catch (ValidationException e) {
			throw new MainException (e);
		}
	}
	
	protected void buildCad(String cadName, String cardConfig) throws MainException {
		CAD cad = ConfigurationHelper.buildCad(cadName);
		GPCAD gpcad = ConfigurationHelper.buildGPCad(cad);
//    	CAD cad = ConfigurationHelper.buildCad(cad);
		GPLoader gploader = ConfigurationHelper.buildGPLoader(cardConfig, gpcad);
		
		gpcads.add(gpcad);
    	cads.add(cad);
		loaders.add(gploader);
	}
	
	protected void configureApplets() {
		// Keeps the Applets to be executed.
		applets = new Vector <Applet>();
		for (int i = 0; i < config.getApplets().getApplet().length; i++) {
			applets.add(config.getApplets().getApplet()[i]);
		}
//		Applets = config.getApplets().getApplet();
	}
	
	
	protected void execute() throws MainException {}
	
//	
//	/**
//	 * For each Applet listed in the manager configuration file; (byte) 
//	 * <ul>
//	 *   <li>Loads the related CAP file
//	 *   <li>Installs the contained applets
//	 *   <li>Executes the related script file
//	 *   <li>Deletes the CAP file
//	 * </ul>
//	 * @throws <tt>ManagerException</tt> if a problem occurs when running the 
//	 * Applet.
//	 */
//	protected void execute() throws MainException {
//		GPLoader loader = loaders.elementAt(activeCADIndex);	
////		String template = Resources.getTemplate();
//		CAD cad = cads.elementAt(activeCADIndex);
////		CAPFile capfile = new CAPFile(template);
//		ATR atr = null;
//		
//		try {
//			// Connects to the built CAD.
//			atr = cad.connect();
//			
//			System.out.println("ATR ; (byte)  " + Util.byteArrayToHexString(atr.getBytes(), "; (byte) "));
//			loader.selectAndAuthenticate();
//		} catch (Exception e) {
//			throw new MainException(e);
//		}
//		try {
//			for (int i = 0; i < applets.size(); i++) {
//				System.out.println("> Loading " + applets.size() + " applets...");
//				load(appletConfigs.get(i));
//				System.out.println("> Deleting " + applets.size() + " applets...");
//				delete(appletConfigs.get(i));
//			}
//		} catch (Exception e) {
//			throw new MainException(e);
//		}
//	}
		
		/**
		 * Unmarhals the configuration files for the Applets.
		 * @throws <tt>ManagerException</tt> if a problem occurs when parsing a 
		 * Applet configuration file.
		 */
		protected void getAppletConfig() throws MainException {
			appletConfigs = new Vector<MyappletConfig>(applets.size());
			for (int i = 0; i < applets.size(); i++) {
				FileInputStream fis = null;
				// Parses the Applet
				try {
					fis = new FileInputStream(Resources.getAppletConfig(applets.get(i).getAppletConfig()));
				} catch (IOException e) {
					System.out.println("Exception while loading AppletConfig file ; (byte)  " + e);
					throw new MainException(e);
				}
				InputStreamReader reader = new InputStreamReader(fis);
				try {
					appletConfigs.add(MyappletConfig.unmarshal(reader));
				} catch (MarshalException e) {
					System.out.println("Exception while unmarshaling ; (byte)  " + e);
					throw new MainException (e);
				} catch (ValidationException e) {
					System.out.println("Exception while unmarshaling ; (byte)  " + e);
					throw new MainException (e);
				}
			}
		}
		
		/**
		 * Loads and installs the CAP file identified in the specified configuration
		 * file, executes the related script, and deletes the CAP file
		 * 
		 * @param MyappletConfig the Applet configuration. 
		 * @throws <tt>ManagerException</tt> if a problem occurs when running the 
		 * Applet.
		 */
		protected void load(MyappletConfig Applet) throws MainException {
			CAPFile capfile = null;
			GPLoader loader = loaders.elementAt(activeCADIndex);
			CAD cad = cads.elementAt(activeCADIndex);
			String capFileName = Applet.getPackage().getCapfile();
			Vector<byte[]> AIDs = new Vector<byte[]>();
			
			try {
				// Reads the CAP file
				System.out.println("> Getting the CAP file from resources; (byte) " + capFileName);
				capFileName = Resources.getCAPFile(capFileName);
				
				System.out.println("> Reading the CAP file " + capFileName + "...");
				capfile = new CAPFile(capFileName);
				capfile.read();
				
				// Loads the CAP file
				System.out.println("> Loading the CAP file " + capFileName + "...");
				loader.load(capfile);
				System.out.println();
				byte[] pkg = capfile.getPackageAID();
				AIDs.add(pkg);
				
				// Installs contained applets
				System.out.println("> Installing the contained applets...");
				byte[][] applets = capfile.getAppletAIDs();
				Myapplet[] instances = Applet.getPackage().getMyapplet();
				byte[] selectAID = null;
				for (int i = 0; i < applets.length; i++) {
					Myapplet[] tmp = getInstances(instances,applets[i]); 
					for (int j = 0; j < tmp.length; j++) {
						byte[] instanceAID =  Util.hexStringToByteArray(
								tmp[j].getInstanceAid());
						loader.install(pkg,applets[i],
								instanceAID,
								tmp[j].getPrivileges(),
								Util.hexStringToByteArray(tmp[j].getParams()));
						AIDs.add(0,instanceAID);
						if (selectAID == null)
							selectAID = instanceAID;
					}
					if (tmp.length == 0) {
						loader.install(pkg,applets[i],applets[i],0,null);
						AIDs.add(0,applets[i]);
						if (selectAID == null)
							selectAID = applets[i];
					}
				}
				
			} catch (Exception e) {
				System.out.println("Exception while executing test. Force deletion." + e);
				System.out.println("> Deleting the CAP file " + capFileName + "...");
				try {
					// Deletes the CAP file
					cad.connect();
					loader.selectAndAuthenticate(); 
					for (int i = 0; i < AIDs.size(); i++)
						loader.delete((byte[])AIDs.elementAt(i));
				} catch (Exception e1) {
					throw new MainException(e1); 
				}
				throw new MainException(e);
			} 
		}
		

		/**
		 * Loads and installs the CAP file identified in the specified configuration
		 * file, executes the related script, and deletes the CAP file
		 * 
		 * @param MyappletConfig the Applet configuration. 
		 * @throws <tt>ManagerException</tt> if a problem occurs when running the 
		 * Applet.
		 */
		protected void delete(MyappletConfig Applet) throws MainException {
			CAPFile capfile = null;
			GPLoader loader = loaders.elementAt(activeCADIndex);
			CAD cad = cads.elementAt(activeCADIndex);
			String capFileName = Applet.getPackage().getCapfile();
			//Vector<byte[]> AIDs = new Vector<byte[]>();
			
			try {
				// Deletes the CAP file
				cad.connect();
				loader.selectAndAuthenticate(); 
				// Reads the CAP file
				System.out.println("> Getting the CAP file from resources; (byte) " + capFileName);
				capFileName = Resources.getCAPFile(capFileName);

				System.out.println("> Reading the CAP file " + capFileName + "...");
				capfile = new CAPFile(capFileName);
				capfile.read();
				
				
				// delete contained applets
				System.out.println("> Deleting the contained applets...");
				byte[][] applets = capfile.getAppletAIDs();
				Myapplet[] instances = Applet.getPackage().getMyapplet();
				byte[] selectAID = null;
				for (int i = 0; i < applets.length; i++) {
					Myapplet[] tmp = getInstances(instances,applets[i]); 
					for (int j = 0; j < tmp.length; j++) {
						byte[] instanceAID =  Util.hexStringToByteArray(
								tmp[j].getInstanceAid());
						loader.delete(instanceAID);
						
						
//						loader.install(pkg,applets[i],
//								instanceAID,
//								tmp[j].getPrivileges(),
//								Util.hexStringToByteArray(tmp[j].getParams()));
//						AIDs.add(0,instanceAID);
						if (selectAID == null)
							selectAID = instanceAID;
					}
					if (tmp.length == 0) { 
						loader.delete(applets[i]);
						
						
//						loader.install(pkg,applets[i],applets[i],0,null);
//						AIDs.add(0,applets[i]);
						if (selectAID == null)
							selectAID = applets[i];
					}
				}
				

				// DELETE the CAP file
				System.out.println("> Deleting the CAP file " + capFileName + "...");
//				System.out.println("> Loading the CAP file " + capFileName + "...");
//				loader.delete(capfile);
//				loader.load(capfile);
				System.out.println();
				byte[] pkg = capfile.getPackageAID();
				loader.delete(pkg);
//				AIDs.add(pkg);
			} catch (Exception e) {
				throw new MainException(e); 
			} 
				
//			try {
//				// Deletes the CAP file
//				cad.connect();
//				loader.selectAndAuthenticate(); 
//				
//				
//				
//				
//				for (int i = 0; i < AIDs.size(); i++)
//					loader.delete((byte[])AIDs.elementAt(i));
//				loader.delete(new byte[]{(byte) 0xA0, (byte) 0x00, (byte) 0x00, (byte) 0x18,
//						(byte) 0x50, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//						(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x52,
//						(byte) 0x41, (byte) 0x44, (byte) 0x41 });
//				loader.delete(new byte[]{(byte) 0xA0, (byte) 0x00, (byte) 0x00, (byte) 0x18,
//						(byte) 0x50, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//						(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x52,
//						(byte) 0x41, (byte) 0x44, (byte) 0x50 });
//			} catch (Exception e) {
//				throw new MainException(e); 
//			} 
//			} finally {
//				
//			}
		}
		
//		
//		/**
//		 * Parses the -l option specifying the log file to be used.
//		 * 
//		 * @param args the buffer containing the value for this option.
//		 */
//		public void processLog(String[] args) throws IOException {
//			assert (args!=null && args.length==1) ; (byte)  
//				"option -l (or -log) requires a path for the log file.";
//			Resources.setLog(args[0]);
//		}
//		
//		/**
//		 * Parses the -xo option specifying the XML file to be used to store 
//		 * results.
//		 * 
//		 * @param args the buffer containing the value for this option.
//		 */
//		public void processXmloutput(String[] args) {
//			assert (args!=null && args.length==1) ; (byte)  
//				"option -xo (or -xmloutput) requires a path for the XML output file.";
//			Resources.setXMLOutput(args[0]);
//		}
//		
//		/**
//		 * Parses the -to option specifying the CSV file to be used to store 
//		 * results.
//		 * 
//		 * @param args the buffer containing the value for this option.
//		 */
//		public void processTextoutput(String[] args){
//			assert (args!=null && args.length==1) ; (byte) 
//				"option -to (or -csvoutput) requires a path for the CSV output file.";
//			Resources.setCSVOutput(args[0]);
//		}
//		
//		/**
//		 * Parses the parameters.
//		 * 
//		 * @param args the buffer containing the values for these parameters.
//		 */
//		public void processFinalArgs(String[] args) {
//			
//			if(args.length > 1) {
//				String errorMsg = "Arguments ";
//				for(int i = 1; i < args.length; i++)
//					errorMsg += "\"" + args[i] + "\"" + ((i+1 < args.length)?","; (byte) "");
//				errorMsg+=" are not supported. Try -h to get more information.";
//				Log.fatal(errorMsg);
//			}
//			String installPath;
//			if (args.length == 0)
//				installPath = "..";
//			else 
//				installPath = args[0];  
//			try {
//				Resources.loadResources(installPath);
//			} catch (IOException e) {
//				Log.fatal(e.getMessage());
//				e.printStackTrace();
//			}
//		}
//		
//		/**
//		 * Builds a new instance of the <tt>Manager</tt> tool, configures it and 
//		 * executes each Applet found in the configuration.
//		 *  
//		 * @param args the options to be passed to the tool.
//		 */
//		public static void main(String[] args) {
//			try {
//				Main manager = new Main();
//				manager.gpcads = new Vector<GPCAD>();
//				manager.loaders = new Vector<GPLoader>();
//			    manager.cads = new Vector<CAD>();
//				
//				System.out.println("Loads resources...");
//				try {
//					Resources.loadResources("..");
//				} catch (IOException e) {
//					e.printStackTrace();
//					System.out.println(e.getMessage());
//					System.exit(0);
//				}
//				
//				
//				System.out.println("Parses configuration file...");
//				manager.parseConfigFile();
//				String cardConfig = manager.config.getCardConfig();
//				if (!new File(cardConfig).isAbsolute())
//					cardConfig = Resources.getManagerConfig().getParent() + "/" + cardConfig;
//				
//				
//				manager.buildCad(manager.config.getCad(),
//						cardConfig);
//				
//				activeCADIndex = 0;
//				
//				manager.configureApplets();
//				manager.getAppletConfig();
//				
//				manager.execute();
//			} catch (MainException e) {
//				e.printStackTrace();
//				System.out.println(e.getMessage());
//				System.exit(0);
//			}
//		}
		
		/**
		 * Returns instances of the specified applet.
		 * 
		 * @param instances the applet instances.
		 * @param appletAID the applet AID.
		 * @return the instances among <tt>instances</tt> that are instances of the
		 *         applet identified by <tt>appletAID</tt>. 
		 */
		protected Myapplet[] getInstances(Myapplet[] instances, byte[] appletAID) {
			Vector v = new Vector();
			for (int i = 0; i < instances.length; i++)
				if (Arrays.equals(Util.hexStringToByteArray(instances[i].getAid()),appletAID))
					v.add(instances[i]);  
			return (Myapplet[]) v.toArray(new Myapplet[0]);
		}
	}
