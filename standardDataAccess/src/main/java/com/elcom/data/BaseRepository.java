package com.elcom.data;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class BaseRepository {

    protected Session session;

    public BaseRepository(Session session) {
        this.session = session;
    }

    public <T> T transformObject(Object item, Class<T> input) {

        T result = null;
        try {
            result = input.newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }

        if (result == null) {
            return null;
        }

        Field[] fields = null;

        Object[] o = null;

        fields = result.getClass().getDeclaredFields();

        o = (Object[]) item;

        int stt = 0;

        String fieldType = "";

        Object obj = null;

        for (Field field : fields) {

            if (stt >= o.length) {
                break;
            }

            field.setAccessible(true);

            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }

            fieldType = field.getType().toString();

            try {
                // System.out.println("type["+field.getType().toString()+"]" );

                obj = o[stt];

                if ("class java.lang.String".equals(fieldType)) {
                    field.set(result, obj != null ? String.valueOf(obj) : "");
                } else if ("class java.lang.Long".equals(fieldType)) {
                    field.set(result, obj != null ? Long.valueOf(obj.toString().trim()) : null);
                } else if ("class java.lang.Integer".equals(fieldType)) {
                    field.set(result, obj != null ? Integer.valueOf(obj.toString().trim()) : null);
                } else if ("class java.math.BigDecimal".equals(fieldType)) {
                    field.set(result, obj != null ? (BigDecimal) obj : null);
                } else if ("class java.util.Date".equals(fieldType)) {
                    field.set(result, obj != null ? (Date) obj : null);

                } else if ("interface java.sql.Clob".equals(fieldType)) {
                    field.set(result, obj != null ? (Clob) obj : null);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            stt++;
        }

        return result;
    }

    @SuppressWarnings("rawtypes")
    public <T> List transformList(List list, Class<T> input) {

        List<T> lstObject = new ArrayList<T>();
        for (int i = 0; i < list.size(); i++) {
            try {
                lstObject.add(input.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        List<Object> result = new ArrayList<>();

        Object item = null;

        Field[] fields = null;

        Object[] o = null;

        for (int i = 0; i < list.size(); i++) {

            fields = lstObject.get(i).getClass().getDeclaredFields();

            item = list.get(i);

            o = (Object[]) item;

            int stt = 0;

            String fieldType = "";

            Object obj = null;

            for (Field field : fields) {

                if (stt >= o.length) {
                    break;
                }

                field.setAccessible(true);

                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }

                fieldType = field.getType().toString();

                try {
                    // System.out.println("type["+field.getType().toString()+"]" );

                    obj = o[stt];

                    if ("class java.lang.String".equals(fieldType)) {
                        field.set(lstObject.get(i),
                                obj != null ? String.valueOf(obj) : "");
                    } else if ("class java.lang.Long".equals(fieldType)) {
                        field.set(lstObject.get(i),
                                obj != null ? Long.valueOf(obj.toString())
                                        : null);
                    } else if ("class java.lang.Integer".equals(fieldType)) {
                        field.set(lstObject.get(i),
                                obj != null ? Integer.valueOf(obj.toString())
                                        : null);
                    } else if ("class java.math.BigDecimal".equals(fieldType)) {
                        field.set(lstObject.get(i),
                                obj != null ? (BigDecimal) obj : null);
                    } else if ("class java.util.Date".equals(fieldType)) {
                        field.set(lstObject.get(i), obj != null ? (Date) obj
                                : null);
                    } else if ("interface java.sql.Clob".equals(fieldType)) {
                        field.set(lstObject.get(i), obj != null ? (Clob) obj
                                : null);
                    } else if ("class java.sql.Timestamp".equals(fieldType)) {
                        field.set(lstObject.get(i), obj != null ? (Timestamp) obj
                                : null);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                stt++;
            }
            result.add(lstObject.get(i));
        }
        return result;
    }

    public <T> void removeEntity(Class<T> entity, CriteriaBuilder builder, String key, Object value) {

        try {
            CriteriaDelete<T> criteria = builder.createCriteriaDelete(entity);
            Root<T> root = criteria.from(entity);

            criteria.where(builder.equal(root.get(key), value));

            this.session.createQuery(criteria).executeUpdate();

        } catch (HibernateException ex) {
            throw new DataException(ex);
        }
    }

    public void updateEntity(Object input, String entityName) {

        Object origin = null;
        try {
            Field field = input.getClass().getDeclaredField("id");
            field.setAccessible(true);
            origin = get(input.getClass(), (Long) field.get(input));

            System.out.println("Merge[" + entityName + "] with ID[" + (Long) field.get(input) + "]");

        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (NoSuchFieldException e) {
            //e.printStackTrace();
        }

        if (origin == null) {
            return;
        }

        Field[] fields = input.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                field.set(input, getNotNull(field.get(input), field.get(origin)));
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
            }
        }

        this.session.merge(entityName, input);
    }

    public static <T> T getNotNull(T a, T b) {
        if (a == null) {
            return b;
        }
        return a;
        //return b != null && a != null && !a.equals(b) ? a : b;
    }

    public final Object get(Class<?> entityClass, Long id) {
        try {
            return this.session.get(entityClass, id);
        } catch (HibernateException ex) {
            throw new DataException(ex);
        }
    }

    public final Object get(Class<?> entityClass, Integer id) {
        try {
            return this.session.get(entityClass, id);
        } catch (HibernateException ex) {
            throw new DataException(ex);
        }
    }
}
