package org.buaa.sc.storage;

import org.buaa.sc.model.GenericDataModel;

import java.util.List;

/**
 * Created by qixiang on 12/11/16.
 */
public interface InternalStorage {

    List<GenericDataModel> get(String domain, String status);

    GenericDataModel get(String domain, String status, String external_id);

    int add(GenericDataModel model);

    int delete(String domain, String status, String external_id);

    int update(String domain, String status, String external_id, GenericDataModel model);

    int append(String domain, String status, String external_id, GenericDataModel model, String old_status);

    int count(String status);

    int count(String domain, String status);
}
