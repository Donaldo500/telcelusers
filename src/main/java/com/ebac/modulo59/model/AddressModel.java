package com.ebac.modulo59.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ebac.modulo59.dto.Address;

public class AddressModel implements OperacionesCRUD<Address>{
    private Connection connection;

    public AddressModel(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Address save(Address address) throws SQLException {
        String sql = "INSERT INTO addresses(idUser, number, state, street) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, address.getIdUser());
        statement.setInt(2, address.getNumber());
        statement.setString(3, address.getState());
        statement.setString(4, address.getStreet());

        int elementosInsertados = statement.executeUpdate();
        if (elementosInsertados == 1) {
            return address;
        }
        throw new SQLException("Error al insertar el teléfono en la base de datos");
    }

    @Override
    public Address updateById(Address address) throws SQLException {
        String sql = "UPDATE addresses SET number = ?, state = ?, street = ? WHERE idUser = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, address.getNumber());
        statement.setString(2, address.getState());
        statement.setString(3, address.getStreet());
        statement.setInt(4, address.getIdUser());

        int elementosInsertados = statement.executeUpdate();
        if (elementosInsertados == 1) {
            return address;
        }
        throw new SQLException("Error al actualizar la direccion en la base de datos");
    }

    @Override
    public int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM addresses WHERE idUser = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeUpdate();  
    }

    @Override
    public Address getById(int id) throws SQLException{
        String sql = "SELECT * FROM addresses WHERE idUser = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Address address = new Address();

        while(resultSet.next()) {
            address.setIdUser(resultSet.getInt("idUser"));
            address.setNumber(resultSet.getInt("number"));
            address.setState(resultSet.getString("state"));
            address.setStreet(resultSet.getString("street"));
        }

        return address;
    }
}