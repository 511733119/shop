package cn.it.shop.service;

import cn.it.shop.model.User;

public interface UserService extends BaseService<User> {

	public User login(User user);
}
