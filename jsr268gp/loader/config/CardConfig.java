/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package jsr268gp.loader.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class CardConfig.
 * 
 * @version $Revision$ $Date$
 */
public class CardConfig implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _cardManagerAid
     */
    private java.lang.String _cardManagerAid = "0xA0:0x00:0x00:0x00:0x03:0x00:0x00:0x00";

    /**
     * Field _mutualAuthenticate
     */
    private jsr268gp.loader.config.MutualAuthenticate _mutualAuthenticate;

    /**
     * Field _load
     */
    private jsr268gp.loader.config.Load _load;


      //----------------/
     //- Constructors -/
    //----------------/

    public CardConfig() 
     {
        super();
        setCardManagerAid("0xA0:0x00:0x00:0x00:0x03:0x00:0x00:0x00");
    } //-- jsr268gp.loader.config.CardConfig()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'cardManagerAid'.
     * 
     * @return String
     * @return the value of field 'cardManagerAid'.
     */
    public java.lang.String getCardManagerAid()
    {
        return this._cardManagerAid;
    } //-- java.lang.String getCardManagerAid() 

    /**
     * Returns the value of field 'load'.
     * 
     * @return Load
     * @return the value of field 'load'.
     */
    public jsr268gp.loader.config.Load getLoad()
    {
        return this._load;
    } //-- jsr268gp.loader.config.Load getLoad() 

    /**
     * Returns the value of field 'mutualAuthenticate'.
     * 
     * @return MutualAuthenticate
     * @return the value of field 'mutualAuthenticate'.
     */
    public jsr268gp.loader.config.MutualAuthenticate getMutualAuthenticate()
    {
        return this._mutualAuthenticate;
    } //-- jsr268gp.loader.config.MutualAuthenticate getMutualAuthenticate() 

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
     * Sets the value of field 'cardManagerAid'.
     * 
     * @param cardManagerAid the value of field 'cardManagerAid'.
     */
    public void setCardManagerAid(java.lang.String cardManagerAid)
    {
        this._cardManagerAid = cardManagerAid;
    } //-- void setCardManagerAid(java.lang.String) 

    /**
     * Sets the value of field 'load'.
     * 
     * @param load the value of field 'load'.
     */
    public void setLoad(jsr268gp.loader.config.Load load)
    {
        this._load = load;
    } //-- void setLoad(jsr268gp.loader.config.Load) 

    /**
     * Sets the value of field 'mutualAuthenticate'.
     * 
     * @param mutualAuthenticate the value of field
     * 'mutualAuthenticate'.
     */
    public void setMutualAuthenticate(jsr268gp.loader.config.MutualAuthenticate mutualAuthenticate)
    {
        this._mutualAuthenticate = mutualAuthenticate;
    } //-- void setMutualAuthenticate(jsr268gp.loader.config.MutualAuthenticate) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return CardConfig
     */
    public static jsr268gp.loader.config.CardConfig unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (jsr268gp.loader.config.CardConfig) Unmarshaller.unmarshal(jsr268gp.loader.config.CardConfig.class, reader);
    } //-- jsr268gp.loader.config.CardConfig unmarshal(java.io.Reader) 

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
