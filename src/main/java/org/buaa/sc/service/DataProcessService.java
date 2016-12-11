package org.buaa.sc.service;

import org.buaa.sc.model.GenericDataModel;
import org.buaa.sc.utils.HttpResponseWrapperUtils;
import org.springframework.stereotype.Service;

/**
 * Created by qixiang on 12/11/16.
 */

public interface DataProcessService {

    HttpResponseWrapperUtils get(String domain, String status);

    HttpResponseWrapperUtils get(String domain, String status, String external_id);

    HttpResponseWrapperUtils add(GenericDataModel model);

    HttpResponseWrapperUtils delete(String domain, String status, String external_id);

    HttpResponseWrapperUtils update(String domain, String status, String external_id, GenericDataModel model);

    HttpResponseWrapperUtils append(String domain, String status, String external_id, GenericDataModel model, String old_status);

    HttpResponseWrapperUtils count(String status);

    HttpResponseWrapperUtils count(String domain, String status);

}
