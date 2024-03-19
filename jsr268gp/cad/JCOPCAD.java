package jsr268gp.cad;

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

/*
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/lib/cad/JSR268CAD.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

import java.io.IOException;

import javax.smartcardio.ATR;

import com.ibm.jc.JCTerminal;
import com.ibm.jc.JCard;
import com.ibm.jc.terminal.RemoteJCTerminal;

public class JCOPCAD extends CAD {
	
	/** The underlying JCOP CAD. */
	private JCard card;
	
	/** 
	 * Builds a <tt>JCOPCAD</tt> object with the specified parameters.
	 * @name params the parameters to be passed to the newly created CAD.
	 */
	public JCOPCAD(String params) throws IOException {
	  super(params);
	  JCTerminal cad = new RemoteJCTerminal().init(params);
	  cad.open();
	  card = new JCard(cad,null,60000);
	}

	/**
	 * @see CAD#connect()
	 */
	public ATR connect() throws CADException {
	  card.getTerminal().open();
	  card.reset();
	  return new ATR(card.getATR().getBytes());
	}

	/**
	 * @see CAD#disconnect()
	 */
	public void disconnect() throws CADException {
	  card.getTerminal().close();
	}

	/**
	 * @see CAD#send(CommandAPDU)
	 */
	public ResponseAPDU send(CommandAPDU capdu) {
	  byte[] command = capdu.getBytes();
	  byte[] rapdu = card.getTerminal().send(0,command,0,command.length);
	  return new ResponseAPDU(rapdu);
	}
}

