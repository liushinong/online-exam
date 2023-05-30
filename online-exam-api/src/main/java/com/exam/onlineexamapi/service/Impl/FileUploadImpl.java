package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.config.property.QnConfig;
import com.exam.onlineexamapi.config.property.SystemConfig;
import com.exam.onlineexamapi.service.FileUpload;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;

@Service
public class FileUploadImpl implements FileUpload {

    @Resource
    SystemConfig systemConfig;

    @Override
    public String uploadFile(InputStream inputStream, long size, String extName) {
        QnConfig qnConfig = systemConfig.getQn();
        com.qiniu.storage.Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qnConfig.getAccessKey(), qnConfig.getSecretKey());
        String upToken = auth.uploadToken(qnConfig.getBucket());
        try {
            Response response = uploadManager.put(inputStream, null, upToken, null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return qnConfig.getUrl() + "/" + putRet.key;
        } catch (QiniuException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
