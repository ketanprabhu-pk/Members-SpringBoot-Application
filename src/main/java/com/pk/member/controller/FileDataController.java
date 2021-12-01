package com.pk.member.controller;

import com.pk.member.dto.MembersDTO;
import com.pk.member.service.MemberException;
import com.pk.member.service.MemberService;
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
import java.util.List;

@RestController
public class FileDataController {
    Logger log= Logger.getLogger(this.getClass());
    BufferedReader br;

    @Autowired
    private MemberService memberService;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @PostMapping("/upload")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException, MemberException {
//        File uploadFile = new File(fileUploadPath + file.getOriginalFilename());
//        try {
//            if(uploadFile.createNewFile()){
//                FileOutputStream fos = new FileOutputStream(uploadFile);
//                fos.write(file.getBytes());
//                fos.close();
//                log.info("File upload success\n");
//                return new ResponseEntity<Object>("success", HttpStatus.OK);
//            } else {
//                log.info("File upload fail, file already exist \n"+file);
//                return new ResponseEntity<>(" Error creating file or already exists ", HttpStatus.BAD_REQUEST);
//            }
//        } catch (FileNotFoundException e) {
//            log.error("Error FileNotFoundException : "+e.getMessage());
//        } catch (IOException e) {
//            log.error("Error IOException : "+e.getMessage());
//        } catch (Exception e) {
//            log.error("Error Exception : " + e.getMessage());
//        }
//        Files.deleteIfExists((Path) file);
        br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        if(memberService.addMembers(br))
            return new ResponseEntity<>("File Data uploaded Successfully ", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("File Data uploaded Failed ", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/members")
    public List<MembersDTO> members() {
        return memberService.getMembers();
    }

}