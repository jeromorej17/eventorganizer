package com.homedepot.eventorganizer.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGInterval;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

public class Interval implements UserType {
    private static final int[] SQL_TYPES = { Types.OTHER };

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return Integer.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        String interval = resultSet.getString(names[0]);
        if(resultSet.wasNull() || interval == null){
            return null;
        }
        PGInterval pgInterval = new PGInterval(interval);
        Date epoch  = new Date(0l);
        pgInterval.add(epoch);
        return Integer.valueOf((int) epoch.getTime() /1000);
    }

    public static String getInterval(int value){
        return new PGInterval(0, 0, 0, 0, 0, value).getValue();
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (value == null) {
            preparedStatement.setNull(index, Types.VARCHAR);
        } else {
            //this http://postgresql.1045698.n5.nabble.com/Inserting-Information-in-PostgreSQL-interval-td2175203.html#a2175205
            preparedStatement.setObject(index, getInterval(((Integer) value).intValue()), Types.OTHER);
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object original, Object o1, Object o2) throws HibernateException {
        return original;
    }
}
