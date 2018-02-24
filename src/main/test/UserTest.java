import com.csx.domain.CompositeKeys;
import com.csx.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */
public class UserTest {
//    @Test
//    public void testUser() {
//        User user = new User("csx1", "1234");
//        // 1.加载配置文件
//        Configuration configure = new Configuration().configure();
//        // 2.获得session factory对象
//        SessionFactory sessionFactory = configure.buildSessionFactory();
//        // 3.创建session
//        Session session = sessionFactory.openSession();
//        // 4.开启事务
//        Transaction transaction = session.beginTransaction();
//        // 5.保存并提交事务
//        session.save(user);
//        transaction.commit();
//        // 6.释放资源
//        session.close();
//        sessionFactory.close();
//    }

    private static SessionFactory sf;
    static  {
        // 创建sf对象
        sf = new Configuration()
                .configure()
                .addClass(User.class)  //（测试） 会自动加载映射文件：Employee.hbm.xml
                .buildSessionFactory();
    }


    /**
     * 测试复合主键
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        // 对象
        CompositeKeys keys = new CompositeKeys();
        keys.setAddress("广州棠东");
        keys.setUserName("Jack");
        User user = new User();
        user.setAge(20);
        user.setKeys(keys);

        // 保存
        session.save(user);


        tx.commit();
        session.close();
    }

    @Test
    public void testGet() throws Exception {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //构建主键再查询
        CompositeKeys keys = new CompositeKeys();
        keys.setAddress("广州棠东");
        keys.setUserName("Jack");

        // 主键查询
        User user = (User) session.get(User.class, keys);
        // 测试输出
        if (user != null){
            System.out.println(user.getKeys().getUserName());
            System.out.println(user.getKeys().getAddress());
            System.out.println(user.getAge());
        }


        tx.commit();
        session.close();
    }
}
