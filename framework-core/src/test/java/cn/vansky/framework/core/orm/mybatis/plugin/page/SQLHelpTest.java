package cn.vansky.framework.core.orm.mybatis.plugin.page;

import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.Dialect;
import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.MySQLDialect;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.Searchable;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by IntelliJ IDEA .
 * Auth: hyssop
 * Date: 2016/9/5 0005
 */
public class SQLHelpTest {

    @Test
    public void testGenerateRealPageSql() throws Exception {
        String sql ="select * from table";
        Searchable searchable=  Searchable.newSearchable();
        searchable.setPage(null);
        Dialect dialect = new MySQLDialect();
        SQLHelp.generateRealPageSql(sql,searchable,dialect);

    }
}