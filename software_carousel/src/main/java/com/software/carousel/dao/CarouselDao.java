package com.software.carousel.dao;

import com.software.carousel.pojo.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarouselDao extends JpaRepository<Carousel,String> {

    List<Carousel> findBySoftwareId(String softwareId);

}
