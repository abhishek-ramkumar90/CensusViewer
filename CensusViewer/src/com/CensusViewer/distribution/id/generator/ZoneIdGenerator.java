package com.CensusViewer.distribution.id.generator;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
public class ZoneIdGenerator implements IdentifierGenerator {
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        String prefix = "ZN";
        Connection connection = session.connection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT nextval('zone_id')");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("nextval");
                String code = prefix + id;
                System.out.println("zone code - " + code);
                return code;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new HibernateException("Unable to generate zone Code Sequence");
        }
        return null;
    }
}


