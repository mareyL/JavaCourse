package opinion;

public class Member {
	private  String login;
	private String password;
	private String description;
	
	public Member(String login, String password, String description) {
		this.login = login;
		this.password = password;
		this.description = description;
	}
	
	public boolean hasLogin(String login) {
		if (login == null) {
			return (this.login == null);
		}
		return (this.login.trim().equalsIgnoreCase(login.trim()));
	}
	
	public boolean checkPassword(String password) {
		return this.password == password;
	}
	public boolean equals(Object o) {
		if (o.getClass() == Member.class && ((Member)o).hasLogin(this.login)){
			return true;
		}
		return false;
	}
	public String toString() {
		return "login: " + this.login + " password: " + this.password + " description: " + this.description;
	}
	
		
	
}
