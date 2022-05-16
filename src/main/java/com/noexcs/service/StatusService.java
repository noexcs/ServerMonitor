package com.noexcs.service;

import com.noexcs.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


/**
 * @author noexcs
 */
public interface StatusService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Status queryById(Integer id);

    /**
     * 分页查询
     *
     * @param status 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Status> queryByPage(Status status, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param status 实例对象
     * @return 实例对象
     */
    Status insert(Status status);

    /**
     * 修改数据
     *
     * @param status 实例对象
     * @return 实例对象
     */
    Status update(Status status);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Status> queryStatus(Status status);

}
