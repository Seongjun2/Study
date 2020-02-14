package DAO;

import java.sql.Connection;
import java.sql.SQLException;


public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
/*
 인터페이스로 만드는 이유 : 여러 상황마다 연결하는 방법이 다를 수 있기 때문
 ex) DConnectionMaker 를 만드는 것 처럼.
 */


