package com.ebac.modulo59.model;
import java.sql.SQLException;

public interface OperacionesCRUD<T> {
    T save(T t) throws SQLException;
    T updateById(T t)throws SQLException;
    int deleteById(int id)throws SQLException;
    T getById(int id) throws SQLException;
}