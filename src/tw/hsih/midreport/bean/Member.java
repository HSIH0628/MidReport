package tw.hsih.midreport.bean;

public class Member {
	private int ID;
	private String CompanyName;
	private String Address;
	private String Phone;

	public Member() {
		super();
	}

	public Member(String CompanyName, String Address, String Phone) {
		super();
		// this.ID = ID;
		this.CompanyName = CompanyName;
		this.Address = Address;
		this.Phone = Phone;

	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String CompanyName) {
		this.CompanyName = CompanyName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String Phone) {
		this.Phone = Phone;
	}

	@Override
	public String toString() {
		return "Member [id=" + ID + ", CompanyName=" + CompanyName + ", Address=" + Address + ", Phone=" + Phone + "]";
	}

}
