package jsr268gp.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

import jsr268gp.util.Util;

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
 * "http://www.cecill.info". 
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
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */


/**
 * The test manager resources. 
 */
public class Resources {

  //============================================================================
  // External Resources
  //============================================================================	
  /** The installation folder. */
  private static String root;
    
  // Libraries
  /** The relative path of the folder containing the libraries. */
  private static final String LIB = "/lib/";
  
  // Executable Files
  /** The relative path of the folder containing the executable files. */
  private static final String BIN = "/bin/";

  // Configuration Files
  /** The relative path of the folder containing the configuration files. */
  private static final String CONFIG = "/config/";
  
  /** The relative path of the folder containing the manager configuration files. */
  private static final String MANAGER_CONFIG = CONFIG + "manager/";
  
  // Temporary Files
  /** The relative path of the folder containing the temporary files. */
  private static final String TMP = "/tmp/";
  
  /** The folder containing the temporary files. */
  private static File tmp;
  
  // Log File
  /** The generic output stream used to write to the log file. */  
  private static PrintStream out;

  /** The specific output stream used to write to the log file. */ 
  private static FileOutputStream fos;
  
  /** The log file. */
  private static File log;
  
  /**
   * Builds the log file.
   * 
   * @param path the path of the log file to be built.
   */
  static void setLog(String path) throws IOException {
    log = new File(tmp, path);
	fos = new FileOutputStream(log);
	out = new PrintStream(fos);
//	jsr268gp.log.Log.setOut(out);
  }
  
  // Input Files
  /** The manager configuration file. */
  private static File managerConfig;
  
  /**
   * Returns the manager configuration file.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getManagerConfig() {
    return managerConfig;
  }
  
  /**
   * Builds the manager configuration file.
   * 
   * @param path the path of the manager configuration file.
   * @throws <tt>FileNotFoundException</tt> exception if the specified file
   *         does not exist.
   */
  static void setManagerConfig(String path) throws FileNotFoundException {
	managerConfig = exists(path);
  }

  // Output Files
  /** The XML file containing the results. */
  private static File xmlOutput;
  
  /**
   * Returns the XML file containing the results.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getXMLOutput() {
    return xmlOutput;
  }  
  
  /**
   * Builds the XML file containing the results.
   * 
   * @param path the path of the XML file to be built.
   */
  static void setXMLOutput(String path) {
	xmlOutput = new File(tmp, path);
  }
  
  /** The CSV file containing the results. */
  private static File csvOutput;
  
  /**
   * Returns the CSV file containing the results.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getCSVOutput() {
    return csvOutput;
  }  
  
  /**
   * Builds the XML file containing the results.
   * 
   * @param path the path of the XML file to be built.
   */
  static void setCSVOutput(String path) {
	csvOutput = new File(tmp, path);
  }
    
  //============================================================================
  // Internal Resources
  //============================================================================
//  // XSD Files
//  /** The relative path of the XSD file used for results. */
//  private static final String RESULT_XSD = 
//    "../../lib/xml/test_result/TestResult.xsd";
  
  /** The complete path of the XSD file used for results. */
  private static String xsdOutput;
  
  /**
   * Returns the complete path of the XSD file used for results.
   * 
   * @return the <tt>String</tt> object representing this path.
   */
  public static String getXSDOutput() {
    return xsdOutput;
  }
//  
//  // CAP Files
//  /** The relative path of the template CAP file. */
//  private static final String TEMPLATE = 
//      "../../benchs/lib/templates/javacard/templates.cap";
//  
//  /** The complete path of the template CAP file. */
//  private static String template;
//
//  /**
//   * Returns the complete path of the template CAP file.
//   * 
//   * @returns the <tt>String</tt> object representing this path.
//   */
//  public static String getTemplate() {
//    return template;
//  }
  
  /**
   * Returns the complete path of a test configuration file.
   * 
   * @param configPath the relative path of a test configuration file.
   * @return the <tt>String</tt> object representing its complete path.
   * @throws <tt>IOException</tt> exception if a problem occurs when building
   *         the complete path.
   */
  public static String getAppletConfig(String configPath) throws IOException {
	  return Util.Url2Path(Resources.class.getResource(
        "../../" + configPath),false,tmp);
  }
  
  /**
   * Returns the complete path of a test CAP file.
   * 
   * @param CAPFilePath the relative path of a test CAP file.
   * @return the <tt>String</tt> object representing its complete path.
   * @throws <tt>IOException</tt> exception if a problem occurs when building
   *         the complete path.
   */
  public static String getCAPFile(String CAPFilePath) throws IOException {
	  URL url = Resources.class.getResource("../../" + CAPFilePath);
	  // splitted to allow debugging.. url can be null.
	  if (url == null)
		  throw new IOException("Can't find file : " + "../../" + CAPFilePath);
	  String s = Util.Url2Path(url, true, tmp);
	  return s;
  }
  
  /**
   * Loads useful resources.
   * 
   * @param installPath the installation path.
   * @throw <tt>IOException</tt> exception if some required resources
   *        are missing.
   */
  static void loadResources(String installPath) throws IOException
  {
	System.out.println("Loading resources...");
	//=========================================================================
    // External Resources
	//=========================================================================  
	// Installation Path
	root = installPath;
	exists(root);
	
	// Libraries
	String libPath = root + LIB;
	exists(libPath);
	exists(libPath + "castor-1.0.3.jar");
	exists(libPath + "commons-logging-1.1.jar");
	exists(libPath + "xerces-J_1.4.0.jar");
	
//	// Temporary Files
//	String tmpPath = root + TMP;
//	tmp = new File(tmpPath);
//	if (!tmp.exists())
//	  tmp.mkdir();
	
    // Log File
//    if (log == null)
//      setLog(tmpPath + "log.txt");

	// Input Files
	if (managerConfig == null)
	  setManagerConfig(root + MANAGER_CONFIG + "ManagerConfig.xml");
	
	// Output Files
	if (xmlOutput == null)
	  xmlOutput = new File(tmp,"results.xml");
	if (csvOutput == null)
	  csvOutput = new File(tmp,"results.csv");
	      
    //=========================================================================
	// Internal Resources
	//=========================================================================
	// XSD Files
//	xsdOutput = 
//	    Util.Url2Path(Resources.class.getResource(RESULT_XSD),false,tmp);
//	
//	// CAP Files
//	template = Util.Url2Path(Resources.class.getResource(TEMPLATE),true,tmp);
  }
  
  /**
   * Checks that the specified file or directory exists.
   *
   * @param name the name of the file or directory.
   * @return the associated <tt>File</tt> object if the specified file or
   *         directory exists.
   * @throw <tt>FileNotFoundException</tt> exception otherwise.
   */
  static File exists(String name) throws FileNotFoundException
  {
    File f = new File(name);
    if (!f.exists())
      throw new FileNotFoundException(f.getAbsolutePath());
    return f;
  }
  
//  public static TestConfig loadTestConfig(String args) throws ManagerException {
//	  FileInputStream fis = null;
//	  TestConfig config = null;
//	  // Parses the test
//	  try {
//		  fis = new FileInputStream(getTestConfig(args));
//	  } catch (Exception e) {
//		  throw new ManagerException(e);
//	  }
//	  InputStreamReader reader = new InputStreamReader(fis);
//	  try {
//		  config = TestConfig.unmarshal(reader);
//	  } catch (MarshalException e) {
//		  throw new ManagerException (e);
//	  } catch (ValidationException e) {
//		  throw new ManagerException (e);
//	  } 
//	  return config;
//  }
}
