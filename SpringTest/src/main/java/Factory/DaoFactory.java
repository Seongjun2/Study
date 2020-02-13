package Factory;

import DAO.ConnectionMaker;
import DAO.DConnectionMaker;
import DAO.UserDao;

public class DaoFactory {

    public UserDao userDao(){
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        return userDao;
    }
}
