package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class Regex {

    public static void main(String[] args){
        Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");
        String string = "2010-12-20 2011-02-14";
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()) {
            System.out.println(matcher.group(0));
        }
    }
}
