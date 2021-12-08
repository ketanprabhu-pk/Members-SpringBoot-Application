package org.pk.members.repository;

import org.pk.members.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {

    List<Member> findAll();
    Member findByName(String name);
}
