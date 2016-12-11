package org.buaa.sc.storage.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.sc.model.GenericDataModel;
import org.buaa.sc.storage.InternalStorage;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by qixiang on 12/11/16.
 */
@Repository
public class MysqlInternalStorageImpl extends JdbcDaoSupport implements InternalStorage {

    private Log logger = LogFactory.getLog(MysqlInternalStorageImpl.class);
    private final String table_name = "GenericData";

    @Resource
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<GenericDataModel> get(String domain, String status) {
        String sql = String.format("SELECT external_id, meta_info, data, create_time FROM %s WHERE domain=? AND status=?",table_name);
        return this.getJdbcTemplate().query(sql,new GenericDataModel(), domain, status);
    }

    @Override
    public GenericDataModel get(String domain, String status, String external_id) {
        String sql = String.format("SELECT meta_info, data, create_time FROM %s WHERE domain=? AND external_id=? AND status=?",table_name);
        try {
            return this.getJdbcTemplate().queryForObject(sql, new GenericDataModel(), domain, external_id, status);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int add(GenericDataModel model) {
        GenericDataModel old_model = get(model.getDomain(), model.getStatus(), model.getExternal_id());
        if ( old_model != null ) {
            logger.warn(String.format("the GenericDataModel is exists:%s",old_model));
            return 0;
        }
        String sql = String.format("INSERT INTO %s(external_id,domain,meta_info,data,status) VALUES(?,?,?,?,?)", table_name);
        return this.getJdbcTemplate().update(sql, model.getExternal_id(), model.getDomain(), model.getMeta_info(), model.getData(), model.getStatus());
    }

    @Override
    public int delete(String domain, String status, String external_id) {
        String sql = String.format("DELETE FROM %s WHERE domain=? AND external_id=? AND status=?",table_name);
        return this.getJdbcTemplate().update(sql,domain,external_id,status);
    }

    @Override
    public int update(String domain, String status, String external_id, GenericDataModel model) {
        String sql = String.format("UPDATE %s SET meta_info=?, data=?, status=? WHERE domain=? AND status=? AND external_id=?", table_name);
        return this.getJdbcTemplate().update(sql,model.getMeta_info(),model.getData(),model.getStatus(),domain,status,external_id);
    }

    @Override
    @Transactional
    public int append(String domain, String status, String external_id, GenericDataModel model, String old_status) {
        GenericDataModel old_model = get(domain,status,external_id);
        if ( old_model == null ) {
            logger.warn(String.format("failure to append the GenericDataModel with domain:%s\tstatus:%s\texternal_id:%s",domain,status,external_id));
            return 0;
        }
        old_model.setStatus(old_status);
        update(domain, status, external_id, old_model);
        return add(model);
    }

    @Override
    public int count(String status) {
        return this.getJdbcTemplate().queryForObject(String.format("SELECT COUNT(*) FROM %s WHERE status=?",table_name),Integer.class,status);
    }

    @Override
    public int count(String domain, String status) {
        return this.getJdbcTemplate().queryForObject(String.format("SELECT COUNT(*) FROM %s WHERE domain=? AND status=?",table_name),Integer.class,domain,status);
    }
}
