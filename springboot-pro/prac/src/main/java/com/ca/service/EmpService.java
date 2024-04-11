package com.ca.service;

import com.ca.pojo.Emp;
import com.ca.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    //分页查询
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);


    //批量删除
    void delete(List<Integer> ids);

    void save(Emp emp);

    //根据ID查询员工
    Emp getById(Integer id);

    void update(Emp emp);


    //员工登录
    Emp login(Emp emp);
}
