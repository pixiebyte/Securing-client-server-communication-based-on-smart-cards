package jsr268gp.sampleapplet;
import javacard.framework.*;
import javacard.security.*;
import javacardx.crypto.*;

public class SampleTestApplet extends Applet {
	
	/******************** Constantes ************************/
	private static final byte CLA_PERSO = 0x00;
	public static final byte INS_MODULUS= 0x01;
	public static final byte INS_PUBLIC_EXPONENT= 0x02;
	public static final byte INS_PRIVATE_EXPONENT= 0x03;
	
	public static final byte CLA_MONAPPLET = (byte) 0xB0;
	final static byte INS_RANDOM = (byte) 0x04;
	final static byte INS_ENCRYPT = (byte) 0x05;
	final static byte INS_DECRYPT = (byte) 0x06;

	RandomData randomDataSecure = RandomData.getInstance(RandomData.ALG_PSEUDO_RANDOM);
	Cipher cipher = Cipher.getInstance(Cipher.ALG_RSA_PKCS1, false);

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    public static void install(byte[] bArray, short bOffset, byte bLength) {
        new SampleTestApplet().register();

    }

    public void process(APDU apdu) {
        if (selectingApplet()) {
            return;
        }
        byte[] buffer = apdu.getBuffer();
        
        if(buffer[ISO7816.OFFSET_CLA]!=CLA_PERSO)
            ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);

       

        switch (buffer[ISO7816.OFFSET_INS]) {
            case INS_MODULUS:
                short modulusLength = apdu.setIncomingAndReceive();
                byte[] modulus = JCSystem.makeTransientByteArray(modulusLength, JCSystem.CLEAR_ON_DESELECT);
                Util.arrayCopy(buffer, ISO7816.OFFSET_CDATA, modulus, (short) 0, modulusLength);
                this.publicKey.setModulus(modulus,(short)0, modulusLength);
                this.privateKey.setModulus(modulus,(short)0, modulusLength);
                break;
            case INS_PUBLIC_EXPONENT:
            	 short publicExponentLength = apdu.setIncomingAndReceive();
                 byte[] publicExponent = JCSystem.makeTransientByteArray(publicExponentLength, JCSystem.CLEAR_ON_DESELECT);
                 Util.arrayCopy(buffer, ISO7816.OFFSET_CDATA, publicExponent, (short) 0, publicExponentLength);
                 this.publicKey.setExponent(publicExponent,(short)0, publicExponentLength);
                break;
            case INS_PRIVATE_EXPONENT:
            	short privateExponentLength = apdu.setIncomingAndReceive();
                byte[] privateExponent = JCSystem.makeTransientByteArray(privateExponentLength, JCSystem.CLEAR_ON_DESELECT);
                Util.arrayCopy(buffer, ISO7816.OFFSET_CDATA, privateExponent, (short) 0, privateExponentLength);
                this.privateKey.setExponent(privateExponent,(short)0, privateExponentLength);
                break;
            case INS_RANDOM:
                secureRandomGeneration(apdu);
                break;
            case INS_ENCRYPT:
                encrypt(apdu);
                break;
            case INS_DECRYPT:
                decrypt(apdu);
                break;
            default:
                ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
        	}
        }

    private void secureRandomGeneration(APDU apdu) {
        byte[] buffer = apdu.getBuffer();
        byte[] randomData = JCSystem.makeTransientByteArray((short)buffer[ISO7816.OFFSET_P2], JCSystem.CLEAR_ON_DESELECT);
        randomDataSecure.generateData(randomData, (short) 0, (short) randomData.length);
        Util.arrayCopyNonAtomic(randomData, (short) 0, buffer,(short) ISO7816.OFFSET_CDATA, (short) randomData.length);
        apdu.setOutgoingAndSend((short)ISO7816.OFFSET_CDATA, (short) randomData.length);
    }

    private void encrypt(APDU apdu) {
        byte[] buffer = apdu.getBuffer();
        short dataLength = apdu.setIncomingAndReceive();
        byte[] dataToEncrypt = JCSystem.makeTransientByteArray(dataLength, JCSystem.CLEAR_ON_DESELECT);
        Util.arrayCopy(buffer, ISO7816.OFFSET_CDATA, dataToEncrypt, (short) 0, dataLength);

        byte[] encryptedData = JCSystem.makeTransientByteArray(dataLength, JCSystem.CLEAR_ON_DESELECT);
        cipher.init(publicKey, Cipher.MODE_ENCRYPT);
        short encryptedDataLength = cipher.doFinal(dataToEncrypt, (short) 0, dataLength, encryptedData, (short) 0);

        Util.arrayCopyNonAtomic(encryptedData, (short) 0, buffer, ISO7816.OFFSET_CDATA, encryptedDataLength);
        apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA, encryptedDataLength);
    }

    private void decrypt(APDU apdu) {
        byte[] buffer = apdu.getBuffer();
        short dataLength = apdu.setIncomingAndReceive();
        byte[] dataToDecrypt = JCSystem.makeTransientByteArray(dataLength, JCSystem.CLEAR_ON_DESELECT);
        Util.arrayCopy(buffer, ISO7816.OFFSET_CDATA, dataToDecrypt, (short) 0, dataLength);

        byte[] decryptedData = JCSystem.makeTransientByteArray(dataLength, JCSystem.CLEAR_ON_DESELECT);
        cipher.init(privateKey, Cipher.MODE_DECRYPT);
        short decryptedDataLength = cipher.doFinal(dataToDecrypt, (short) 0, dataLength, decryptedData, (short) 0);

        Util.arrayCopyNonAtomic(decryptedData, (short) 0, buffer, ISO7816.OFFSET_CDATA, decryptedDataLength);
        apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA, decryptedDataLength);
    }

    

}