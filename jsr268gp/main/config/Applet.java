/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package jsr268gp.main.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Applet.
 * 
 * @version $Revision$ $Date$
 */
public class Applet implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _appletConfig
     */
    private java.lang.String _appletConfig;


      //----------------/
     //- Constructors -/
    //----------------/

    public Applet() 
     {
        super();
    } //-- jsr268gp.main.config.Applet()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'appletConfig'.
     * 
     * @return String
     * @return the value of field 'appletConfig'.
     */
    public java.lang.String getAppletConfig()
    {
        return this._appletConfig;
    } //-- java.lang.String getAppletConfig() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'appletConfig'.
     * 
     * @param appletConfig the value of field 'appletConfig'.
     */
    public void setAppletConfig(java.lang.String appletConfig)
    {
        this._appletConfig = appletConfig;
    } //-- void setAppletConfig(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Applet
     */
    public static jsr268gp.main.config.Applet unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (jsr268gp.main.config.Applet) Unmarshaller.unmarshal(jsr268gp.main.config.Applet.class, reader);
    } //-- jsr268gp.main.config.Applet unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
