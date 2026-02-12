package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.exception.MemberException;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository repo;
	// 建立加密實例
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public String rigister(Member m) {
		// TODO Auto-generated method stub

		// 1.帳號重複驗證
		// 這裡就是「HTTP 狀態碼轉換」：根據訊息字串決定要給 409 還是 400
		if (repo.existsByUsername(m.getUsername())) {
			throw new MemberException("帳號已重複");//409
		}
		// 2.密碼加密
		String encodedPassword = passwordEncoder.encode(m.getPassword());
		m.setPassword(encodedPassword);// 將加密後的字串塞回物件
		repo.save(m);
		return "註冊成功";
	}

	@Override
	public Optional<Member> login(String username, String password) {
		// TODO Auto-generated method stub
		// 因為資料庫現在存的是「亂碼」，不能直接用 findByUsernameAndPassword。必須先根據帳號取出資料，再比對密碼。
		// 1.先用帳號找人
		Member member = repo.findByUsername(username).orElseThrow(() -> new MemberException("帳號不存在"));

		// 2.使用 matches 比對 (原始密碼，資料庫加密密碼)
		if (passwordEncoder.matches(password, member.getPassword())) {
			return Optional.of(member);
		} else {
			throw new MemberException("密碼錯誤");
		}

	}

	@Override
	public boolean isUsernameExists(String username) {
		// TODO Auto-generated method stub
		if (repo.existsByUsername(username)) {
			// 如果存在，直接拋出異常，訊息包含重複或存在
			// 這裡就是「HTTP 狀態碼轉換」：根據訊息字串決定要給 409 還是 400
			throw new MemberException("帳號已存在");//400
		}
		return false;
	}

}
