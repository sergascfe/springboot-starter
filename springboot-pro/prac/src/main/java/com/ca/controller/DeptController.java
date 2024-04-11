package com.ca.controller;

import com.ca.anno.Log;
import com.ca.pojo.Dept;
import com.ca.pojo.Result;
import com.ca.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查询部门数据
    //private static Logger log= LoggerFactory.getLogger(DeptController.class);
    //@RequestMapping("/depts")
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");

        //调用service查询数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    //删除部门
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        //调用service删除部门
        deptService.delete(id);
        return Result.success();
    }

    //添加部门
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        //调用service新增部门
        deptService.add(dept);
        return Result.success();
    }
}
