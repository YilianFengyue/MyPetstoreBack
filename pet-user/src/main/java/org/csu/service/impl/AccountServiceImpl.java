package org.csu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.dao.AccountDao;
import org.csu.dao.SignonDao;
import org.csu.domain.Account;
import org.csu.domain.Signon;
import org.csu.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements IAccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private SignonDao signonDao;
    //1.查询所有
    @Override
    public List<Account> getAccounts() {
        List<Account> accountList = accountDao.selectList(null);

        return accountList;
    }
    //2.更新用户信息
    @Override
    public boolean updateAccount(Account account) {
        int flag=accountDao.updateById(account);
        return flag==1;
    }
    //3.删除用户
    @Override
    public boolean deleteAccount(String userid) {
        int flag=accountDao.deleteById(userid);
        int flag2=signonDao.deleteById(userid);
        return flag==1 &&flag2==1;
    }
    //4.重置密码（默认密码1234）
    @Override
    public boolean resetPassword(Signon signon) {
        int flag=signonDao.updateById(signon);
        return flag==1;
    }
}
