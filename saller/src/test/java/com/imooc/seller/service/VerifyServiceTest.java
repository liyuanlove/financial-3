package com.imooc.seller.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by songyouyu on 2018/6/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerifyServiceTest {

    @Autowired
    private VerifyService verifyService;

    @Test
    public void makeVerificationFile() {
        Date day = new GregorianCalendar(2017, 11,31).getTime();
        File file = verifyService.makeVerificationFile("111", day);
        System.out.println(file.getAbsolutePath());

    }

    @Test
    public void saveVerificationOrders(){
        Date day = new GregorianCalendar(2017,11,31).getTime();
        //verifyService.saveChanOrders("111",day);
    }
}