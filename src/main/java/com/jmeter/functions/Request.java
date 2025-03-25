package com.jmeter.functions;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

// 导入指定jar包
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Request extends AbstractJavaSamplerClient implements JavaSamplerClient {

    // 新增参数，可在jmeter界面上展示
    public Arguments getDefaultParameters(){
        Arguments params = new Arguments();
        params.addArgument("requestBody", "requestBody");
        params.addArgument("responseBody", "responseBody");
        params.addArgument("isWrite", "是否写");
        params.addArgument("path", "路径");
        params.addArgument("timeStamp", "时间戳");
        return params;
    }

    public void setUpTest(JavaSamplerContext context) {
        Request.super.setupTest(context);

    }
    // 实现的方法
    public SampleResult runTest(JavaSamplerContext javaSamplerContext){
        // 实例化请求结果的类
        SampleResult result = new SampleResult();
        result.sampleStart();
        String isWrite = javaSamplerContext.getParameter("isWrite");
        // 这里可以获取到从jmeter界面上对应参数传过来的值，注意这里只能用string，也支持int之类的。看一下源码就知道了。方法不同
        String filePath = "../log/"+javaSamplerContext.getParameter("path")+"/"+javaSamplerContext.getParameter("timeStamp")+".log";
        if(isWrite.equals("1")){
            try{
                String stringText = javaSamplerContext.getParameter("requestBody");
                File file = new File(filePath);
                if(!file.exists()){
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fileWriter);
                bw.write(stringText);
                bw.close();
                // 调用方法定义请求成功
                result.setSuccessful(true);
                result.setResponseData(stringText, "utf-8");
            } catch (IOException e){
                //logger.error(e.getMessage());
                e.printStackTrace();
                result.setResponseData(e.getMessage(), (String) null);
                result.setResponseCode(e.getClass().getSimpleName());
                result.setResponseMessage(e.getMessage());
                result.setSuccessful(false);
            }
        }else{
            result.setSuccessful(true);
            return null;
        }
        result.sampleEnd();
        return result;
    }
}
