package com.pluralcamp.daw.persistence.daos.impl.jdbc;

import com.pluralcamp.daw.entities.core.Color;
import com.pluralcamp.daw.persistence.daos.contracts.ColorDAO;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorDAOJDBCImpl implements ColorDAO {
    @Override
    public Color getColorById(long id) throws DAOException {

        // Objectes que calen:
        // 1er objecte - Connexio, via DriverManager de JDBC
        Connection conn = null;
        // 2n objecte - Obrir un canal de Consulta - PraparedStatement
        PreparedStatement sentSQL = null;
        // 2.1 - Enviar la consulta SQL
        ResultSet reader = null;
        // 3er objecte - Obrir un canal de Lectura, un cursor - ResultSet
        Color c = null;

        try {
            // Crear connexion
            conn = DriverManager.getConnection("jdbc-mysql-//localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "alex", "12345678");
            
            // Crear consulta
            sentSQL = conn.prepareStatement("SELECT id, name, red, green, blue, FROM colors where id = ?");
            sentSQL.setLong(1, id);

            // Leer consulta
            reader = sentSQL.executeQuery();

            if (reader.next()) {
                c = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"), reader.getInt("blue"));
                c.setId(reader.getLong("id"));
            }


        } catch (SQLException ex) {
            System.out.println();
        } finally {

        }

        return c;
    }

    @Override
    public List<Color> getColors() throws DAOException {
        return null;
    }

    @Override
    public List<Color> getColors(int offset, int count) throws DAOException {
        return null;
    }

    @Override
    public List<Color> getColors(String searchTerm) throws DAOException {
        return null;
    }

    @Override
    public List<Color> getColors(String searchTerm, int offset, int count) throws DAOException {
        return null;
    }
}
