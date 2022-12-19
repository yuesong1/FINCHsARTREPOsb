package com.ethfin.artrepo.controller;

import com.ethfin.artrepo.service.UploadService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
//@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;
    @Value("${file.upload.path}")
    private String fileUploadPath;
    @GetMapping(value = "/upload")
    public String uploadPage(){
        return "upload page";
    }
    @RequestMapping(value = "/upload",
            method = RequestMethod.POST)
    public int upload(@RequestParam("file") MultipartFile[] uploadFiles){
            String currentFile=null;
            try{
                for (MultipartFile uploadFile: uploadFiles
            ) {
                    currentFile=uploadFile.getOriginalFilename();
                String path=fileUploadPath;
                System.out.println("upload to: "+path);
                File file=new File(path);
                if(!file.exists()){
                    file.mkdirs();
                }
                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss_SS");
                String filename=df.format(new Date().getTime());
                //String filename=new Timestamp(System.currentTimeMillis()).toString();
                String newName=filename+'.'+FilenameUtils.getExtension(uploadFile.
                        getOriginalFilename());
                uploadFile.transferTo(new File(file,newName));
                System.out.println(newName);

        }

                return 200;
    }catch(
    IOException e){
           System.out.println(e);
           System.out.println("upload failed: "+ currentFile);
            return 500;
    }
}
}
