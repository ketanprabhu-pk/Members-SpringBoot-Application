package com.pk.member.configuration;

import com.pk.member.dto.MembersDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class MembersConfiguration {

    @Bean(name="members")
    public HashMap<String, MembersDTO> membersMap(){
        HashMap<String, MembersDTO> members = new HashMap<String, MembersDTO>();
        return members;
    }
}
