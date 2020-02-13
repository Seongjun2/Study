package Singleton;

import DAO.ConnectionMaker;
import DAO.DConnectionMaker;

public class UserDao {

    private static UserDao INSTANCE;
    private ConnectionMaker connectionMaker;

    private UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }
    //생성자가 private 가 되어 테스트 클래스에서 ConnectionMaker 오브젝트를 넣어주지 못함.-> 싱글톤의 단점


    public static synchronized UserDao getInstance(){
        if(INSTANCE == null) INSTANCE = new UserDao(new DConnectionMaker());
        return INSTANCE;
    }

    /*
        싱글톤 패턴은 생성자를 private 로 제한한다. 오직 싱글톤 클래스 자신만이 자기 오브젝ㅌ르를 만들도록 제한 하는 것.
        private 생성자만 존재하면 상속이 불가능.

        스프링은 싱글톤의 여러 단점을 보완하기 위해 싱글톤 레지스트리를 제공한다.
        싱글톤 레지스트리 : 직접 싱글톤 형태의 오브젝트를 만들고 관리하는 기능을 제공.

     */

}
