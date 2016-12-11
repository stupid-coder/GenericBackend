package org.buaa.sc.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.sc.model.GenericDataModel;
import org.buaa.sc.service.DataProcessService;
import org.buaa.sc.utils.HttpResponseWrapperUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qixiang on 12/11/16.
 */

@RestController
public class DataProcessController {

    private static Log logger = LogFactory.getLog(DataProcessController.class);

    @Resource
    DataProcessService dataProcessService;

    @RequestMapping(value="/data/{domain}/{status}/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils getData(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @PathVariable("domain") String domain,
                                            @PathVariable("status") String status)
    {
        return dataProcessService.get(domain,status);
    }


    @RequestMapping(value="/data/{domain}/{status}/{external_id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils getData(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @PathVariable("domain") String domain,
                                            @PathVariable("status") String status,
                                            @PathVariable("external_id") String external_id)
    {
        return dataProcessService.get(domain,status,external_id);
    }

    @RequestMapping(value="/data/", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils addData(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestBody GenericDataModel model)
    {
        return dataProcessService.add(model);
    }

    @RequestMapping(value="/data/{domain}/{status}/{external_id}", method= RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils updateData(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @PathVariable("domain") String domain,
                                               @PathVariable("status") String status,
                                               @PathVariable("external_id") String external_id,
                                               @RequestBody GenericDataModel model)
    {
        return dataProcessService.update(domain,status,external_id,model);
    }

    @RequestMapping(value="/data/{domain}/{status}/{external_id}", method= RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils deleteData(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @PathVariable("domain") String domain,
                                               @PathVariable("status") String status,
                                               @PathVariable("external_id") String external_id)
    {
        return dataProcessService.delete(domain,status,external_id);
    }


    @RequestMapping(value="/data/{domain}/{status}/{external_id}/{old_status}", method= RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils appendData(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @PathVariable("domain") String domain,
                                               @PathVariable("status") String status,
                                               @PathVariable("external_id") String external_id,
                                               @RequestBody GenericDataModel model,
                                               @PathVariable("old_status") String old_status)
    {
        return dataProcessService.append(domain,status,external_id, model, old_status);
    }

    @RequestMapping(value="/count/{status}/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils countData(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @PathVariable("status") String status)
    {
        return dataProcessService.count(status);
    }

    @RequestMapping(value="/count/{domain}/{status}/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils countData(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @PathVariable("domain") String domain,
                                              @PathVariable("status") String status)
    {
        return dataProcessService.count(domain,status);
    }

}
