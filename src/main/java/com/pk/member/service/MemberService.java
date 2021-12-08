package com.pk.member.service;

import com.pk.member.dto.MembersDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {
    Logger log= Logger.getLogger(this.getClass());
    BufferedReader br;
    MembersDTO membersDTO;

    @Autowired
    public HashMap<String, MembersDTO> membersMap;

    public List<MembersDTO> getMembers(){
        if (membersMap.isEmpty())
            return null;
        else
            return membersMap.values().stream().collect(Collectors.toList());
    }

    public boolean addMembers(BufferedReader br) throws MemberException  {
        this.br = br;
        String line;
        int i = 1;
        try {
            while ((line = br.readLine()) != null) {
                try {
//                    log.info("BufferedReader : " + line);
                    String[] member = line.split(",");
                    if (member[0].equals("usercode")) {
                        i++;
                        continue;
                    }
                    if (member.length != 5) {
                        throw new MemberException("Invalid member data length at line " + i + " : " + Arrays.toString(member));
                    } else if (member[0].trim().equals(null) || member[0].trim().equals("") || member[0].isEmpty()) {
                        throw new MemberException("Invalid member data at line " + i + " at position 1 : " + Arrays.toString(member));
                    } else if (member[1].trim().equals(null) || member[1].trim().equals("") || member[1].isEmpty()) {
                        throw new MemberException("Invalid member data at line " + i + " at position 2 : " + Arrays.toString(member));
                    } else if (member[2].trim().equals(null) || member[2].trim().equals("") || member[2].isEmpty()) {
                        throw new MemberException("Invalid member data at line " + i + " at position 3 : " + Arrays.toString(member));
                    } else if (member[3].trim().equals(null) ||  member[3].trim().equals("") || member[3].isEmpty()) {
                        throw new MemberException("Invalid member data at line " + i + " at position 4 : " + Arrays.toString(member));
                    } else if (member[4].trim().equals(null) || member[4].trim().equals("") || member[4].isEmpty()) {
                        throw new MemberException("Invalid member data at line " + i + " at position 5 : " + Arrays.toString(member));
                    } else {
//                        log.info("Adding member "+i+" : " + member[0]);
//                        log.info("\n userid: " + member[0]+"\n name: " + member[1]+"\n jobs_completed: " + member[2]+"\n preferred_location: " + member[3]+"\n inactive: " + member[4]);
                        membersDTO = new MembersDTO(member[0], member[1], member[2], member[3], Boolean.parseBoolean(member[4]));
                        membersMap.put( member[0], membersDTO);
                    }
                    i++;
                } catch (Exception e) {
                    log.error("Error : " + e.getMessage());
                    e.printStackTrace();
                    return false;
                }
            }
        } catch (IOException e) {
            log.error("Error : " + e.getMessage());
        }
        return true;
    }

    public MembersDTO getMember(String userid) {
        return membersMap.get(userid);
    }

    public boolean updateMember(String userid, MembersDTO membersDTO) {
        if (membersMap.containsKey(userid)) {
            membersMap.put(userid, membersDTO);
            return true;
        } else
            return false;
    }

    public boolean deleteMember(String userid) {
        if (membersMap.containsKey(userid)) {
            membersMap.remove(userid);
            return true;
        } else
            return false;
    }

    public boolean insertMember(MembersDTO membersDTO) {
        try {
            membersMap.put(membersDTO.getUserid(), membersDTO);
        }catch (Exception e){
            log.error("Error : " + e.getMessage());
            return false;
        }
        return true;
    }

    public List<MembersDTO> remoteMember() {
        return membersMap.values().stream().filter(m -> m.getPreferred_location().equals("remote")).collect(Collectors.toList());
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





//    File uploadFile = new File(fileUploadPath + file.getOriginalFilename());
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