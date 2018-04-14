package com.eko.config;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(DatasourceConfig.class)
public class MapperScannerConfig {
    
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
   
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.eko.mapper");
        return mapperScannerConfigurer;
    }


}