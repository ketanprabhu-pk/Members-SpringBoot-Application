package com.pk.member.controller;

import com.pk.member.dto.MembersDTO;
import com.pk.member.service.MemberException;
import com.pk.member.service.MemberService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/members-upload")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException, MemberException {
        br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        if(memberService.addMembers(br))
            return new ResponseEntity<>("File Data uploaded Successfully ", HttpStatus.OK);
        else
            return new ResponseEntity<>("File Data uploaded Failed ", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/members/{userid}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public MembersDTO getMember(@PathVariable("userid") String userid) {
        if(memberService.getMember(userid)!=null)
            return memberService.getMember(userid);
        else
            return null;
    }
    @RequestMapping(value = "/members", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
    public MembersDTO updateMember(@PathVariable("userid") String userid, @RequestBody MembersDTO membersDTO) {
        if(memberService.updateMember(userid, membersDTO))
            return memberService.getMember(userid);
        else
            return null;
    }

    @RequestMapping(value = "/members/{userid}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public ResponseEntity<Object> deleteMember(@PathVariable("userid") String userid) {
        if(memberService.deleteMember(userid))
            return new ResponseEntity<>("Deleted Member with userId: "+ userid, HttpStatus.OK);
        else
            return new ResponseEntity<>("Error while deleting the Member ", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public List<MembersDTO> getMembers() {
        return memberService.getMembers();
    }
}