package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


//connection pool test (hikari o mybatis o)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
	
	@Setter(onMethod_ = { @Autowired })
	private DataSource dataSource;
	
	@Setter(onMethod_ = { @Autowired })
	private SqlSessionFactory sqlSessionFactory;
	@Test
	// 설정된 SqlSessionFactory 인터페이스 타입의 SqlSessionFactoryBean을 이용해서 생성하고
	public void testMyBatis() {
		try (
				SqlSession session = sqlSessionFactory.openSession();
				Connection conn = dataSource.getConnection();
				) {
			log.info(conn);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
/**
result of testMyBatis()
INFO : org.springframework.test.context.support.DefaultTestContextBootstrapper - Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener]
INFO : org.springframework.test.context.support.DefaultTestContextBootstrapper - Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@6eebc39e, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@464bee09, org.springframework.test.context.support.DependencyInjectionTestExecutionListener@f6c48ac, org.springframework.test.context.support.DirtiesContextTestExecutionListener@13deb50e, org.springframework.test.context.transaction.TransactionalTestExecutionListener@239963d8, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener@3abbfa04]
INFO : org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loading XML bean definitions from URL [file:src/main/webapp/WEB-INF/spring/root-context.xml]
INFO : org.springframework.context.support.GenericApplicationContext - Refreshing org.springframework.context.support.GenericApplicationContext@3b0143d3: startup date [Wed Apr 07 12:29:25 KST 2021]; root of context hierarchy
INFO : org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor - JSR-330 'javax.inject.Inject' annotation found and supported for autowiring
//해당 클래스 이름 비추천, 자동으로 클래스 com.mysql.cj.jdbc.Driver 변경
// Q1) 자동으로 클래스명 생성할 시 추후 문제 발생가능?
// A1) 상관x
Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
INFO : com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Starting...
WARN : com.zaxxer.hikari.util.DriverDataSource - Registered driver with driverClassName=com.mysql.jdbc.Driver was not found, trying direct instantiation.
INFO : com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Start completed.
INFO : org.zerock.persistence.DataSourceTests - HikariProxyConnection@1855261647 wrapping com.mysql.cj.jdbc.ConnectionImpl@16414e40
INFO : org.springframework.context.support.GenericApplicationContext - Closing org.springframework.context.support.GenericApplicationContext@3b0143d3: startup date [Wed Apr 07 12:29:25 KST 2021]; root of context hierarchy
INFO : com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Shutdown initiated...
INFO : com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Shutdown completed.

 */

/*
// connection pool test (hikari o mybatis x)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
	
	@Setter(onMethod_ = { @Autowired })
	private DataSource dataSource;
	@Test
	public void testConnection() {
		try (Connection conn = dataSource.getConnection()) {
			log.info(conn);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
*/