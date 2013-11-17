package profile;

public abstract class Mail { // abstract needed?
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
