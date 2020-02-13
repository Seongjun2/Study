package Singleton;

import DAO.ConnectionMaker;
import DAO.DConnectionMaker;
import DAO.User;

import java.sql.Connection;
import java.sql.SQLException;

//인스턴스 변수를 사용하도록 수정한 UserDao
public class UserDao_modify {

    private ConnectionMaker connectionMaker;// 초기에 설정하면 사용중에는 바뀌지 않는 읽기 전용 인스턴스 변수
    private Connection c;
    private User user;
    // c, user : 매번 새로운 값으로 바뀌는 정보를 담은 인스턴스 변수. 심각한 문제 발생

    public  User get(String id) throws ClassNotFoundException, SQLException{
        this.c = connectionMaker.makeConnection();

        /*
         db 에서 읽어오는 코드
         */

        this.user = new User();
        /*
            읽어온 것으로 user setting 코드
            this.user.setId(rs.getString("id"));
            ....
         */

        return this.user;
    }

    /*
        읽기 전용의 속성을 가진 정보라면 싱글톤에서 인스턴스 변수로 사용해도 좋다.
        단, 단순한 읽기 전용이라면 static final 이나 final 로 선언하는 것이 나을 것.
     */

}
