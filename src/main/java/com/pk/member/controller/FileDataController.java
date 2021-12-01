package com.pk.member.controller;

import com.pk.member.dto.MembersDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

@RestController
public class FileDataController {
    Logger log= Logger.getLogger(this.getClass());



    @Autowired
    private HashMap<String, MembersDTO> membersMap;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @PostMapping("/upload")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {
        File uploadFile = new File(fileUploadPath + file.getOriginalFilename());
        BufferedReader br = null;
        MembersDTO membersDTO = new MembersDTO();
        br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line=null;
        int i = 1;
        while ((line = br.readLine()) != null) {
            log.info("BufferedReader : " + line);
            String[] data = line.split(",");
            membersDTO = new MembersDTO(data[0], data[1], data[2], data[3], Boolean.valueOf(data[4]));
            membersMap.put( data[0], membersDTO);
            i++;
        }
        try {
            if(uploadFile.createNewFile()){
                FileOutputStream fos = new FileOutputStream(uploadFile);
                fos.write(file.getBytes());
                fos.close();
                log.info("File upload success\n");
                return new ResponseEntity<Object>("success", HttpStatus.OK);
            } else {
                log.info("File upload fail, file already exist \n"+file);
                return new ResponseEntity<>(" Error creating file or already exists ", HttpStatus.BAD_REQUEST);
            }
        } catch (FileNotFoundException e) {
            log.error("Error FileNotFoundException : "+e.getMessage());
        } catch (IOException e) {
            log.error("Error IOException : "+e.getMessage());
        } catch (Exception e) {
            log.error("Error Exception : " + e.getMessage());
        }
        Files.deleteIfExists((Path) file);
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/")
    public String Hello() {
        return "Hello";
    }
}
