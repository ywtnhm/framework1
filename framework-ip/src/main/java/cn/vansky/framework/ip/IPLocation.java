/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.ip;

/**
 * 封装ip相关信息
 * Author: CK
 * Date: 2016/2/14
 */
public class IPLocation {
    /**
     * 国家
     */
    private String country;
    /**
     * 地区
     */
    private String area;

    public IPLocation() {
    }

    public IPLocation(String country, String area) {
        this.country = country;
        this.area = area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        if ("CZ88.NET".equals(area.trim())) {
            this.area = "局域网";
        } else {
            this.area = area;
        }
    }
}
