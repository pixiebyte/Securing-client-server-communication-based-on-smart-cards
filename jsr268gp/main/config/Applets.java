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

import java.util.Collections;


import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Applets.
 * 
 * @version $Revision$ $Date$
 */
public class Applets implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _appletList
     */
    private java.util.ArrayList _appletList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Applets() 
     {
        super();
        _appletList = new java.util.ArrayList();
    } //-- jsr268gp.main.config.Applets()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addApplet
     * 
     * 
     * 
     * @param vApplet
     */
    public void addApplet(jsr268gp.main.config.Applet vApplet)
        throws java.lang.IndexOutOfBoundsException
    {
        _appletList.add(vApplet);
    } //-- void addApplet(jsr268gp.main.config.Applet) 

    /**
     * Method addApplet
     * 
     * 
     * 
     * @param index
     * @param vApplet
     */
    public void addApplet(int index, jsr268gp.main.config.Applet vApplet)
        throws java.lang.IndexOutOfBoundsException
    {
        _appletList.add(index, vApplet);
    } //-- void addApplet(int, jsr268gp.main.config.Applet) 

    /**
     * Method clearApplet
     * 
     */
    public void clearApplet()
    {
        _appletList.clear();
    } //-- void clearApplet() 

    /**
     * Method enumerateApplet
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateApplet()
    {
        return Collections.enumeration(_appletList);
    } //-- java.util.Enumeration enumerateApplet() 

    /**
     * Method getApplet
     * 
     * 
     * 
     * @param index
     * @return Applet
     */
    public jsr268gp.main.config.Applet getApplet(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _appletList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (jsr268gp.main.config.Applet) _appletList.get(index);
    } //-- jsr268gp.main.config.Applet getApplet(int) 

    /**
     * Method getApplet
     * 
     * 
     * 
     * @return Applet
     */
    public jsr268gp.main.config.Applet[] getApplet()
    {
        int size = _appletList.size();
        jsr268gp.main.config.Applet[] mArray = new jsr268gp.main.config.Applet[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (jsr268gp.main.config.Applet) _appletList.get(index);
        }
        return mArray;
    } //-- jsr268gp.main.config.Applet[] getApplet() 

    /**
     * Method getAppletCount
     * 
     * 
     * 
     * @return int
     */
    public int getAppletCount()
    {
        return _appletList.size();
    } //-- int getAppletCount() 

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
     * Method removeApplet
     * 
     * 
     * 
     * @param vApplet
     * @return boolean
     */
    public boolean removeApplet(jsr268gp.main.config.Applet vApplet)
    {
        boolean removed = _appletList.remove(vApplet);
        return removed;
    } //-- boolean removeApplet(jsr268gp.main.config.Applet) 

    /**
     * Method setApplet
     * 
     * 
     * 
     * @param index
     * @param vApplet
     */
    public void setApplet(int index, jsr268gp.main.config.Applet vApplet)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _appletList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _appletList.set(index, vApplet);
    } //-- void setApplet(int, jsr268gp.main.config.Applet) 

    /**
     * Method setApplet
     * 
     * 
     * 
     * @param appletArray
     */
    public void setApplet(jsr268gp.main.config.Applet[] appletArray)
    {
        //-- copy array
        _appletList.clear();
        for (int i = 0; i < appletArray.length; i++) {
            _appletList.add(appletArray[i]);
        }
    } //-- void setApplet(jsr268gp.main.config.Applet) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Applets
     */
    public static jsr268gp.main.config.Applets unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (jsr268gp.main.config.Applets) Unmarshaller.unmarshal(jsr268gp.main.config.Applets.class, reader);
    } //-- jsr268gp.main.config.Applets unmarshal(java.io.Reader) 

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
