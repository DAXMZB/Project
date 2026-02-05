package dao;

import vo.member;

public interface memberDao {
	//create
	void add(member m);
	void add(String memberNo, String username, String password, String name, String address, String phone,
			String mobile);
	
	//read
	member queryUser(String username,String password);//登入用
	boolean queryUser(String username);//帳號是否重複
	
	//update
	
	//delete
}
