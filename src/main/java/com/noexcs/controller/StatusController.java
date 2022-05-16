package com.noexcs.controller;

import com.noexcs.entity.Result;
import com.noexcs.entity.Status;
import com.noexcs.service.StatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author noexcs
 */
@RestController
@RequestMapping("status")
public class StatusController {
    /**
     * 服务对象
     */
    @Resource
    private StatusService statusService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 分页查询
     *
     * @param status 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Status>> queryByPage(Status status, Integer page,Integer size) {
        PageRequest pageRequest=  PageRequest.of(page-1,size);
        return ResponseEntity.ok(this.statusService.queryByPage(status, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Status> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.statusService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param status 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Status> add(@RequestBody Status status) {
        return ResponseEntity.ok(this.statusService.insert(status));
    }
    /**
     * 新增数据
     *
     * @param status 实体
     * @return 新增结果
     */
    @PostMapping("addMessage")
    public Result addMessage(@RequestBody Status status, HttpServletRequest request) throws JsonProcessingException {
        Result result= new Result();
        ObjectMapper objectMapper = new ObjectMapper();
        //设置远程服务器的ip地址
        String remoteAddr = request.getRemoteAddr();
        status.setServerIp(remoteAddr);
        kafkaTemplate.send("server-monitor", objectMapper.writeValueAsString(status));
        result.setCode(0);
        result.setMsg("success");
        result.setData(status);
        return result;
    }

    /**
     * 编辑数据
     *
     * @param status 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Status> edit(Status status) {
        return ResponseEntity.ok(this.statusService.update(status));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.statusService.deleteById(id));
    }

    /**
     * cpu负载情况
     */
    @GetMapping("queryStatus")
    public  Result queryStatus(Status status){
        Result result = new Result();
        List<Status> statusList = statusService.queryStatus(status);
        /*for (Status status1 : statusList) {
            System.out.println(status1.toString());
        }*/
        result.setCode(0);
        List<Status> collect = statusList.stream().sorted(Comparator.comparing(Status::getId)).collect(Collectors.toList());
      /*  System.out.println("=================");
        for (Status status1 : collect) {
            System.out.println(status1.toString());
        }*/
        result.setData(collect);
        return result;
    }

}

