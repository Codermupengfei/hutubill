package dao;

import com.mysql.jdbc.*;
import entity.Config;
import util.DBUtil;

import javax.xml.transform.Result;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OovEver on 2017/8/29.
 */
public class ConfigDAO {
//    统计总数
    public int getTotal() {
        int total = 0;
        try(Connection c= DBUtil.getConnection(); Statement s =c.createStatement() ) {
            String sql = "select count(*) from config";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("total:" + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
//    增加数据
    public void add(Config config) {
        String sql = "insert into config values(null,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement preparedStatement = c.prepareStatement(sql)) {
            preparedStatement.setString(1, config.key);
            preparedStatement.setString(2, config.value);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getByte(1);
                config.id = id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    更新
    public void update(Config config) {

        String sql = "update config set key_= ?, value=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, config.key);
            ps.setString(2, config.value);
            ps.setInt(3, config.id);

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    //    删除
    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "delete from config where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    //    获取数据
    public Config get(int id) {
        Config config = null;

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from config where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                config = new Config();
                String key = rs.getString("key_");
                String value = rs.getString("value");
                config.key = key;
                config.value = value;
                config.id = id;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return config;
    }

    //获取所有数据
    public List<Config> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Config> list(int start, int count) {
        List<Config> configs = new ArrayList<Config>();

        String sql = "select * from config order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Config config = new Config();
                int id = rs.getInt(1);
                String key = rs.getString("key_");
                String value = rs.getString("value");
                config.id = id;
                config.key = key;
                config.value = value;
                configs.add(config);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return configs;
    }
//    通过键获取Config实例，比如预算对应的Config实例，就会通过这种方式获取： new ConfigDAO().getByKey("budget");
public Config getByKey(String key) {
    Config config = null;
    String sql = "select * from config where key_ = ?" ;
    try (Connection c = DBUtil.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
    ) {

        ps.setString(1, key);
        ResultSet rs =ps.executeQuery();

        if (rs.next()) {
            config = new Config();
            int id = rs.getInt("id");
            String value = rs.getString("value");
            config.key = key;
            config.value = value;
            config.id = id;
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }
    return config;
}
}
