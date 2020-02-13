package Factory;

import DAO.ConnectionMaker;
import DAO.DConnectionMaker;
import DAO.UserDao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시

public class DaoFactory {

    /*
    public UserDao userDao(){
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        return userDao;
    }
    아래 메소드로 변형, 경우에 따라 중복 제거하기 위해
     */

    @Bean//오브젝트 생성을 담당하는 IoC용 메소드라는 표시
    public UserDao userDao(){
//        return new UserDao(new DConnectionMaker());
        return new UserDao(connectionMaker());
    }

    /*
    public AccountDao accountDao(){
        return new AccountDao(new DConnectionMaker());
        을
        return new AccountDao(connectionMaker()); 로 변경
    }

    public MessageDao messageDao(){
        return new MessageDao(new DConnectionMaker());
        을
        return new MessageDao(connectionMaker()); 로 변경
    }
     */

    @Bean
    //new DConnectionMaker() <<< 중복을 막기 위해 생성된 메서드
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }

}
/*
 자바 코드의 탈을 쓰고 있지만 사실은 XML과 같은 스프링 전용 설정정보라고 보는 것이 좋다.
 */
