package com.june.myspring.dao;

import com.june.myspring.pojo.User;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> queryUserList(Map<String, Object> params) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String sql = "select * from users where name=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,params.get("name"));
            List<User> results = new ArrayList<>();
            User result = null;
            Class<?> clazz = User.class;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result = (User) clazz.getDeclaredConstructor().newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int len = metaData.getColumnCount();
                for(int i = 0; i < len ; i ++){
                    String name = metaData.getColumnName(i + 1);
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(result,resultSet.getObject(i + 1));
                }
                results.add(result);
            }
            return  results;

        } catch (SQLException | InstantiationException|IllegalAccessException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            if(resultSet !=null){
               try {
                   resultSet.close();
               }catch (SQLException e){
                   e.printStackTrace();
               }
            }

            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }


        return null;
    }
}
