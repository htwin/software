package com.software.classify.dao;

import com.software.classify.pojo.Classify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassifyDao extends JpaRepository<Classify,String> {
}
