package profile;

public class Administrator extends Profile{
	
	public Administrator(String ID, String name, String password) {
		super(ID, name, password);
	}
	
//	public void createAnnouncement(String announcement, QuizWebsite site) {
//		site.addAnnouncement(announcement);
//	}
	
	@Override
	public boolean isAdmin() {
		return true;
	}
}
