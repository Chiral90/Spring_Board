package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
//JDBC Test code
public class JDBCTests {
	// static try catch : 
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try(Connection conn =
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3307/db_ex?serverTimezone=Asia/Seoul",
						"root",
						"72004014"
						)) {
			log.info(conn);
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
}
// Q1) Main없이도 이 소스가 실행되는 이유? src/test/java 경로 때문? @Test때문?
// A1) spring 내부 구조 때문(spring의 JUnit이 서버역할을 하면서 실행)
// Q2) static try catch 기능, 의미?
// A2) java 책 1권 p303 클래스 초기화 블럭

/*
result : success
Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
INFO : org.zerock.persistence.JDBCTests.JDBCTests - com.mysql.cj.jdbc.ConnectionImpl@cd3fee8
*/
