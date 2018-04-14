package com.eko.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.eko.dynamicdb.DynamicDataSource;
import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
public class DatasourceConfig {
	
	// one Data source config start
	@Value("${spring.datasource.one.url}")
	private String onejdbcUrl;
	@Value("${spring.datasource.one.driver-class-name}")
	private String onejdbcDriverClassName;
	@Value("${spring.datasource.one.username}")
	private String onejdbcUsername;
	@Value("${spring.datasource.one.password}")
	private String onejdbcPassword;
	//one Data source config end
	 
	//two Data source config start
	@Value("${spring.datasource.two.url}")
	private String twojdbcUrl;
	@Value("${spring.datasource.two.driver-class-name}")
	private String twojdbcDriverClassName;
	@Value("${spring.datasource.two.username}")
	private String twojdbcUsername;
	@Value("${spring.datasource.two.password}")
	private String twojdbcPassword;
	//two Data source config end
	
	//dynamic data source config can only be with function name start
	@Bean(destroyMethod = "close")
	public BoneCPDataSource onedbDatasource() {
	    BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
	    boneCPDataSource.setDriverClass(onejdbcDriverClassName);
	    boneCPDataSource.setJdbcUrl(onejdbcUrl);
	    boneCPDataSource.setUsername(onejdbcUsername);
	    boneCPDataSource.setPassword(onejdbcPassword);
	    boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
	    boneCPDataSource.setIdleMaxAgeInMinutes(30);
	    boneCPDataSource.setMaxConnectionsPerPartition(100);
	    boneCPDataSource.setMinConnectionsPerPartition(5);
	    return boneCPDataSource;
	}
	@Bean(destroyMethod = "close")
	public BoneCPDataSource twodbDatasource() {
	    BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
	    boneCPDataSource.setDriverClass(twojdbcDriverClassName);
	    boneCPDataSource.setJdbcUrl(twojdbcUrl);
	    boneCPDataSource.setUsername(twojdbcUsername);
	    boneCPDataSource.setPassword(twojdbcPassword);
	    boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
	    boneCPDataSource.setIdleMaxAgeInMinutes(30);
	    boneCPDataSource.setMaxConnectionsPerPartition(100);
	    boneCPDataSource.setMinConnectionsPerPartition(5);
	    return boneCPDataSource;
	}
	//dynamic data source config can only be with function name end
	@Bean(name="multipleDatasource")
	public DataSource multipleDatasource(){
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		dynamicDataSource.setDefaultTargetDataSource(onedbDatasource());
		Map<Object,Object> dsmap = new HashMap<Object,Object>();
		dsmap.put("onedbDatasource", onedbDatasource());
		dsmap.put("twodbDatasource", twodbDatasource());
		dynamicDataSource.setTargetDataSources(dsmap);
		return dynamicDataSource;
	}
	
    @Bean
    @ConditionalOnMissingBean 
    public SqlSessionFactory sqlSessionFactory() {
    	 try {
	    	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	        sqlSessionFactoryBean.setDataSource(multipleDatasource());
	        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        Resource mybatisConfigXml = resolver.getResource("classpath:mybatis/mybatis-config.xml");
	        sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);
	        sqlSessionFactoryBean.setTypeAliasesPackage("com.eko.mapper.domain");
			return sqlSessionFactoryBean.getObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
