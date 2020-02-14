package FactoryTest;

import DAO.User;
import DAO.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.sql.SQLException;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class) // 스프링의 테스트 컨텍스트 프레임 워크의 Junit 확장 기능 지정
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
public class UserDaoTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserDao userDao;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /*
        UserDao dao = new DaoFactory().userDao();
        //Client 는 DaoFactory 에 요청 -> DaoFactory 는 UserDao 를 생성, DConnectionMaker 를 생성-> UserDao 는 ConnectionMaker 를 사용
       */

//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        //applicationContext.xml 에 등록 된 것을 사용할 것.

//        UserDao dao = context.getBean("userDao", UserDao.class);
        //@Configuration 이 붙은 자바코드를 설정정보로 사용하려면 AnnotationConfigApplicationContext 클래스를 이용
        //getBean()의 파라미터 userDao 는 메서드 이름

        JUnitCore.main("FactoryTest.UserDaoTest");


    }

    @Before
    public void setUp(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userDao = context.getBean("UserDad", UserDao.class);
    }
    @Test//Junit 을 사용하기 위해 어노테이션과 public 선언은 필수
    public void addAndGet() throws SQLException, ClassNotFoundException {

        User user = new User();
        user.setId("seongjun");
        user.setName("윤성준");
        user.setPassword("springno1");

        userDao.add(user);

        User user2 = userDao.get(user.getId());

        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getPassword(),is(user.getPassword()));

    }
}

/*
 애플리케이션 컨텍스트를 사용했을 때 얻을 수 있는 장점
 - 클라이언트는 구체적인 팩토리를 알 필요가 없음
 - 애플리케이션 컨텍스트는 종합 IoC 서비스를 제공해준다.
 - 애플리케이션 컨텍스트는 빈을 검색하는 다양한 방법을 제공

 UserDao dao1 = context.getBean("userDao","UserDao.class);
 UserDao dao2 = context.getBean("userDao","UserDao.class);
 dao1, dao2 는 같은 주소값을 가짐 -> 싱글톤

 */
