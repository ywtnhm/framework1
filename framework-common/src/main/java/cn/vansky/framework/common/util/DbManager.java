/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util;

import cn.vansky.framework.common.constant.Constant;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/3/27.
 */
public class DbManager {

    /**
     * Instantiates a new Db manager.
     */
    public DbManager() {
    }

    /**
     * Instantiates a new Db manager.
     *
     * @param url the url
     * @param user the user
     * @param password the password
     */
    public DbManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * default the driveName of mysql
     */
    private String driveName = Constant.DEFAULT_DRIVE_NAME;

    /**
     * the database url
     */
    private String url;

    /**
     * the database user
     */
    private String user;

    /**
     * the database password
     */
    private String password;

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        try {
            Class.forName(this.driveName);
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("加载数据库驱动类异常:" + e.getMessage(), e);
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库连接异常:" + e.getMessage(), e);
        }
    }

    /**
     * Gets prepared statement.
     *
     * @param connection the connection
     * @param sql the sql
     * @return the prepared statement
     */
    public PreparedStatement getPreparedStatement(Connection connection, String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException("获取PrepareStatement异常:" + e.getMessage(), e);
        }
    }

    /**
     * Gets result set.
     *
     * @param preparedStatement the prepared statement
     * @return the result set
     */
    public ResultSet getResultSet(PreparedStatement preparedStatement) {
        try {
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("获取ResultSet异常:" + e.getMessage(), e);
        }
    }

    /**
     * 获取表信息
     * @param connection the connection
     * @param localCatalog 表类别
     * @param localSchema 表模式
     * @param localTableName 表名称
     * @return 表信息
     */
    public ResultSet getTables(Connection connection, String localCatalog, String localSchema, String localTableName) {
        try {
            return getDatabaseMetaData(connection).getTables(localCatalog, localSchema, localTableName, null);
        } catch (SQLException e) {
            throw new RuntimeException("获取getTables异常:" + e.getMessage(), e);
        }
    }

    /**
     * 获取列信息
     * @param connection the connection
     * @param localCatalog 表类别
     * @param localSchema 表模式
     * @param localTableName 表名称
     * @return 列信息

     */
    public ResultSet getColumns(Connection connection, String localCatalog, String localSchema, String localTableName) {
        try {
            return getDatabaseMetaData(connection).getColumns(localCatalog, localSchema, localTableName, null);
        } catch (SQLException e) {
            throw new RuntimeException("获取getColumns异常:" + e.getMessage(), e);
        }
    }

    /**
     * 获取主键信息
     * @param connection the connection
     * @param localCatalog 表类别
     * @param localSchema 表模式
     * @param localTableName 表名称
     * @return 主键信息
     */
    public ResultSet getPrimaryKeys(Connection connection, String localCatalog, String localSchema, String localTableName) {
        try {
            return getDatabaseMetaData(connection).getPrimaryKeys(localCatalog, localSchema, localTableName);
        } catch (SQLException e) {
            throw new RuntimeException("获取getPrimaryKeys异常:" + e.getMessage(), e);
        }
    }


    /**
     * Gets result set meta data.
     *
     * @param resultSet the result set
     * @return the result set meta data
     */
    public ResultSetMetaData getResultSetMetaData(ResultSet resultSet) {
        try {
            return resultSet.getMetaData();
        } catch (SQLException e) {
            throw new RuntimeException("获取ResultSetMetaData异常:" + e.getMessage(), e);
        }
    }

    /**
     * Gets database meta data.
     *
     * @param connection the connection
     * @return the database meta data
     */
    public DatabaseMetaData getDatabaseMetaData(Connection connection) {
        try {
            return connection.getMetaData();
        } catch (SQLException e) {
            throw new RuntimeException("获取DatabaseMetaData异常:" + e.getMessage(), e);
        }
    }

    /**
     * Close void.
     *
     * @param connection the connection
     * @param preparedStatement the prepared statement
     * @param resultSet the result set
     */
    public void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                resultSet = null;
            }
        }
        if (null != preparedStatement) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                preparedStatement = null;
            }
        }
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                connection = null;
            }
        }
    }

    /**
     * Close void.
     *
     * @param connection the connection
     */
    public void close(Connection connection) {
        close(connection, null, null);
    }

    /**
     * Close void.
     *
     * @param preparedStatement the prepared statement
     */
    public void close(PreparedStatement preparedStatement) {
        close(null, preparedStatement, null);
    }

    /**
     * Close void.
     *
     * @param resultSet the result set
     */
    public void close(ResultSet resultSet) {
        close(null, null, resultSet);
    }

    /**
     * Gets drive name.
     *
     * @return the drive name
     */
    public String getDriveName() {
        return driveName;
    }

    /**
     * Sets drive name.
     *
     * @param driveName the drive name
     */
    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
