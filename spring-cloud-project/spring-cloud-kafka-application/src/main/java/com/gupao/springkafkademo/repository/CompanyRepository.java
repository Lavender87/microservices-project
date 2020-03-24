package com.gupao.springkafkademo.repository;

import com.gupao.springkafkademo.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CompanyRepository
        extends PagingAndSortingRepository<Company, Integer>
{


    @Query(value = "select * from company",nativeQuery = true)
    List<Company> GetAllCompanyData();

    //根据id查询用户
    @Query(value = "SELECT * FROM company WHERE id=?",
            nativeQuery = true)
    Company findCompanyById(int id);

    //根据name查询用户
    @Query(value = "SELECT * FROM company WHERE name=?",
            nativeQuery = true)
    public List<Company> getCompanyByName(String name);

    //修改用户
    @Query(value = "UPDATE company  SET address=:#{#company.address} WHERE id=:#{#company.id}"
            , nativeQuery = true)
    @Modifying
    @Transactional
    int updateCompanyById(Company company);

    //删除用户
    @Modifying
    @Transactional
    @Query(value = "delete from company where id=?", nativeQuery =
            true)
    int deleteCompanyById(int id);

    //增加用户
    @Query(value = "insert into company(name,address)" +
            " values(:#{#company.name},:#{#company.address})",
            nativeQuery =
                    true)
    int addCompany(Company company);

    //分页查询
    @Query(value = "select id,name,address from " +
            "company limit  ?1  offset  ?2", nativeQuery = true)
    List<Company> getPage(int pageSize, int pageNumber);

    //获取记录总数
    @Query(value = "SELECT \"count\"(*) from company",
            nativeQuery = true)
    int getAccount();
}
