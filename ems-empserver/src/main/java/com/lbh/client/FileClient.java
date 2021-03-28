package com.lbh.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author lbh
 * @Date 2021/3/28
 * @Description:
 */

@FeignClient("fileserver")
public interface FileClient {

    //使用openfeign传递文件参数时必须指定consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    @PostMapping(value = "file/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String,Object> upload(@RequestPart("photo") MultipartFile photo);

}
