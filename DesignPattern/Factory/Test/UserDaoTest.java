//Factory Test

public class UserDaoTest{
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        UserDao dao = new DaoFactory().userDao();
        ...
    }
}
