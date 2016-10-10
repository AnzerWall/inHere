package util;

import com.inHere.entity.User;
import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by Administrator on 2016/10/10.
 */
public class XStreamTest {

    Logger log = Logger.getLogger(getClass());

    @Test
    public void readXml2Object() {
        XStream xstream = new XStream();

        try {
            log.info("-----------Xml >>> Bean--------------");
            User user = new User();
            user.setSchoolId(1);
            user.setUserId("ni_menhao");

            log.info(xstream.toXML(user));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
