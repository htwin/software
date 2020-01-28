package com.software.carousel.service;

import com.software.carousel.dao.CarouselDao;
import com.software.carousel.pojo.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselService {

    @Autowired
    private CarouselDao carouselDao;

    public List<Carousel> findBySoftwareId(String softwareId){
        return carouselDao.findBySoftwareId(softwareId);
    }

    public List<Carousel> list(){
        return carouselDao.findAll();
    }

}
