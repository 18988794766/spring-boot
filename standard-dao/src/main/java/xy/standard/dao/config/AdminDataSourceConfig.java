package xy.standard.dao.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * admin数据库配置
 * Author：HuangLibin 2019/09/08
 */
@Configuration
@MapperScan(basePackages = "xy.standard.dao.dao.admin", sqlSessionFactoryRef = "adminSqlSessionFactory")
public class AdminDataSourceConfig {
    /**
     * 数据源
     */
    @Primary
    @Bean(name = "adminDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.admin")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * session工厂
     */
    @Primary
    @Bean(name = "adminSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("adminDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setPlugins(new Interceptor[]{ new PaginationInterceptor()});
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 事务管理器
     */
    @Primary
    @Bean(name = "adminTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("adminDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
