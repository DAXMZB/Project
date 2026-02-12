package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 讓Spring 自動掃描並處理Controller異常 <-- 這是「統一處理」的靈魂
public class GlobalExceptionHandler {
	@ExceptionHandler(MemberException.class) // <-- 捕捉所有 Service 拋出的 MemberException
	public ResponseEntity<String> handleMemberException(MemberException ex) {
		String msg = ex.getMessage();
		// 根據訊息判斷要給什麼狀態碼
		// 這裡就是「HTTP 狀態碼轉換」：根據訊息字串決定要給 409 還是 400
		if (ex.getMessage().contains("重複") || ex.getMessage().contains("存在")) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());// 409
		}
		if (ex.getMessage().contains("不存在")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);// 401
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);// 預設400
	}

	// 400 Bad Request 格式錯誤：資料本身不符合規則。
	// 401 UNAUTHORIZED 未經授權
	// 409 Conflict 資料衝突：格式對，但資料庫已存在。
	// 200 OK 請求成功
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		// 從異常物件中抓取在Member.java的錯誤訊息
		String errorMsg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return ResponseEntity.badRequest().body(errorMsg);
	}
}
