package vo;

public class member {
	private Integer id;
	private String memberNo;
	private String username;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String mobile;

	public member() {
		super();
	}

	public member(String memberNo, String username, String password, String name, String address, String phone,
			String mobile) {
		super();
		this.memberNo = memberNo;
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.mobile = mobile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
