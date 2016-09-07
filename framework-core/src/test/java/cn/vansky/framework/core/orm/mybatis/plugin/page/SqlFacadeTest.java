package cn.vansky.framework.core.orm.mybatis.plugin.page;

import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.Dialect;
import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.MySQLDialect;
import cn.vansky.framework.core.orm.mybatis.plugin.search.SqlFacade;
import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Searchable;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA .
 * Auth: hyssop
 * Date: 2016/9/5 0005
 */
public class SqlFacadeTest {

    @Test
    public void testGenerateRealPageSql() throws Exception {
        String sql ="select * from table";
        Searchable searchable=  Searchable.newSearchable();
        searchable.setPage(null);
        Dialect dialect = new MySQLDialect();
        SqlFacade.generateRealPageSql(sql, searchable, dialect);

    }
}