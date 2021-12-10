package org.pk.members.service;

import org.apache.log4j.Logger;
import org.pk.members.model.Member;
import org.pk.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    Logger log= Logger.getLogger(this.getClass());

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(String id) {
        return memberRepository.findById(id).orElse(null);
    }

    public boolean addMember(Member member) throws MembersException  {
        try {
            Member existingMember=memberRepository.findById(member.getUserid()).orElse(null);
            if (existingMember==null) {
                memberRepository.save(member);
                return true;
            }else {
                throw new MembersException("Member already exists");
            }
        }catch (Exception e) {
            log.error("Error while adding member, Error: "+e.getMessage());
            return false;
        }
    }

    public boolean updateMember(String userid,Member member) throws MembersException {
        Member existingMember=memberRepository.findById(userid).orElse(null);
        try {
            if (existingMember!=null) {
                existingMember.setUserid(member.getUserid());
                existingMember.setName(member.getName());
                existingMember.setJobsCompleted(member.getJobsCompleted());
                existingMember.setPreferredLocation(member.getPreferredLocation());
                existingMember.setProfileCompleted(member.getProfileCompleted());
                memberRepository.save(existingMember);
                return true;
            }else {
                throw new MembersException("Member does not exist");
            }
        }catch (Exception e) {
            log.error("Error while updating member, Error: "+e.getMessage());
            return false;
        }

    }

    public boolean deleteMember(String userid) throws MembersException {
        try {
            Member existingMember=memberRepository.findById(userid).orElse(null);
            if (existingMember!=null) {
                memberRepository.deleteById(userid);
                return true;
            }   else {
                throw new MembersException("Member does not exist");
            }
        } catch (Exception e) {
            log.error("Error while deleting member, Error: ",e);
            return false;
        }
    }

    public Member getMembersWithMaxJobs() {
        return memberRepository.getMaxJobsCompleted();
    }

    public List<Member> getMembersWithRemote() {
        return memberRepository.getMembersWithRemote();
    }
}