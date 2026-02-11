package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.repository.MemberRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.entity.Member;

@RestController // RestFul API
@RequestMapping("/api/members")
public class MemberController {
	@Autowired
	private MemberRepository repo;

	@GetMapping("/check") // 檢查帳號是否存在
	public ResponseEntity<String> checkUsername(@RequestParam String username) {
		// 呼叫 repo 檢查帳號
		if (repo.existsByUsername(username)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("帳號已存在");
		}
		return ResponseEntity.ok("此帳號可以使用");
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Member m) {
		// 1.新增：檢查密碼長度 (假設規定至少8位)
		if (m.getPassword() == null || m.getPassword().length() < 8) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("註冊失敗，密碼長度最少為8位");
		}

		// 2.檢查帳號是否重複
		if (repo.existsByUsername(m.getUsername())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("帳號已重複");
		}
		repo.save(m);
		return ResponseEntity.ok("註冊成功");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member m) {
		// 先執行查詢並取得 Optional 物件
		var memberOpt = repo.findByUsernameAndPassword(m.getUsername(), m.getPassword());

		// 判斷是否存在該會員
		if (memberOpt.isPresent()) {
			// 成功：回傳會員物件
			return ResponseEntity.ok(memberOpt.get());
		} else {
			// 失敗：回傳 401 錯誤狀態
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
		}
	}

}
