package jsr268gp.sampleclient;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import com.sun.javacard.apduio.CadTransportException;
import javax.crypto.*;
import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadT1Client;
import javax.smartcardio.*;
import jsr268gp.util.Util;


public class SampleClient {
    	private static final byte CLA_PERSO = 0x00;
    	public static final byte INS_MODULUS= 0x01;
    	public static final byte INS_PUBLIC_EXPONENT= 0x02;
    	public static final byte INS_PRIVATE_EXPONENT= 0x03;
		public static final String ALGORITHM = "RSA";
	    public static final int KEY_SIZE = 1024;
	    
	    public static final String PRIVATE_KEY_FILE = "C:/keys/private.key";
	    public static final String PUBLIC_KEY_FILE = "C:/keys/public.key";
	    public static final String n_FILE = "C:/keys/n.txt";
	    public static final String e_FILE = "C:/keys/e.txt";
	    public static final String d_FILE = "C:/keys/d.txt";
	    
	    public SampleClient() {
	    	super();
	    	// TODO Auto-generated constructor stub
	    	}

	    	/**
	    	* @param args
	    	*/

	    public static KeyPair generateKey() {
	        try {
	            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
	            keyGen.initialize(KEY_SIZE);
	            KeyPair keyPair = keyGen.generateKeyPair();
	            
	            /*File privateKeyFile = new File(PRIVATE_KEY_FILE);
	            File publicKeyFile = new File(PUBLIC_KEY_FILE);
	            File nFile = new File(n_FILE);
	            File eFile = new File(e_FILE);
	            File dFile = new File(d_FILE);

	            privateKeyFile.getParentFile().mkdirs();
	            publicKeyFile.getParentFile().mkdirs();

	            try (ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
	                 ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
	                 PrintWriter nWriter = new PrintWriter(new FileOutputStream(nFile));
	                 PrintWriter eWriter = new PrintWriter(new FileOutputStream(eFile));
	                 PrintWriter dWriter = new PrintWriter(new FileOutputStream(dFile))) {

	                PublicKey publicKey = keyPair.getPublic();
	                PrivateKey privateKey = keyPair.getPrivate();

	                publicKeyOS.writeObject(publicKey);
	                privateKeyOS.writeObject(privateKey);

	                RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
	                RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;

	                nWriter.println(rsaPublicKey.getModulus());
	                eWriter.println(rsaPublicKey.getPublicExponent());
	                dWriter.println(rsaPrivateKey.getPrivateExponent());

		            }*/
	            
	            return keyPair;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	        
	    }


    public static void main(String[] args) throws CadTransportException, IOException  {

    	//Sélectionner votre lecteur de carte
    	//Le nom du lecteur se trouve dans la base de registres:
    	//Hkey local machine/software / Microsoft/cryptography/calais/readers
    	TerminalFactory tf = TerminalFactory.getDefault();
    	CardTerminals list = tf.terminals();
    	//CardTerminal cad = list.getTerminal("USB CCID Smart Card Reader 0");
    	//CardTerminal cad = list.getTerminal("ACS ACR1281U 0");
    	CardTerminal cad = list.getTerminal("ACS ACR1281 1S Dual Reader PICC 0");


     
     	//******************************************/
        
     	//**********Mise sous tension de la carte ******************/
     
    	try {
    		/*
    		*  Remarque: pour le transtypage de tableau byte vers une variable string en hexa
    		*  utiliser la méthode byteArrayToHexString de la classe Util
    		*/

    		//Etablir la connexion avec la carte à puce
    		Card c = cad.connect("T=0");
    		System.out.println("Card: "+c);

    		//Afficher l'ATR et sa taille (reset the card)
    		ATR atr = c.getATR();
    		System.out.println("ATR: "+Util.byteArrayToHexString(atr.getBytes(), " ")+"\n");

    		//Ouverture d'un canal de communication
    		CardChannel canal = c.getBasicChannel();

        //******************************************/
        
        //********* Sélection de l'applet: Commande APDU de type SELECT *********/ 
   
    		CommandAPDU commande = new CommandAPDU(new byte[]{(byte) 0x00, (byte) 0xA4, (byte) 0x04,(byte) 0x00,(byte) 0x07,(byte) 0xA0,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x04,(byte) 0x00,(byte) 0x02, (byte) 0x7F});
    		                                                                                                                     
    		ResponseAPDU reponse = canal.transmit(commande);
    		System.out.println("Reponse SELECT : "+Util.byteArrayToHexString(reponse.getBytes(), " ")+"\n");

        
        //******************************************/

         	KeyPair keyPair;
            keyPair = generateKey();  
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

            byte[] modulusBytes = rsaPublicKey.getModulus().toByteArray();
 	        byte[] publicExponentBytes = rsaPublicKey.getPublicExponent().toByteArray();
 	        byte[] privateExponentBytes = rsaPrivateKey.getPrivateExponent().toByteArray();

            
 	    //********* Commande APDU de MODULUS *********/     
 	        
 	       commande = new CommandAPDU(0,1, 0, 0, modulusBytes, 0);

 	      System.out.println("Commande2 : "+Util.byteArrayToHexString(commande.getBytes(), " "));

 	      //Envoyer la commande APDU
 	      reponse = canal.transmit(commande);

 	      //Afficher la réponse APDU
 	      System.out.println("Reponse2 : "+Util.byteArrayToHexString(reponse.getBytes(), " "));

            //********* Commande APDU de PUBLIC EXPONENT *********/ 
	       commande = new CommandAPDU(0,2, 0, 0, publicExponentBytes, 0);

	      System.out.println("Commande3: "+Util.byteArrayToHexString(commande.getBytes(), " "));

	      //Envoyer la commande APDU
	      reponse = canal.transmit(commande);

	      //Afficher la réponse APDU
	      System.out.println("Reponse3 : "+Util.byteArrayToHexString(reponse.getBytes(), " "));

            
            //********* Commande APDU de PRIVATE EXPONENT *********/ 
	       commande = new CommandAPDU(0,3, 0, 0,privateExponentBytes, 0);

	      System.out.println("Commande4 : "+Util.byteArrayToHexString(commande.getBytes(), " "));

	      //Envoyer la commande APDU
	      reponse = canal.transmit(commande);

	      //Afficher la réponse APDU
	      System.out.println("Reponse4 : "+Util.byteArrayToHexString(reponse.getBytes(), " "));

           
         //******************************************/
	      
          //********* Commande APDU de Random *********/ 
	       commande = new CommandAPDU(0,4, 0, 16, 16);

	      System.out.println("Commande5 : "+Util.byteArrayToHexString(commande.getBytes(), " "));

	      //Envoyer la commande APDU
	      reponse = canal.transmit(commande);
	      byte[] randomData = reponse.getData();
	      
	      //Afficher la réponse APDU
	      System.out.println("Reponse5 : "+Util.byteArrayToHexString(reponse.getBytes(), " "));

         
       //******************************************/
	      
          //********* Commande APDU de Encrypt *********/ 
	       commande = new CommandAPDU(0,5, 0, 0,randomData);

	      System.out.println("Commande6 : "+Util.byteArrayToHexString(commande.getBytes(), " "));

	      //Envoyer la commande APDU
	      reponse = canal.transmit(commande);
	      byte[] encryptedData = reponse.getData();
	      
	      //Afficher la réponse APDU
	      System.out.println("Reponse6 : "+Util.byteArrayToHexString(reponse.getBytes(), " "));

         
       //******************************************/
	      
          //********* Commande APDU de decrypt *********/ 
	       commande = new CommandAPDU(0,6, 0, 0,encryptedData);

	      System.out.println("Commande7 : "+Util.byteArrayToHexString(commande.getBytes(), " "));

	      //Envoyer la commande APDU
	      reponse = canal.transmit(commande);

	      //Afficher la réponse APDU
	      System.out.println("Reponse7 : "+Util.byteArrayToHexString(reponse.getBytes(), " "));

         
       //******************************************/
            
         //******************* Mise hors tension de la carte ******************/ 
            
	   // Déconnexion
	      c.disconnect(false);

	      } catch (Exception e) {
	      e.printStackTrace();
	      System.exit(1);
	      }
	      }
    	//******************************************/
            
      
    }

