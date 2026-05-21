package com.ebac.modulo59.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ebac.modulo59.dto.Phone;

public class PhoneModel implements OperacionesCRUD<Phone>{
    private Connection connection;

    public PhoneModel(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Phone save(Phone phone) throws SQLException {
        String sql = "INSERT INTO phones(idUser, phone, type) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, phone.getIdUser());
        statement.setString(2, phone.getPhone());
        statement.setString(3, phone.getType());

        int elementosInsertados = statement.executeUpdate();
        if (elementosInsertados == 1) {
            return phone;
        }
        throw new SQLException("Error al insertar el teléfono en la base de datos");
    }

    @Override
    public Phone updateById(Phone phone) throws SQLException {
        String sql = "UPDATE phones SET phone = ?, type = ? WHERE idUSer = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, phone.getPhone());
        statement.setString(2, phone.getType());
        statement.setInt(3, phone.getIdUser());

        int elementosInsertados = statement.executeUpdate();
        if (elementosInsertados == 1) {
            return phone;
        }
        throw new SQLException("Error al actualizar el teléfono en la base de datos");
    }

    @Override
    public int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM phones WHERE idUser = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeUpdate();  
    }

    @Override
    public Phone getById(int id) throws SQLException{
        String sql = "SELECT * FROM phones WHERE idUser = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Phone phone = new Phone();

        while(resultSet.next()) {
            phone.setIdUser(resultSet.getInt("idUser"));
            phone.setPhone(resultSet.getString("phone"));
            phone.setType(resultSet.getString("type"));
        }

        return phone;
    }
}
