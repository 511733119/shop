package cn.it.shop.serviceImpl;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Account;
import cn.it.shop.model.Category;
import cn.it.shop.service.AccountService;
import cn.it.shop.service.CategoryService;

/*
 * 模块自身的业务逻辑
 */
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {
	
}
