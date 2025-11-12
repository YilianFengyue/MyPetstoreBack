package org.csu.service.impl;

import org.csu.domain.Inventory;
import org.csu.dao.InventoryDao;
import org.csu.service.IInventoryService;
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
public class InventoryServiceImpl extends ServiceImpl<InventoryDao, Inventory> implements IInventoryService {

}
