package org.csu.service.impl;

import org.csu.domain.Sequence;
import org.csu.dao.SequenceDao;
import org.csu.service.ISequenceService;
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
public class SequenceServiceImpl extends ServiceImpl<SequenceDao, Sequence> implements ISequenceService {

}
