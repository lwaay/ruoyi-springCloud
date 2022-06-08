package com.sinonc.common.oss.service;

import com.aliyun.ocr20191230.Client;
import com.aliyun.ocr20191230.models.RecognizeCharacterRequest;
import com.aliyun.ocr20191230.models.RecognizeCharacterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class OCRUtil {
    private static final Logger logger = LoggerFactory.getLogger(OCRUtil.class.getName());

    @Autowired(required = false)
    private Client client;

    public String ocrImage(String url) throws Exception {
        try{
            if (client == null){
                return "";
            }
            RecognizeCharacterRequest recognizeCharacterRequest = new RecognizeCharacterRequest();
            recognizeCharacterRequest.setImageURL(url);
            recognizeCharacterRequest.setMinHeight(21);
            recognizeCharacterRequest.setOutputProbability(true);
            // 复制代码运行请自行打印 API 的返回值
            RecognizeCharacterResponse response = client.recognizeCharacter(recognizeCharacterRequest);
            return response.getBody().getData().getResults().get(0).text;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return "";
    }

}
