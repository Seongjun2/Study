package FactoryTest;

import DAO.UserDao;
import Factory.DaoFactory;

public class UserDaoTest {
    public static void main(String[] args) {
        UserDao dao = new DaoFactory().userDao();
        //Client 는 DaoFactory 에 요청 -> DaoFactory 는 UserDao 를 생성, DConnectionMaker 를 생성-> UserDao 는 ConnectionMaker 를 사용
    }
}

/*


 */
