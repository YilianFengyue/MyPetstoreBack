package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.dao.AdminDao;
import org.csu.domain.Admin;
import org.csu.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IAdminServiceImpl implements IAdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public boolean validateUser(String username, String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        return adminDao.selectCount(queryWrapper) > 0;
    }
}
