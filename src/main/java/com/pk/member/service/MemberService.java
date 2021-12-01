package com.pk.member.service;

import com.pk.member.dto.MembersDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
public class MemberService {
    Logger log= Logger.getLogger(this.getClass());
    BufferedReader br;
    String line;
    MembersDTO membersDTO;
    List<MembersDTO> membersDTOList;

    public List<MembersDTO> getMembers(){
        if (membersDTOList.isEmpty())
            return null;
        else
            return membersDTOList;
    }

    public boolean addMembers(BufferedReader br) throws MemberException  {
        this.br = br;
        String line;
        int i = 1;
        try {
            while ((line = br.readLine()) != null) {
                try {
                    log.info("BufferedReader : " + line);
                    String[] member = line.split(",");
                    if (member[0].equals("usercode")) {
                        i++;
                        continue;
                    }
                    try {
                        if (member.length != 5) {
                            throw new MemberException("Invalid member data length at line " + i + " : " + Arrays.toString(member));
                        } else if (member[0].trim().equals("") || member[0].isEmpty()) {
                            throw new MemberException("Invalid member data at line " + i + " at position 1 : " + Arrays.toString(member));
                        } else if (member[1].trim().equals("") || member[1].isEmpty()) {
                            throw new MemberException("Invalid member data at line " + i + " at position 2 : " + Arrays.toString(member));
                        } else if (member[2].trim().equals("") || member[2].isEmpty()) {
                            throw new MemberException("Invalid member data at line " + i + " at position 3 : " + Arrays.toString(member));
                        } else if ( member[3].trim().equals("") || member[3].isEmpty()) {
                            throw new MemberException("Invalid member data at line " + i + " at position 4 : " + Arrays.toString(member));
                        } else if (member[4].trim().equals("") || member[4].isEmpty()) {
                            throw new MemberException("Invalid member data at line " + i + " at position 5 : " + Arrays.toString(member));
                        } else {
                            log.info("Adding member: " + member[0]);
                            membersDTO = new MembersDTO(member[0], member[1], member[2], member[3], Boolean.parseBoolean(member[4]));
                            membersDTOList.add(membersDTO);
                        }
                    } catch (Exception e) {
                        log.error("Error at line " + i + " : " + Arrays.toString(member));
                    }
                    i++;
                } catch (Exception e) {
                    log.error("Error : " + e.getMessage());
                    return false;
                }
            }
        } catch (IOException e) {
            log.error("Error : " + e.getMessage());
        }
        return true;
    }

    public MembersDTO updateMember(MembersDTO member) {
        return null;
    }

    public MembersDTO deleteMember(String id) {
        return null;
    }

}
//    File file;
//    public MemberService(File file) {
//        this.file = file;
//    }
//
//    public List<MembersDTO> getMember(BufferedReader br) throws MemberException {
//        BufferedReader br = null;
//        MembersDTO membersDTO = new MembersDTO();
//        br = new BufferedReader(new InputStreamReader(file.getInputStream()));
//        String line=null;
//        int i = 1;
//        while ((line = br.readLine()) != null) {
//            log.info("BufferedReader : " + line);
//            String[] data = line.split(",");
//            membersDTO = new MembersDTO(data[0], data[1], data[2], data[3], Boolean.valueOf(data[4]));
//            membersMap.put( data[0], membersDTO);
//            i++;
//        }
//        return
//    }
//}
