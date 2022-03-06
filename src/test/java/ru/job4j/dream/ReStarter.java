package ru.job4j.dream;

import ru.job4j.dream.store.DbStoreTest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class ReStarter {

    public static Connection connection;
    public static Properties config;

    public static Properties loadConfig() {
        try (InputStream in = DbStoreTest.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            config = new Properties();
            config.load(in);
            initConnection();
            return config;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void initConnection() {
        try {
            Class.forName(config.getProperty("jdbc.driver"));
            connection = DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wipeTable() {
        try (PreparedStatement statement = connection.prepareStatement(
                "ALTER TABLE candidate ALTER COLUMN id RESTART WITH 1;"
                        + "DELETE FROM candidate;"
                        + "ALTER TABLE post ALTER COLUMN id RESTART WITH 1;"
                        + "DELETE FROM post;"
                        + "ALTER TABLE users ALTER COLUMN id RESTART WITH 1;"
                        + "DELETE FROM users;")) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection () {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
