package com.zxl.test.myproject;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = AppRunner.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BaseWebTest{

}
