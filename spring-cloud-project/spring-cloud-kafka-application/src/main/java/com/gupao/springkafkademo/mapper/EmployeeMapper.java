package com.gupao.springkafkademo.mapper;

import com.gupao.springkafkademo.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
public interface EmployeeMapper{

    public List<Employee> findALL();

    public int getSize();

    public int insertEmployee(Employee employee);

    public int updateEmployee(Employee employee);

    public Employee queryById(int id);
}
