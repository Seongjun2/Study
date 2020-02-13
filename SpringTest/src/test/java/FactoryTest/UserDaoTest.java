package FactoryTest;

import DAO.UserDao;
import Factory.DaoFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /*
        UserDao dao = new DaoFactory().userDao();
        //Client 는 DaoFactory 에 요청 -> DaoFactory 는 UserDao 를 생성, DConnectionMaker 를 생성-> UserDao 는 ConnectionMaker 를 사용
       */

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        //@Configuration 이 붙은 자바코드를 설정정보로 사용하려면 AnnotationConfigApplicationContext 클래스를 이용
        //getBean()의 파라미터 userDao 는 메서드 이름


    }
}

/*


 */
