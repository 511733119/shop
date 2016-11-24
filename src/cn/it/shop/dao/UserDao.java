package cn.it.shop.dao;

import cn.it.shop.model.User;

public interface UserDao extends BaseDao<User> {

	public User login(User user);
}
