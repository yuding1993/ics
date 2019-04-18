package org.wheport.ics.util;


import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;

public class AccessServer {
	 
	/**
	 * 访问webService方法
	 * @param serviceUrl webService地址
	 * @param param 参数
	 * @param nameSpace webService的nameSpace
	 * @param method webService的method
	 * @return
	 */
    public static String startAccess(String serviceUrl,String param,String nameSpace,String method){
    	String resultUrl = "";
        try {
            RPCServiceClient rpcServiceClient = new RPCServiceClient();
            Options options = rpcServiceClient.getOptions();
            EndpointReference targetEPR = new EndpointReference(serviceUrl);//http://27.17.33.122:11982/MyGetXUrl/GetUrlPort
            options.setTo(targetEPR);
            // options.setAction("http://webservice.com");
            
            String [] paramArr= param.split(",");
            
            Object[] opAddEntryArgs = paramArr;//此段传参   {"200612204","2","200612204","origin"}
            Class[] classes = new Class[] { String.class };
            // 指定要调用的sfexpressService方法及WSDL文件的命名空间
            QName opAddEntry = new QName(nameSpace,method);//"http://geturl.com/","getUrl"
            Object[] str = rpcServiceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes);
            System.out.println( str[0].toString());
            resultUrl = str[0].toString();
        }catch (AxisFault e){
            e.printStackTrace();
        }
        return resultUrl;
    }
    
}
