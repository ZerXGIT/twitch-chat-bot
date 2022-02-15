package de.zerx.core.mysql;

import java.sql.*;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 28.12.2021 00:48
 */
public class MySQL extends Thread {

    private static final String host = "host";
    private static final String port = "3306";
    private static final String database = "database";
    private static final String username = "root";
    private static final String password = "password";
    private static final String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&serverTimezone=UTC";

    /**
     *
     * @return
     */
    public static <T> Connection getConnection() throws Exception{
        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("MySQL: Connect");
            return conn;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}