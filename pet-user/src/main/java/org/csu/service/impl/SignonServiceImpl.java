package org.csu.service.impl;

import org.csu.dao.AccountDao;
import org.csu.domain.Account;
import org.csu.domain.Signon;
import org.csu.dao.SignonDao;
import org.csu.service.ISignonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@Service
public class SignonServiceImpl extends ServiceImpl<SignonDao, Signon> implements ISignonService {

    @Autowired
    private SignonDao signonDao;
    @Autowired
    private AccountDao accountDao;
    @Override
    public void register(String username, String password) {
        Signon signon = new Signon();
        signon.setUsername(username);
        signon.setPassword(password); // 实际使用中建议加密处理
        Account account = new Account();
        account.setUserid(username);
        accountDao.insert(account);
        signonDao.insert(signon);
    }
}
