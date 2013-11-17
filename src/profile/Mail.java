package profile;

import java.io.Serializable;

public abstract class Mail implements Serializable { // abstract needed?
	/**
	 * 
	 */
	private static final long serialVersionUID = -7235166688870963419L;
	public String msg;
	
	/**
	 * Creates a mail from a given msg.
	 * @param msg
	 */
	public Mail(String msg) {
		this.msg = msg;
	}
	
	public String display() {
		return msg;
	}
}
