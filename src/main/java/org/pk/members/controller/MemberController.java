package org.pk.members.controller;

import org.pk.members.model.Member;
import org.pk.members.service.MemberService;
import org.pk.members.service.MembersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/member", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @RequestMapping(value = "/member/{userid}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public Member getMemberById(@PathVariable String userid) {
        return memberService.getMemberById(userid);
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ResponseEntity<Object> addMember(@RequestBody Member member) throws MembersException {
        if(memberService.addMember(member)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/member/{userid}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public ResponseEntity<Object> deleteMember(@PathVariable String userid) throws MembersException {
        if(memberService.deleteMember(userid)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/member/{userid}", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
    public ResponseEntity<Object> updateMember(@PathVariable String userid, @RequestBody Member member) throws MembersException {
        if(memberService.updateMember(userid, member)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/member/max-jobs", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public Member getMembersWithMaxJobs() {
        return memberService.getMembersWithMaxJobs();
    }

    @RequestMapping(value="/member/remote", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public List<Member> getMembersWithRemote() {
        return memberService.getMembersWithRemote();
    }

}
