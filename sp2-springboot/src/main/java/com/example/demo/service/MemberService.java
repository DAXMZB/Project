package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Member;

public interface MemberService {
	// 定義註冊
	String rigister(Member member);

	// 定義登入
	Optional<Member> login(String username, String password);

	// 定義檢查帳號
	boolean isUsernameExists(String username);
}
