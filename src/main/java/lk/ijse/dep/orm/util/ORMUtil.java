package lk.ijse.dep.orm.util;

import lk.ijse.dep.orm.annotation.Column;
import lk.ijse.dep.orm.annotation.Entity;
import lk.ijse.dep.orm.annotation.Id;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author : Deshan Charuka <d.c.0729439631@gmail.com>
 * @since : 2021-01-19
 **/
public class ORMUtil {
    public static void init(Properties dbProperties, Class... entities) throws Exception {
        String username = dbProperties.getProperty("javax.persistence.jdbc.username");
        String password = dbProperties.getProperty("javax.persistence.jdbc.password");
        String url = dbProperties.getProperty("javax.persistence.jdbc.url");
        String driverClass = dbProperties.getProperty("javax.persistence.jdbc.driver_class");
        Connection connection = null;
        String sqlScript = "";

        if (url.indexOf('?') == -1){
            url += "?allowMultiQueries=true";
        }else{
            if (!url.contains("allowMultiQueries=true")){
                url += "&allowMultiQueries=true";
            }
        }

        if (username == null | password == null | url == null | driverClass == null) {
            throw new RuntimeException("Unable to initialize ORM Framework without user credentials");
        }

        for (Class entity : entities
        ) {
            boolean pk = false;
            Annotation entityAnnotation = entity.getDeclaredAnnotation(Entity.class);

            if (entityAnnotation == null){
                throw new RuntimeException("Invalid entity class: "+ entity.getName());
            }
            String simpleName = entity.getSimpleName();

            String ddl = "CREATE TABLE "+simpleName+" (\n";

            Field[] declaredFields = entity.getDeclaredFields();

            for (Field declaredField : declaredFields
            ){
                Column columnAnnotation = declaredField.getDeclaredAnnotation(Column.class);
                Id idAnnotation = declaredField.getDeclaredAnnotation(Id.class);

                if (columnAnnotation!=null){
                    String columnName = (columnAnnotation.value().trim().isEmpty()) ? declaredField.getName() : columnAnnotation.value();
                    String colDef = null;
                    if (declaredField.getType()==String.class){
                        colDef = "VARCHAR(255)";
                    }else if (declaredField.getType()==int.class||
                            declaredField.getType()==long.class||
                            declaredField.getType()==short.class
                    ){
                        colDef = "INT";
                    }else if (declaredField.getType()==double.class||
                            declaredField.getType()==float.class||
                            declaredField.getType()== BigDecimal.class
                    ){
                        colDef = "DECIMAL";
                    }else if (declaredField.getType()==boolean.class){
                        colDef = "BOOLEAN";
                    }else {
                        throw new RuntimeException("Invalid data type; Supported Data Types are String, BigDecimal and primitive data types");
                    }

                    ddl += columnName+" "+colDef;

                    if (pk && idAnnotation != null){
                        throw new RuntimeException("Composite keys are not supported yet....");
                    }
                    if (idAnnotation!=null){
                        pk = true;
                        ddl += " PRIMARY KEY";
                    }
                    ddl += ",\n";
                }
            }

            //To remove extra (, and new line)
            ddl = ddl.substring(0, ddl.length() - 2) +  ");\n";
            sqlScript += ddl;
        }
        try {
            Statement stm = connection.createStatement();
            System.out.println(sqlScript);
            stm.execute(sqlScript);
            connection.close();
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed to create tables", throwables);
        }

    }

}
