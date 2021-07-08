import com.example.crm.utils.DateTimeUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test01 {
    @Test
    public void testExpireTime(){
        //验证失效时间
        String expireTime="2019-10-10  10:10:10";
//        Date date=new Date();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String str=sdf.format(date);
//        System.out.println(str);
        //当前系统时间
        String currentTime= DateTimeUtil.getSysTime();
        int count=expireTime.compareTo(currentTime);
        //count<0说明过期
        System.out.println(count);
        //验证账号锁定，判断 lockState
        //判断ip地址
        //浏览器端的IP地址
        String ip="127.0.0.1";
        //允许访问的地址
        String allowIps="192.168.1.1,127.0.0.1";
        //判断是否是包含关系
        if(allowIps.contains(ip)){
            System.out.println("有效的ip地址");
        }else{
            System.out.println("ip地址受限，请联系管理员");
        }

    }

}
