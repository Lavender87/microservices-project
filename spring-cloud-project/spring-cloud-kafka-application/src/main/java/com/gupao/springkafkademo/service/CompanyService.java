package com.gupao.springkafkademo.service;

import com.gupao.springkafkademo.entity.Company;
import com.gupao.springkafkademo.entity.Employee;
import com.gupao.springkafkademo.mapper.EmployeeMapper;
import com.gupao.springkafkademo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> GetAllCompanyData(){
        return companyRepository.GetAllCompanyData();
    }


    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> findALL(){
        return employeeMapper.findALL();
    }

    public int insertEmployee(Employee employee){
        return employeeMapper.insertEmployee(employee);
    }

    public int updateEmployee(Employee employee){
        return employeeMapper.updateEmployee(employee);
    }

    public Employee queryById(int id){
        return employeeMapper.queryById(id);
    }

    public int getSize(){
        return employeeMapper.getSize();
    }

}
