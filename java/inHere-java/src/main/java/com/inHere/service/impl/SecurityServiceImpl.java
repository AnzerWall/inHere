package com.inHere.service.impl;

import com.inHere.service.SecurityService;
import com.inHere.util.SHA256Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 安全加密解密业务
 * 
 * @author lwh
 *
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	/**
	 * 加盐加密，返回加密密码和盐值
	 * 
	 * @param password
	 * @return 返回加盐加密密码、盐值list.get(0) : password | list.get(1) : saltKey
	 * 
	 */
	public List<String> encrypt(String password) {
		// 使用uuid做盐值
		String saltKey = UUID.randomUUID().toString();
		saltKey = saltKey.replace("-", "");

		// 密码加密
		String safePwd = encrypt(saltKey, password);

		List<String> pwdAndSalt = new ArrayList<String>();
		pwdAndSalt.add(safePwd);
		pwdAndSalt.add(saltKey);
		return pwdAndSalt;
	}

	/**
	 * 加盐加密, 返回加密后的密码
	 * 
	 * @param password
	 * @return 加密后的密码
	 */
	public String encrypt(String saltKey, String password) {
		// 密码、盐值切割
		int pwdLen = password.length();
		String pwdPrefix = password.substring(0, pwdLen / 2);
		String pwdSuffix = password.substring(pwdLen / 2);

		int uidLen = saltKey.length();
		String uidPrefix = saltKey.substring(0, uidLen / 3);
		String uidSuffix = saltKey.substring(uidLen / 3);

		// SHA-256加密
		String firstEncrypt = SHA256Util.encrypt(uidSuffix + pwdPrefix) + SHA256Util.encrypt(uidPrefix + pwdSuffix);
		String secondEncrypt = SHA256Util.encrypt(firstEncrypt);
		return secondEncrypt;
	}

	/**
	 * token创建
	 * 
	 * @param userId
	 * @return
	 */
	public String createToken(String userId) {
		String str = "niswlejo_34kl9sk?sds@lds";
		String uid = UUID.randomUUID().toString();
		Long time = new Date().getTime();
		String first = SHA256Util.encrypt(str + userId + uid + time);
		String second = SHA256Util.encrypt(first);
		return second;
	}

}
