package dao.impl;

import dao.DbConnection;
import dao.memberDao;
import vo.member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class memberDaoImpl implements memberDao {

	@Override
	public void add(member m) {
		// TODO Auto-generated method stub
		Connection conn = DbConnection.getdb();
		String SQL = "Insert into member(memberNo,username,password,name,address,phone,mobile values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m.getMemberNo());
			ps.setString(2, m.getUsername());
			ps.setString(3, m.getPassword());
			ps.setString(4, m.getName());
			ps.setString(5, m.getAddress());
			ps.setString(6, m.getPhone());
			ps.setString(7, m.getMobile());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add(String memberNo, String username, String password, String name, String address, String phone,
			String mobile) {
		// TODO Auto-generated method stub

	}

	@Override
	public member queryUser(String username, String password) {
		// TODO Auto-generated method stub
		Connection conn = DbConnection.getdb();
		String SQL = "select * from member where username=? password=?";
		member m = null;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				m = new member();
				m.setId(rs.getInt("id"));
				m.setMemberNo(rs.getString("memberNo"));
				m.setUsername(rs.getString("username"));
				m.setPassword(rs.getString("password"));
				m.setName(rs.getString("name"));
				m.setAddress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return m;
	}

	@Override
	public boolean queryUser(String username) {
		// TODO Auto-generated method stub
		boolean x = false;
		Connection conn = DbConnection.getdb();
		String SQL = "select * from member where username=?";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				x=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
	}

}
