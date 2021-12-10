package org.pk.members.controller;

import org.pk.members.dto.MembersDTO;
import org.pk.members.service.MembersException;
import org.pk.members.service.MembersService;
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
public class MembersController {
    Logger log= Logger.getLogger(this.getClass());
    BufferedReader br;

    @Autowired
    private MembersService membersService;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @PostMapping("/members-upload")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException, MembersException {
        br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        if(membersService.addMembers(br))
            return new ResponseEntity<>("File Data uploaded Successfully ", HttpStatus.OK);
        else
            return new ResponseEntity<>("File Data uploaded Failed ", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/members/{userid}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public MembersDTO getMember(@PathVariable("userid") String userid) {
        if(membersService.getMember(userid)!=null)
            return membersService.getMember(userid);
        else
            return null;
    }

    @RequestMapping(value = "/members/{userid}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public ResponseEntity<Object> deleteMember(@PathVariable("userid") String userid) {
        if(membersService.deleteMember(userid))
            return new ResponseEntity<>("Deleted Member with userId: "+ userid, HttpStatus.OK);
        else
            return new ResponseEntity<>("Error while deleting the Member ", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/members/remote", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public List<MembersDTO> remoteMember() {
        return membersService.remoteMember();
    }

    @RequestMapping(value = "/members/{userid}", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
    public MembersDTO updateMember(@PathVariable("userid") String userid, @RequestBody MembersDTO membersDTO) {
        if(membersService.updateMember(userid, membersDTO))
            return membersService.getMember(userid);
        else
            return null;
    }

    @RequestMapping(value = "/members", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public MembersDTO insertMember(@RequestBody MembersDTO membersDTO) {
        if(membersService.insertMember(membersDTO))
            return membersService.getMember(membersDTO.getUserid());
        else
            return null;
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public List<MembersDTO> getMembers() {
        return membersService.getMembers();
    }
}