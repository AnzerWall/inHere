package util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class ThumbnailsTest {

    public static void main(String[] args) throws IOException {
        //rotate(角度),正数：顺时针负数：逆时针
        Thumbnails.of("D:"+ File.separator+ "img" + File.separator + "1-max.jpg")
                .size(30, 30)
                .keepAspectRatio(false)
                .toFile("D:"+ File.separator+ "img" + File.separator + "1-min.jpg");
    }


}
