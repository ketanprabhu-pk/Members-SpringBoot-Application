package org.pk.members.repository;

import org.pk.members.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {

    List<Member> findAll();

    @Query(value = "SELECT * FROM member WHERE jobs_completed = (SELECT max(jobs_completed) FROM member)", nativeQuery = true)
    Member getMaxJobsCompleted();

    @Query(value = "SELECT * FROM member WHERE preferred_location = 'remote'", nativeQuery = true)
    List<Member> getMembersWithRemote();

}
