package com.csx.enumMap;

import com.csx.domain.anno.Phone;
import com.csx.domain.anno.PhoneType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

/**
 * @author csx
 * @Package com.csx.enumMap
 * @Description: TODO
 * @date 2019/1/19 0019
 */
public class EnumMapTest {
    private static SessionFactory sf;
    static  {
        // 创建sf对象
        sf = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    @Test
    public void test1() {
        Session session = sf.openSession();
        Phone phone = new Phone(1L,"1314882222", PhoneType.LAND_LINE);
        session.save(phone);
    }
}
