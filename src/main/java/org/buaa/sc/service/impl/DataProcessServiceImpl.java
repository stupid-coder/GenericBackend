package org.buaa.sc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.sc.dao.GenericDataDao;
import org.buaa.sc.model.GenericDataModel;
import org.buaa.sc.service.DataProcessService;
import org.buaa.sc.utils.HttpResponseWrapperUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qixiang on 12/11/16.
 */
public class DataProcessServiceImpl implements DataProcessService {

    private Log logger = LogFactory.getLog(DataProcessServiceImpl.class);

    @Autowired
    GenericDataDao genericDataDao;

    @Override
    public HttpResponseWrapperUtils get(String domain, String status) {
        List<GenericDataModel> genericDataModelList = genericDataDao.get(domain,status);
        if ( genericDataModelList.size() == 0 ) {
            logger.warn(String.format("failure to get the data with:%s\t%s",domain,status));
            return new HttpResponseWrapperUtils(null,-1,"failure to get the data");
        } else {
            logger.info(String.format("success to get the data with:%s\t%s",domain,status));
            return new HttpResponseWrapperUtils(genericDataModelList);
        }
    }

    @Override
    public HttpResponseWrapperUtils get(String domain, String status, String external_id) {
        GenericDataModel genericDataModel = genericDataDao.get(domain,status,external_id);
        if ( genericDataModel == null ) {
            logger.warn(String.format("failure to get the data with:%s\t%s\t%s",domain,status,external_id));
            return new HttpResponseWrapperUtils(null,-1,"failure to get the data");
        } else {
            logger.info(String.format("success to get the data:%s",genericDataModel));
            return new HttpResponseWrapperUtils(genericDataModel);
        }
    }

    @Override
    public HttpResponseWrapperUtils add(GenericDataModel model) {
        if ( genericDataDao.add(model) == 0 ) {
            logger.warn(String.format("failure to add the model:%s",model));
            return new HttpResponseWrapperUtils(null,-1,"failure to add the data");
        } else {
            logger.info(String.format("success to add the data:%s",model));
            return new HttpResponseWrapperUtils(null);
        }
    }

    @Override
    public HttpResponseWrapperUtils delete(String domain, String status, String external_id) {
        if ( genericDataDao.delete(domain,status,external_id) == 0 ) {
            logger.warn(String.format("failure to delete the model:%s\t%s\t%s",domain,status,external_id));
            return new HttpResponseWrapperUtils(null,-1,"failure to delete the data");
        } else {
            logger.info(String.format("success to delete the data:%s\t%s\t%s",domain,status,external_id));
            return new HttpResponseWrapperUtils(null);
        }
    }

    @Override
    public HttpResponseWrapperUtils update(String domain, String status, String external_id, GenericDataModel model) {
        if ( genericDataDao.update(domain, status, external_id, model) == 0 ) {
            logger.warn(String.format("failure to update the data:%s\t%s\t%s\t%s",domain,status,external_id));
            return new HttpResponseWrapperUtils(null,-1,"failure to update the data");
        } else {
            logger.info(String.format("success to delete the data:%s\t%s\t%s - %s",domain,status,external_id,model));
            return new HttpResponseWrapperUtils(null);
        }
    }

    @Override
    public HttpResponseWrapperUtils append(String domain, String status, String external_id, GenericDataModel model, String old_status) {
        if ( genericDataDao.append(domain, status, external_id, model, old_status) == 0 ) {
            logger.warn(String.format("failure to append the data:%s\t%s\t%s\t%s",domain,status,external_id));
            return new HttpResponseWrapperUtils(null,-1,"failure to append the data");
        } else {
            logger.info(String.format("success to append the data:%s\t%s\t%s\t%s - %s",domain,status,external_id,old_status,model));
            return new HttpResponseWrapperUtils(null);
        }
    }

    @Override
    public HttpResponseWrapperUtils count(String status) {
        return new HttpResponseWrapperUtils(genericDataDao.count(status));
    }

    @Override
    public HttpResponseWrapperUtils count(String domain, String status) {
        return new HttpResponseWrapperUtils(genericDataDao.count(domain, status));
    }
}
