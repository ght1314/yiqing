package com.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public interface sendSms {
    Boolean sendMessage(String phone,HashMap<String,Object> code);
}
@Service
class sendmessage implements sendSms {



    @Override
    public Boolean sendMessage(String phone,  HashMap<String, Object> code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",Constants.accessKeyID, Constants.Password);
        IAcsClient client = new DefaultAcsClient(profile);
//构建请求 setSysDomain  setSysAction  不要改 阿里云短信验证 虽然没用
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
//        自定义参数 （手机号，验证码，签名，模版）
        request.putQueryParameter("PhoneNumbers", phone); //手机号
        request.putQueryParameter("SignName", Constants.Sign); //签名
        request.putQueryParameter("TemplateCode", Constants.TemplateCode); //模版
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code));//验证码


        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return true;
        }catch (ServerException e){
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

}
