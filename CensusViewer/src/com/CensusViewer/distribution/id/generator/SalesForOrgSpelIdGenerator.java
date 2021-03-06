package com.CensusViewer.distribution.id.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SalesForOrgSpelIdGenerator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {

        String prefix = "SFORGSPEL";
        Connection connection = session.connection();
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT nextval('sales_for_org_spel_id')");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("nextval");
                String code = prefix + id;
                System.out.println("code - " + code);
                return code;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new HibernateException("Unable to generate Stock Code Sequence");
        }
        return null;
    }
}

