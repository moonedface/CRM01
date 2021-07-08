import com.example.crm.settings.dao.UserDao;
import com.example.crm.settings.domain.User;
import com.example.crm.utils.SqlSessionUtil;
import org.junit.Test;

public class MybatisTest {
    @Test
    public void testMybatis(){
        //创建userDao
        UserDao dao= SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
        User u1=dao.login("zs","202cb962ac59075b964b07152d234b70");
        System.out.println(u1.toString());
    }

}
