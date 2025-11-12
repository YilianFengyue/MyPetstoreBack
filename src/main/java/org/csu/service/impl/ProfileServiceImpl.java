package org.csu.service.impl;

import org.csu.domain.Profile;
import org.csu.dao.ProfileDao;
import org.csu.service.IProfileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ProfileServiceImpl extends ServiceImpl<ProfileDao, Profile> implements IProfileService {

}
