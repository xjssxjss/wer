package com.wer.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wer.common.BaseObject;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingOperationInfo;

import javax.xml.namespace.QName;

/**
 * @description: webservice单例工具类
 * @package_name: com.wer.util
 * @data: 2019-10-22 13:47
 * @author: Sean
 * @version: V1.0
 */
public class WebServiceClientUtil extends BaseObject{

    //声明连接url
    private static String portUrl = null;

    static{
        portUrl = resourceMap.get("apec_data_econciliation_url");
    }

    private Client objClient = null;

    private static final WebServiceClientUtil _uniqueInstance = new WebServiceClientUtil();

    private WebServiceClientUtil(){
        //if(objClient == null){
            String wsUrl = portUrl;
            if(!wsUrl.toLowerCase().endsWith("?wsdl")){
                wsUrl = wsUrl + "?wsdl";
            }
            DynamicClientFactory objDynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
            objClient = objDynamicClientFactory.createClient(wsUrl);
        //}
    }

    //获取对象实例
    public static WebServiceClientUtil getInstance(){
        return new WebServiceClientUtil();
    }

    /**
     * 调用webservice接口对象
     * @param method 调用的方法名称
     * @param args  所带参数
     * @return
     * @throws Exception
     */
    public Object[] callService(String method,Object ...args) throws Exception {
        org.apache.cxf.endpoint.Endpoint endpoint = objClient.getEndpoint();
        QName opName = new QName(endpoint.getService().getName().getNamespaceURI(),method);
        BindingInfo bindingInfo = endpoint.getEndpointInfo().getBinding();
        if(bindingInfo.getOperation(opName) == null){
            for (BindingOperationInfo operationInfo:bindingInfo.getOperations()){
                if(method.equals(operationInfo.getName().getLocalPart())){
                    opName = operationInfo.getName();
                    break;
                }
            }
        }
        Object[] result = objClient.invoke(opName,args);
        objClient.destroy();
        return result;
    }

    /**
     * 发起webservice请求
     * @param methodName
     * @param args
     * @return
     */
    public static JSONArray callWebService(String methodName,Object ...args) throws Exception{
        Object[] result = getInstance().callService(methodName, args);
        //获取返回的字符串
        String jsonStr = result[0].toString();

        if(!StringUtil.isEmpty(jsonStr)){
            //把对象转成JAONArray
            JSONArray array = JSON.parseArray(jsonStr);
            return array;
        }
        return null;
    }
}