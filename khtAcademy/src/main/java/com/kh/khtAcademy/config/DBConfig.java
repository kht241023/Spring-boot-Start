package com.kh.khtAcademy.config;


/* @Configuration
 * - 설정용 클래스임 명시
 *   + 객체로 생성해서 내부 코드를 서버 실행시 모두 수행
 *
 *
 * @PropertySource("경로")
 * - 지정된 경로의 properties 파일 내용을 읽어와 사용
 * - 사용할 properties 파일이 다수일 경우
 *   해당 어노테이션을 연속해서 작성하면됨
 *
 * @Bean
 * - 개발자가 수동으로 생성한 객체의 관리를
 *   스프링에게 넘기는 어노테이션
 *   (Bean 등록)
 *
 * @ConfigurationProperties(prefix = "spring.datasource.hikari")
 * - @PropertySource 로 읽어온 properties 파일의 내용 중
 *   접두사(앞부분, prefix)이 일치하는 값만 읽어옴
 *
 * - 읽어온 내용을 생성하려는 Bean에 자동으로 세팅
 *
 *
 * DataSource : Connection 생성 + Connection Pool 지원 하는 객체를
 * 				참조하기 위한 Java 인터페이스
 * 				(DriverManager 대안, Java JNDI 기술 이용)
 *
 *
 * @Autowired
 * - 등록된 Bean 중에서
 *   타입이 일치하거나, 상속 관계에 있는 Bean을
 *   지정된 필드에 주입
 *    == 의존성 주입(DI, Dependency Injection, IOC 관련 기술)
 */


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/config.properties") //MYSQL 아이디 비밀번호 데이터베이스저장소명칭
public class DBConfig {

    @Autowired
    private ApplicationContext applicationContext; // 현재 프로젝트



    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {

        return new HikariConfig();
    }


    @Bean
    public DataSource dataSource(HikariConfig config) {

        DataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception{

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();


        sessionFactoryBean.setDataSource(dataSource);


        sessionFactoryBean.setMapperLocations(
                applicationContext.getResources("classpath:/mappers/**.xml")  );

        sessionFactoryBean.setTypeAliasesPackage("com.kh.khtAcademy");

        sessionFactoryBean.setConfigLocation(
                applicationContext.getResource("classpath:mybatis-config.xml"));

        return sessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }


    @Bean
    public DataSourceTransactionManager
    dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}