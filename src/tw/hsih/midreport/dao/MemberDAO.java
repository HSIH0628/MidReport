package tw.hsih.midreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import tw.hsih.midreport.bean.Member;

public class MemberDAO {

	private Connection conn;

	public MemberDAO(Connection conn) {
		this.conn = conn;
	}
//找ID
	public Member findByID(int id) throws SQLException {
		final String sql = "SELECT * FROM member WHERE id = ?";

		PreparedStatement preState = conn.prepareStatement(sql);

		preState.setInt(1, id);

		ResultSet rs = preState.executeQuery();

		if (rs.next()) {
			Member member = new Member();

			member.setID(rs.getInt("id"));
			member.setCompanyName(rs.getString("CompanyName"));
			member.setAddress(rs.getString("Address"));
			member.setPhone(rs.getString("Phone"));
			return member;
		}

		rs.close();
		preState.close();

		return null;

	}
//全部物件
	public List<Member> findAll() throws SQLException {
		final String sql = "SELECT * FROM member";

		PreparedStatement preState = conn.prepareStatement(sql);
		ResultSet rs = preState.executeQuery();

		List<Member> memberList = new ArrayList<Member>();
		while (rs.next()) {
			Member member = new Member();

			member.setID(rs.getInt("ID"));
			member.setCompanyName(rs.getString("CompanyName"));
			member.setAddress(rs.getString("Address"));
			member.setPhone(rs.getString("Phone"));

			memberList.add(member);
		}

		rs.close();
		preState.close();

		return memberList;

	}

//增加會員	
	public void createMember(Member m) throws SQLException {
		final String sql = "INSERT INTO member(CompanyName, Address, Phone) VALUES(?, ?, ?)";

		try (PreparedStatement preState = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preState.setString(1, m.getCompanyName());
			preState.setString(2, m.getAddress());
			preState.setString(3, m.getPhone());

			preState.executeUpdate();

			// 取得自動生成的主鍵值
			ResultSet generatedKeys = preState.getGeneratedKeys();
			if (generatedKeys.next()) {
				// 設置正確的 id 值
				m.setID(generatedKeys.getInt(1));
			}
		}
	}
	// 更新會員資料
	public void updateMember(Member m) throws SQLException {
	    final String sql = "UPDATE member SET CompanyName = ?, Address = ?, Phone = ? WHERE id = ?";

	    try (PreparedStatement preState = conn.prepareStatement(sql)) {
	        preState.setString(1, m.getCompanyName());
	        preState.setString(2, m.getAddress());
	        preState.setString(3, m.getPhone());
	        preState.setInt(4, m.getID());

	        preState.executeUpdate();
	    }
	}

	// 刪除會員
	public void deleteMember(int id) throws SQLException {
		final String sql = "DELETE FROM member WHERE id = ?";

		try (PreparedStatement preState = conn.prepareStatement(sql)) {
			preState.setInt(1, id);

			preState.executeUpdate();
		}
	}

}
