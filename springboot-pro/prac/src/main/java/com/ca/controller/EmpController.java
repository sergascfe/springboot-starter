package com.ca.controller;

import com.ca.anno.Log;
import com.ca.pojo.Emp;
import com.ca.pojo.PageBean;
import com.ca.pojo.Result;
import com.ca.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    public EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate end) {
        log.info("分页查询，参数:{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        //调用service分页查询
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }


    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除操作,ids{}", ids);
        empService.delete(ids);
        return Result.success();
    }


    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工,emp:{}", emp);
        empService.save(emp);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询员工信息，id:{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }



    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }

}