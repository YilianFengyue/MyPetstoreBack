package org.csu.service.impl;

import org.csu.domain.History;
import org.csu.dao.HistoryDao;
import org.csu.service.IHistoryService;
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
public class HistoryServiceImpl extends ServiceImpl<HistoryDao, History> implements IHistoryService {

}
