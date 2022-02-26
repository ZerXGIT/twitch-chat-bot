package eu.kenexar.core.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import eu.kenexar.core.logger.MainLogger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 28.12.2021 00:48
 */
public class MySQL {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);
    private static final String host = "host";
    private static final int port = 3306;
    private static final String database = "database";
    private static final String password = "password";
    private static final String username = "root";
    private static final String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&serverTimezone=UTC";
    private static Connection connection;

    public static void connect() throws SQLException {
        MysqlDataSource rv = new MysqlDataSource();
        rv.setServerName(host);
        rv.setPortNumber(port);
        rv.setUser(username);
        rv.setPassword(password);
        rv.setDatabaseName(database);
        rv.setAllowMultiQueries(false);
        rv.setAutoReconnect(true);
        rv.setCharacterEncoding("UTF-8");
        rv.setServerTimezone(TimeZone.getDefault().getID());
        rv.setRewriteBatchedStatements(true);

        MainLogger.get().info("Connection with database {}", rv.getUrl());
        connection = rv.getConnection();
    }

    public static ResultSet get(String sql) {
        ResultSet rs = null;
        try {
            rs = MySQL.getConnection().prepareStatement(sql).executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static Connection getConnection(){
        return connection;
    }

}