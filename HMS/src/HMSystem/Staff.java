package HMSystem;

public class Staff {
	private String staff_id;
	private String name;
	private String role;
	private String contact;
	public Staff(String name, String role, String contact) {
		super();
		this.name = name;
		this.role = role;
		this.contact = contact;
	}
	public Staff(String staff_id, String name, String role, String contact) {
		super();
		this.staff_id = staff_id;
		this.name = name;
		this.role = role;
		this.contact = contact;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
