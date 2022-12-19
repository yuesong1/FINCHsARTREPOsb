package com.ethfin.artrepo.service;

import com.ethfin.artrepo.domain.ArtURL;
import com.ethfin.artrepo.repository.ArtURLRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@RequiredArgsConstructor
@Service
public class UploadService {
    @Value("${file.upload.path}")
    private String fileUploadPath;
    private final ArtURLRepository artURLRepository;
    String currentFile=null;
    public String hasDuplicates(MultipartFile multipartFile){
    try{
        String md5=null;
        md5= DigestUtils.md5DigestAsHex(multipartFile.getInputStream()).toUpperCase();
        System.out.println(md5);
        ExampleMatcher md5Matcher=ExampleMatcher.matching()
                .withIgnorePaths("name").withIgnorePaths("url").withIgnorePaths("id")
                .withMatcher("md5",ignoreCase());
        ArtURL probe=new ArtURL();
        probe.setMd5(md5);
        Example<ArtURL> example=Example.of(probe,md5Matcher);
        boolean exists=artURLRepository.exists(example);


        if(exists){
            System.out.println("duplicate found, skipping upload ");
            return null;
        }else {
            return md5;
        }


    }catch (IOException e){
        System.out.println(e);
        System.out.println("upload failed: "+ currentFile);
        return null;
    }

    }

        public void UploadFiles(MultipartFile[] uploadFiles){

            try{
                for (MultipartFile uploadFile: uploadFiles
                ) {
                    //desert empty files
                    //desert duplicated files
                    String md5=hasDuplicates(uploadFile);
                    if(uploadFile.isEmpty()&&md5!=null){
                        continue;
                    }

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
                    String newName=filename+'.'+ FilenameUtils.getExtension(uploadFile.
                            getOriginalFilename());
                    uploadFile.transferTo(new File(file,newName));
                    System.out.println(newName);
                    //update database
                    if(md5!=null){
                        ArtURL artURL=new ArtURL(newName,currentFile,md5);
                        artURLRepository.insert(artURL);
                    }


                }

            }catch(
                    IOException e){
                System.out.println(e);
                System.out.println("upload failed: "+ currentFile);

            }
        }

}
