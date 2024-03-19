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

import java.util.Collections;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Package.
 * 
 * @version $Revision$ $Date$
 */
public class Package implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _capfile
     */
    private java.lang.String _capfile = "0";

    /**
     * Field _myappletList
     */
    private java.util.ArrayList _myappletList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Package() 
     {
        super();
        setCapfile("0");
        _myappletList = new java.util.ArrayList();
    } //-- jsr268gp.applet.config.Package()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addMyapplet
     * 
     * 
     * 
     * @param vMyapplet
     */
    public void addMyapplet(jsr268gp.applet.config.Myapplet vMyapplet)
        throws java.lang.IndexOutOfBoundsException
    {
        _myappletList.add(vMyapplet);
    } //-- void addMyapplet(jsr268gp.applet.config.Myapplet) 

    /**
     * Method addMyapplet
     * 
     * 
     * 
     * @param index
     * @param vMyapplet
     */
    public void addMyapplet(int index, jsr268gp.applet.config.Myapplet vMyapplet)
        throws java.lang.IndexOutOfBoundsException
    {
        _myappletList.add(index, vMyapplet);
    } //-- void addMyapplet(int, jsr268gp.applet.config.Myapplet) 

    /**
     * Method clearMyapplet
     * 
     */
    public void clearMyapplet()
    {
        _myappletList.clear();
    } //-- void clearMyapplet() 

    /**
     * Method enumerateMyapplet
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateMyapplet()
    {
        return Collections.enumeration(_myappletList);
    } //-- java.util.Enumeration enumerateMyapplet() 

    /**
     * Returns the value of field 'capfile'.
     * 
     * @return String
     * @return the value of field 'capfile'.
     */
    public java.lang.String getCapfile()
    {
        return this._capfile;
    } //-- java.lang.String getCapfile() 

    /**
     * Method getMyapplet
     * 
     * 
     * 
     * @param index
     * @return Myapplet
     */
    public jsr268gp.applet.config.Myapplet getMyapplet(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _myappletList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (jsr268gp.applet.config.Myapplet) _myappletList.get(index);
    } //-- jsr268gp.applet.config.Myapplet getMyapplet(int) 

    /**
     * Method getMyapplet
     * 
     * 
     * 
     * @return Myapplet
     */
    public jsr268gp.applet.config.Myapplet[] getMyapplet()
    {
        int size = _myappletList.size();
        jsr268gp.applet.config.Myapplet[] mArray = new jsr268gp.applet.config.Myapplet[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (jsr268gp.applet.config.Myapplet) _myappletList.get(index);
        }
        return mArray;
    } //-- jsr268gp.applet.config.Myapplet[] getMyapplet() 

    /**
     * Method getMyappletCount
     * 
     * 
     * 
     * @return int
     */
    public int getMyappletCount()
    {
        return _myappletList.size();
    } //-- int getMyappletCount() 

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
     * Method removeMyapplet
     * 
     * 
     * 
     * @param vMyapplet
     * @return boolean
     */
    public boolean removeMyapplet(jsr268gp.applet.config.Myapplet vMyapplet)
    {
        boolean removed = _myappletList.remove(vMyapplet);
        return removed;
    } //-- boolean removeMyapplet(jsr268gp.applet.config.Myapplet) 

    /**
     * Sets the value of field 'capfile'.
     * 
     * @param capfile the value of field 'capfile'.
     */
    public void setCapfile(java.lang.String capfile)
    {
        this._capfile = capfile;
    } //-- void setCapfile(java.lang.String) 

    /**
     * Method setMyapplet
     * 
     * 
     * 
     * @param index
     * @param vMyapplet
     */
    public void setMyapplet(int index, jsr268gp.applet.config.Myapplet vMyapplet)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _myappletList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _myappletList.set(index, vMyapplet);
    } //-- void setMyapplet(int, jsr268gp.applet.config.Myapplet) 

    /**
     * Method setMyapplet
     * 
     * 
     * 
     * @param myappletArray
     */
    public void setMyapplet(jsr268gp.applet.config.Myapplet[] myappletArray)
    {
        //-- copy array
        _myappletList.clear();
        for (int i = 0; i < myappletArray.length; i++) {
            _myappletList.add(myappletArray[i]);
        }
    } //-- void setMyapplet(jsr268gp.applet.config.Myapplet) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Package
     */
    public static jsr268gp.applet.config.Package unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (jsr268gp.applet.config.Package) Unmarshaller.unmarshal(jsr268gp.applet.config.Package.class, reader);
    } //-- jsr268gp.applet.config.Package unmarshal(java.io.Reader) 

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
