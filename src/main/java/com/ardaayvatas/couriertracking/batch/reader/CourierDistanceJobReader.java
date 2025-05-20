package com.ardaayvatas.couriertracking.batch.reader;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.item.database.Order;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class CourierDistanceJobReader {

    @Bean
    public JdbcPagingItemReader<Long> courierDistanceReader(DataSource dataSource) {
        JdbcPagingItemReader<Long> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setPageSize(100);
        reader.setRowMapper((rs, rowNum) -> rs.getLong("ID"));

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("SELECT ID");
        queryProvider.setFromClause("FROM couriers");
        queryProvider.setSortKeys(Map.of("ID", Order.ASCENDING));

        reader.setQueryProvider(queryProvider);
        return reader;
    }
}

