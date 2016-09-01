package com.inHere.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.inHere.entity.TbUser;
import com.inHere.service.SecurityService;
import com.inHere.util.MD5Util;

/**
 * 安全加密解密业务
 * 
 * @author lwh
 *
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	/**
	 * 获取加盐加密密码和盐值
	 * 
	 * @param passwd
	 * @return list.get(0) --> password <br>
	 *         list.get(1) --> saltKey <br>
	 */
	public List<String> getPasswordAndSalt(String password) {
		// 使用uuid做盐值
		String uid = UUID.randomUUID().toString();

		// 密码、盐值切割
		int pwdLen = password.length();
		String pwdPrefix = password.substring(0, pwdLen / 2);
		String pwdSuffix = password.substring(pwdLen / 2);
		int uidLen = password.length();
		String uidPrefix = uid.substring(0, uidLen / 3);
		String uidSuffix = uid.substring(uidLen / 3);

		// MD5加密
		byte[] pwdByte = MD5Util.encrypt(uidSuffix + pwdPrefix + uidPrefix + pwdSuffix);
		// 转Base64编码存储
		String safePwd = Base64.encodeBase64String(pwdByte);
		String saltKey = Base64.encodeBase64String(uid.getBytes());

		List<String> pwdAndSalt = new ArrayList<String>();
		pwdAndSalt.add(safePwd);
		pwdAndSalt.add(saltKey);
		return pwdAndSalt;
	}

	/**
	 * 密码验证
	 * 
	 * @param password
	 * @return
	 */
	public boolean verify(TbUser user, String password) {
		boolean flag = false;

		return flag;
	}

}
