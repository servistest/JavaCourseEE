package edu.springtest.interactiontesting;


import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


/**
 * Created by Admin on 08.11.2016.
 */
@Configuration
@ImportResource("classpath:spring/data-source-h2-tx-jpa-dev.xml")
@ComponentScan(basePackages = {"edu.springtest"})
//Аннотация @Profile указывает, что  бины, определенные в этом классе, принадлежат профилю test.
@Profile("test")
public class ServiceTestConfig {

    @Bean(name = "dataSource")
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:config/schema.sql")
                .build();
    }

    @Bean(name = "databaseTester")
    public DataSourceDatabaseTester dataSourceDatabaseTester(){
        DataSourceDatabaseTester dataSourceDatabaseTester=new DataSourceDatabaseTester(dataSource());
        return dataSourceDatabaseTester;
    }

    @Bean(name = "xlsDataFileLoader")
    public XlsDataFileLoader xlsDataFileLoader(){
        return new XlsDataFileLoader();
    }

}
