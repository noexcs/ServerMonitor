package com.noexcs.dao;

import com.noexcs.entity.Status;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;


/**
 * @author noexcs
 */
public interface StatusDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Status queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param status 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Status> queryAllByLimit(@Param("status") Status status, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param status 查询条件
     * @return 总行数
     */
    long count(Status status);

    /**
     * 新增数据
     *
     * @param status 实例对象
     * @return 影响行数
     */
    int insert(Status status);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Status> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Status> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Status> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Status> entities);

    /**
     * 修改数据
     *
     * @param status 实例对象
     * @return 影响行数
     */
    int update(Status status);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Status> queryStatus(@Param("status") Status status);

}

