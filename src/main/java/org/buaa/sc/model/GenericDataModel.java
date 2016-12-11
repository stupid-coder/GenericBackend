package org.buaa.sc.model;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by qixiang on 12/11/16.
 */
public class GenericDataModel implements java.io.Serializable, RowMapper<GenericDataModel> {

    private static final long serialVersionUID = 1L;

    private String domain;
    private String id;
    private String external_id;
    private String meta_info;
    private String data;
    private String create_time;
    private String status;

    @Override
    public GenericDataModel mapRow(ResultSet resultSet, int line) throws SQLException
    {
        GenericDataModel model = new GenericDataModel();

        Field[] fields = this.getClass().getDeclaredFields();

        for ( int i = 0; i < fields.length; ++ i )
        {
            try {
                fields[i].setAccessible(true);

                if (Modifier.isStatic(fields[i].getModifiers())) continue;

                fields[i].set(model,resultSet.getString(fields[i].getName()));
            } catch (Exception e) {
                continue;
            }
        }

        return model;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeta_info() {
        return meta_info;
    }

    public void setMeta_info(String meta_info) {
        this.meta_info = meta_info;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GenericDataModel{" +
                "domain='" + domain + '\'' +
                ", id='" + id + '\'' +
                ", external_id='" + external_id + '\'' +
                ", meta_info='" + meta_info + '\'' +
                ", data='" + data + '\'' +
                ", create_time='" + create_time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
