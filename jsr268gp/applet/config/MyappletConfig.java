/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package jsr268gp.applet.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class MyappletConfig.
 * 
 * @version $Revision$ $Date$
 */
public class MyappletConfig implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _package
     */
    private jsr268gp.applet.config.Package _package;


      //----------------/
     //- Constructors -/
    //----------------/

    public MyappletConfig() 
     {
        super();
    } //-- jsr268gp.applet.config.MyappletConfig()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'package'.
     * 
     * @return Package
     * @return the value of field 'package'.
     */
    public jsr268gp.applet.config.Package getPackage()
    {
        return this._package;
    } //-- jsr268gp.applet.config.Package getPackage() 

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
     * Sets the value of field 'package'.
     * 
     * @param _package
     * @param package the value of field 'package'.
     */
    public void setPackage(jsr268gp.applet.config.Package _package)
    {
        this._package = _package;
    } //-- void setPackage(jsr268gp.applet.config.Package) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return MyappletConfig
     */
    public static jsr268gp.applet.config.MyappletConfig unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (jsr268gp.applet.config.MyappletConfig) Unmarshaller.unmarshal(jsr268gp.applet.config.MyappletConfig.class, reader);
    } //-- jsr268gp.applet.config.MyappletConfig unmarshal(java.io.Reader) 

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
