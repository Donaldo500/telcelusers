package com.ebac.modulo59.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ebac.modulo59.dto.User;

public class UserModel implements OperacionesCRUD<User>{
    private Connection connection;

    public UserModel(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User save(User user) throws SQLException {
        String sql = "INSERT INTO users(name, age) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setInt(2, user.getAge());

        int elementosInsertados = statement.executeUpdate();
        if (elementosInsertados == 1) {
            return user;
        }
        throw new SQLException("Error al insertar el usuario en la base de datos");
    }

    @Override
    public User updateById(User user) throws SQLException {
        String sql = "UPDATE users SET name = ?, lastName = ?, age = ? WHERE idUser = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getLastName());
        statement.setInt(3, user.getAge());
        statement.setInt(4, user.getIdUser());

        int elementosInsertados = statement.executeUpdate();
        if (elementosInsertados == 1) {
            return user;
        }
        throw new SQLException("Error al actualizar el usuario en la base de datos");
    }

    @Override
    public int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE idUser = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeUpdate();  
    }

    @Override
    public User getById(int id) throws SQLException{
        String sql = "SELECT * FROM users WHERE idUser = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        User user = new User();

        while(resultSet.next()) {
            user.setIdUser(resultSet.getInt("idUser"));
            user.setName(resultSet.getString("name"));
            user.setAge(resultSet.getInt("age"));
        }

        return user;
    }
}
