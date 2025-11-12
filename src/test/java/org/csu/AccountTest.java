package org.csu;


import org.csu.dao.*;


import org.csu.domain.Account;
import org.csu.domain.Signon;
import org.csu.service.IAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class AccountTest {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private IAccountService accountService;
    @Autowired
    private SignonDao signonDao;

    @Autowired
    private ProductDao productDao;

    @Test
    void TestUpDate(){
        Account account = new Account();
        account.setUserid("ylfmoonn");
        account.setCity("Luoyang");
        boolean flag=accountService.updateAccount(account);
    }
    @Test
    void TestDelete(){
        Account account = new Account();
        account.setUserid("Lappand");
        boolean flag=accountService.deleteAccount("Lappand");
    }
    @Test
    void TestGet(){
        Signon signon = new Signon();
        signon.setUsername("Lappand");
        Signon flag=signonDao.selectById("2");
        System.out.println(flag);
    }

}
