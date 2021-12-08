package org.pk.members.controller;

import org.pk.members.model.Member;
import org.pk.members.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
