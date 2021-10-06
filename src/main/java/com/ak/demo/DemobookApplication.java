package com.ak.demo;

import java.sql.SQLException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class DemobookApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemobookApplication.class, args);
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Comment for Oracle DB
	 */
	@PostConstruct
	private void initDb() {
		System.out.println(String.format("****** Creating table: %s, and Inserting test data ******", "Employees"));

		String sqlStatements[] = { "drop table book if exists;",
				"create table book(BOOK_ID serial,VERSION SMALLINT,ISBN varchar(20),NAME varchar(50),DESCRIPTION varchar(300),PICTUREURL varchar(255),PRICE DECIMAL(10,2),"
						+ "AUTHOR varchar(300),CREATION_DATE timestamp(6));",
				"drop sequence SEQ_TKSK_SURVEY_ID if exists;",
				"create sequence SEQ_TKSK_SURVEY_ID start with 6 increment by 1;",
				"insert into book(BOOK_ID, VERSION, ISBN,NAME,DESCRIPTION, PICTUREURL, PRICE,AUTHOR,CREATION_DATE) values('1','1','1029283','Java Basics','Java 1.8 tutorials',null,'10.1','Joe',CURRENT_TIMESTAMP);",
				"insert into book(BOOK_ID, VERSION, ISBN,NAME,DESCRIPTION, PICTUREURL, PRICE,AUTHOR,CREATION_DATE) values('2','10','7349443','Oracle Basics','Oracle Administration',null,'31','Foo',CURRENT_TIMESTAMP);",
				"insert into book(BOOK_ID, VERSION, ISBN,NAME,DESCRIPTION, PICTUREURL, PRICE,AUTHOR,CREATION_DATE) values('3','1','73679443','Docker Basics','Docker Administration',null,'23','Adam',CURRENT_TIMESTAMP);",
				"insert into book(BOOK_ID, VERSION, ISBN,NAME,DESCRIPTION, PICTUREURL, PRICE,AUTHOR,CREATION_DATE) values('4','1','869643','AKS Basics','AKS Administration',null,'25','Erick',CURRENT_TIMESTAMP);",
				"insert into book(BOOK_ID, VERSION, ISBN,NAME,DESCRIPTION, PICTUREURL, PRICE,AUTHOR,CREATION_DATE) values('5','1','4589443','Istio Basics','Istio Administration',null,'26','Freddy',CURRENT_TIMESTAMP);"};

		Arrays.asList(sqlStatements).stream().forEach(sql -> {
			System.out.println(sql);
			jdbcTemplate.execute(sql);
		});

	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseServer() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
	}

}
