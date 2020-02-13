/*
팩토리 패턴
객체의 생성 방법을 결정하고 그렇게 만들어진 오브젝트를 돌려주는 역할
*/

Public class DaoFactory{

    public DConnectionMaker connectionMaker(){// 아래 메소드에 중복으로 많이 들어가기 때문에 따로 뺀 것
        return new DConnectionMaker();
    }

	public UserDao userDao(){
		UserDao userDao = new UserDao(connectionMaker());
		return userDao;
	}

	public AccountDao accountDao(){
	    return new AccountDao(connectionMaker());
	}

	public MessageDao messageDao(){
	    return new MessageDao(connectionMaker());
	}

}
