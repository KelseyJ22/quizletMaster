package profile;

import java.io.Serializable;
import java.util.*;

/** 
 * Profile Class. Contains all information about a user. 
 *
 * @author KuanP
 *
 */
public class Profile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2252425260054810253L;
	private String ID;
	private String name;
	private String password;
	//private list<Performance> history;
	private List<Profile> friends;
	private List<Mail> mailbox;
	
	/**
	 * Constructor. MUST create w/ unique ID. ID should NEVER change. 
	 * On the other hand, name and password can be eventually to be set and changed. 
	 * @param name
	 * @param password
	 */
	public Profile(String ID, String name, String password) {
		this.ID = ID; // UNIQUE!!!
		this.name = name;
		this.password = password;
		//history = new ArrayList<Performance>();
		friends = new ArrayList<Profile>();
		mailbox = new ArrayList<Mail>();
	}
	
	public String getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isGoodPassword(String pw) {
		return pw.equals(password);
	}
	
	/** 
	 * Self-explanatory...checks if this user has the same friend. 
	 * @param friend
	 * @return
	 */
	public boolean hasFriend(Profile friend) {
		return friends.contains(friend);
	}
	
	/**
	 * Adds a friend to the list of friend. This friend must not be in the friends list
	 * already, nor can this friend be the user itself. 
	 * @param friend
	 * @return
	 */
	public boolean addFriend(Profile friend) {
		if (!hasFriend(friend)) {
			if(!this.equals(friend)) {
				friends.add(friend);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * To be overriden by the admin class. 
	 * @return
	 */
	public boolean isAdmin() {
		return false;
	}
	
	/**
	 * Adds mail to the profile. 
	 * @param mail
	 */
	public void addMail(Mail mail) {
		mailbox.add(mail);
	}
	
	/**
	 * Checks if the IDs of the profiles are the same. 
	 * @param profile
	 * @return
	 */
	public boolean equals(Profile profile) {
		return (this.ID.equals(profile.ID));
	}
}

