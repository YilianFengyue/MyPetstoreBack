package org.csu.service;

import org.csu.domain.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.domain.Signon;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */

public interface IAccountService extends IService<Account> {
    //1.查询所有
    public List<Account> getAccounts();

    //2.修改用户信息
    public boolean updateAccount(Account account);

    //3.密码删除
    public boolean deleteAccount(String account);

    //4.重置用户密码
    public boolean resetPassword(Signon signon);

}
