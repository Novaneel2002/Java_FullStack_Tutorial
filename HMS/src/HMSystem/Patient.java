package HMSystem;

public class Patient {
	private int id;
	private String fname;
	private String lname;
	private int age;
	private String gender;
	private String contactNumber;
	public Patient(String fname, String lname, int age, String gender, String contactNumber) {
		super();
//		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.gender = gender;
		this.contactNumber = contactNumber;
	}
	
	public Patient(int id, String fname, String lname, int age, String gender, String contactNumber) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.gender = gender;
		this.contactNumber = contactNumber;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
}
