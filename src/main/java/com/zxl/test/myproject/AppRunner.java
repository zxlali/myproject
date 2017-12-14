package com.zxl.test.myproject;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zhangxianli
 *
 */
@SpringBootApplication
@ImportResource("spring/appContext.xml")
@MapperScan("com.zxl.test.myproject.dao.mapper")
public class AppRunner {
  private static org.slf4j.Logger log = LoggerFactory.getLogger(AppRunner.class);

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(AppRunner.class, args);
    log.info("MyProject 启动成功!");
  }
}
