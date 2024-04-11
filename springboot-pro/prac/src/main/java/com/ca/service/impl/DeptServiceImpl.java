package com.ca.service.impl;

import com.ca.mapper.DeptMapper;
import com.ca.mapper.EmpMapper;
import com.ca.pojo.Dept;
import com.ca.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;


    @Override
    public List<Dept> list(){
        return deptMapper.list();
    }

    //@Transactional(rollbackFor = Exception.class)//spring事务管理
    @Transactional
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);//根据Id删除部门数据

        empMapper.deleteByDeptId(id);//根据id删除部门下的员工
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }
}
