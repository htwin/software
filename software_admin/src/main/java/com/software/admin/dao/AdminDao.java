package com.software.admin.dao;

import com.software.admin.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,String> {

    Admin findByUsername(String username);

}
