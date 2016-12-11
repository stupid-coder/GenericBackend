package org.buaa.sc.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.sc.dao.GenericDataDao;
import org.buaa.sc.model.GenericDataModel;
import org.buaa.sc.storage.InternalStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qixiang on 12/11/16.
 */
@Repository
public class GenericDataDaoImpl implements GenericDataDao {

    private static Log logger = LogFactory.getLog(GenericDataDaoImpl.class);

    @Autowired
    InternalStorage internalStorage;

    @Override
    public List<GenericDataModel> get(String domain, String status) {
        return internalStorage.get(domain,status);
    }

    @Override
    public GenericDataModel get(String domain, String status, String external_id) {
        return internalStorage.get(domain,status,external_id);
    }

    @Override
    public int add(GenericDataModel model) {
        return internalStorage.add(model);
    }

    @Override
    public int delete(String domain, String status, String external_id) {
        return internalStorage.delete(domain,status,external_id);
    }

    @Override
    public int update(String domain, String status, String external_id, GenericDataModel model) {
        return internalStorage.update(domain,status,external_id,model);
    }

    @Override
    public int append(String domain, String status, String external_id, GenericDataModel model, String old_status) {
        return internalStorage.append(domain,status,external_id,model,old_status);
    }

    @Override
    public int count(String status) {
        return internalStorage.count(status);
    }

    @Override
    public int count(String domain, String status) {
        return internalStorage.count(domain, status);
    }
}
