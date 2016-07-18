package cn.vansky.framework.common.util;

import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.*;

public class DateUtilTest {

    @Test
    public void testDateRedMonthBegin() throws Exception {
        Date date = DateUtil.parse(DateUtil.yyyyMM, "201603");
        String startDate = DateUtil.dateRedMonthBegin(date, 0) + " 00:00:00";
        System.out.println(startDate);
    }

    @Test
    public void testDateRedMonthEnd() throws Exception {
        Date date = DateUtil.parse(DateUtil.yyyyMM, "201603");
        String endDate = DateUtil.dateRedMonthEnd(date, 0) + " 23:59:59";
        System.out.println(endDate);
    }
}