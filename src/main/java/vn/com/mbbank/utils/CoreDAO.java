/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.mbbank.utils;

import java.io.File;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author gpdn_huyennv1
 */
public class CoreDAO {

    public static Connection openDatabase() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileReader(new File("./etc/config.properties")));
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        Class.forName(driver).newInstance();
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    /**
     * Chuyen xau kieu CSDL sang xau kieu Java, ky tu dau tien viet thuong.
     * @param input Xau dang ABC_DEF
     * @return Xau dang abcDef
     */
    public static String columnName(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '_') {
                i++;
                output += input.charAt(i);
            } else {
                output += Character.toLowerCase(input.charAt(i));
            }
        }
        return output;
    }

    /**
     * Viet hoa ky tu dau tien.
     * @param input Xau dau vao dang abcDef
     * @return Xau dang AbcDef
     */
    public static String tableName(String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }

    /**
     * Alias cua bang.
     * @param input Xau dang ABC_DEF
     * @return ADF
     */
    public static String alias(String input) {
        String output = "";
        output += input.charAt(0);
        for (int i = 1; i < input.length() - 1; i++) {
            if (input.charAt(i) == '_') {
                i++;
                output += input.charAt(i);
            }
        }
        output += input.charAt(input.length() - 1);
        return output;
    }
}
