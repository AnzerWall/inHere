package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
	/**
	 * 文字内容是否超出长度 (比较的是字符)
	 * 
	 * @param str
	 * @param leng
	 * @return
	 */
	public static boolean isLengOut(String str, int leng) {
		return stringIsNull(str) ? false : str.trim().length() > leng;
	}

	/**
	 * 文字内容是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean stringIsNull(String str) {
		return str == null ? true : str.trim().length() <= 0;
	}

	/**
	 * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDoubleNegative(String str) {
		return Regular(str, "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$");
	}

	/**
	 * 判断字段是否为正整数正则表达式 >=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIntegerNegative(String str) {
		return Regular(str, "^[1-9]\\d*|0$");
	}

	/**
	 * 判断字符串是否为合法手机号 11位 13 14 15 17 18开头
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isMobile(String str) {
		return Regular(str, "^(13|14|15|17|18)\\d{9}$");
	}

	/**
	 * 判断日期是否符合指定格式
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static boolean isValidDate(String str, String pattern) {
		boolean flag = true;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			sdf.setLenient(false);
			sdf.parse(str);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static boolean isEmail(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return match(regex, str);
	}

	public static boolean isIP(String str) {
		String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
		return match(regex, str);
	}

	public static boolean isUrl(String str) {
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return match(regex, str);
	}

	public static boolean isTelephone(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		String result;
		String regex = "^[0-9]+?[0-9-()]*[0-9()]+?$|^[0-9]+?$|^$";
		String regex1 = "\\-{2,}";
		String regex2 = "\\({2,}";
		String regex3 = "\\){2,}";
		result = str.replaceAll(regex1, " ");
		result = result.replaceAll(regex2, " ");
		result = result.replaceAll(regex3, " ");
		// String regex = "^\\d{1,18}$";
		return match(regex, str) && str.length() == result.length();
	}

	public static boolean isPassword(String str) {
		String regex = "[A-Za-z]+[0-9]";
		return match(regex, str);
	}

	public static boolean isPasswLength(String str) {
		String regex = "^.{6,20}$";
		return match(regex, str);
	}

	public static boolean isPostalcode(String str) {
		String regex = "^\\d{6}$";
		return match(regex, str);
	}

	public static boolean isHandset(String str) {
		String regex = "^[0,1]+[3,5]+\\d{9}$";
		// String regex = "^\\d{1,18}$";
		return match(regex, str);
	}

	public static boolean isIDcard(String str) {
		String regex = "(^\\d{18}$)|(^\\d{15}$)";
		return match(regex, str);
	}

	public static boolean isDecimal(String str) {
		String regex = "^[\\-\\+]?[0123456789]+(.[0123456789]*)?$";
		if (match(regex, str)) {
			try {
				double a = Double.parseDouble(str);
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	public static boolean isSignlessDecimal(String str) {
		String regex = "^[\\+]?[0-9]+(.[0-9]*)?$";
		return match(regex, str);
	}

	public static boolean isDecimalLength(String str, int intlen, int digitallen) {
		str = str.replace("[\\-\\+]", "");
		String[] strs = str.split("\\.");
		if (strs.length > 1) {
			if (digitallen < 0) {
				return !(strs[0].length() > intlen);
			} else {
				return !(strs[0].length() > intlen || strs[1].length() > digitallen);
			}
		} else {
			return !(strs[0].length() > intlen);
		}
	}

	public static boolean isMonth(String str) {
		String regex = "^(0?[[1-9]|1[0-2])$";
		return match(regex, str);
	}

	public static boolean isBeforeToday(String str) {
		Date nowdate = new Date();
		Date d;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			d = sdf.parse(str);
			cal.setTime(d);
			cal.add(Calendar.DATE, 1);
			if (nowdate.before(cal.getTime())) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isDay(String str) {
		String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
		return match(regex, str);
	}

	public static boolean isDate(String str) {
		String regex = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
		return match(regex, str);
	}

	public static boolean isNumber(String str) {
		String regex = "^[0-9]*$";
		return match(regex, str);
	}

	public static boolean isIntNumber(String str) {
		if ("0".equals(str))
			return true;
		String regex = "^[\\-\\+]?[1-9][0-9]*$";
		return match(regex, str);
	}

	public static boolean isUpChar(String str) {
		String regex = "^[A-Z]+$";
		return match(regex, str);
	}

	public static boolean isLowChar(String str) {
		String regex = "^[a-z]+$";
		return match(regex, str);
	}

	public static boolean isLetter(String str) {
		String regex = "^[A-Za-z]+$";
		return match(regex, str);
	}

	public static boolean isChinese(String str) {
		String regex = "^[\u4e00-\u9fa5],{0,}$";
		return match(regex, str);
	}

	public static boolean isLength(String str) {
		String regex = "^.{8,}$";
		return match(regex, str);
	}

	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.lookingAt();
	}

	public static boolean checkHtmlTag(String str) {
		String regex = "^[a-zA-Z0-9],{0,}$";
		return match(regex, str);
	}

	public static boolean hasCrossScriptRisk(String qString, String regx) {
		if (qString != null) {
			qString = qString.trim();
			Pattern p = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(qString);
			return m.find();
		}
		return false;
	}

	public static boolean checkString(String qString) {
		String regx = "!|！|@|◎|#|＃|(\\$)|￥|%|％|(\\^)|……|(\\&)|※|(\\*)|×|(\\()|（|(\\))|）|_|——|(\\+)|＋|(\\|)|§ ";
		return hasCrossScriptRisk(qString, regx);
	}

	public static boolean isBlank(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isSelected(String[] select) {
		if (select == null || select.length == 0) {
			return false;
		}
		return true;
	}

	public static boolean isLength(String str, int minlen, int maxlen) {
		int len = 0;
		if (str != null) {
			len = str.length();
		}
		if (len >= minlen && len <= maxlen) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isRepeat(String[] strs) {
		ArrayList search = new ArrayList();
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			if ("".equals(str)) {
				continue;
			}
			int find = Arrays.binarySearch(search.toArray(), str);
			if (find >= 0) {
				return true;
			}
			search.add(str);
		}
		return false;
	}

	public static boolean isEmpty(String[] strs) {
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] == null || "".equals(strs[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasFullText(String str) {
		if (str == null)
			return false;
		String hanzen = str.replaceAll("[\\x00-\\xff]", "**");
		return hanzen.length() == str.length() ? true : false;
	}

	/**
	 * 匹配是否符合正则表达式pattern 匹配返回true
	 * 
	 * @param str
	 *            匹配的字符串
	 * @param pattern
	 *            匹配模式
	 * @return boolean
	 */
	private static boolean Regular(String str, String pattern) {
		if (null == str || str.trim().length() <= 0)
			return false;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}
}
