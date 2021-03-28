package com.lbh.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author lbh
 * @Date 2021/3/28
 * @Description:
 */
@RestController
@Slf4j
public class FileController {

    @PostMapping("file/upload")
    public Map<String,Object> upload(@RequestPart("photo") MultipartFile photo) throws IOException {
        Map<String,Object> map = new HashMap<>();
        try{

            String realPath = System.getProperty("user.dir")
                    +System.getProperty("file.separator")
                    +"ems-fileserver"
                    +System.getProperty("file.separator")
                    +"src"
                    +System.getProperty("file.separator")
                    +"main"
                    +System.getProperty("file.separator")
                    +"resources"
                    +System.getProperty("file.separator")
                    +"photo";

            String extension = FilenameUtils.getExtension(photo.getOriginalFilename());
            String newFileName = UUID.randomUUID().toString()+"."+extension;
            photo.transferTo(new File(realPath,newFileName));

            String url = "http://8990/"+newFileName;
            map.put("url",url);
            map.put("state","true");
            log.info(realPath);
        }catch (Exception e){
            map.put("state","false");
            e.printStackTrace();
        }
        return map;

    }
}
