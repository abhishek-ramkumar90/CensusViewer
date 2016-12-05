package com.mars.HibernateUtility;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.spatial.dialect.postgis.PostgisDialect;
import org.hibernate.type.StandardBasicTypes;

public class CustomeDialect extends PostgisDialect {


public CustomeDialect(){
    registerFunction("statusdelete()", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));



}
}
