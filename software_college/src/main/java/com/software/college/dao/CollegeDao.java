package com.software.college.dao;

import com.software.college.pojo.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeDao extends JpaRepository<College,String> {
}
