package com.noexcs.service.impl;

import com.noexcs.entity.Status;
import com.noexcs.dao.StatusDao;
import com.noexcs.service.StatusService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author noexcs
 */
@Service("statusService")
public class StatusServiceImpl implements StatusService {
    @Resource
    private StatusDao statusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Status queryById(Integer id) {
        return this.statusDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param status 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Status> queryByPage(Status status, PageRequest pageRequest) {
        long total = this.statusDao.count(status);
        return new PageImpl<>(this.statusDao.queryAllByLimit(status, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param status 实例对象
     * @return 实例对象
     */
    @Override
    public Status insert(Status status) {
        this.statusDao.insert(status);
        return status;
    }

    /**
     * 修改数据
     *
     * @param status 实例对象
     * @return 实例对象
     */
    @Override
    public Status update(Status status) {
        this.statusDao.update(status);
        return this.queryById(status.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.statusDao.deleteById(id) > 0;
    }

    @Override
    public List<Status> queryStatus(Status status) {
        return statusDao.queryStatus(status);
    }
}
