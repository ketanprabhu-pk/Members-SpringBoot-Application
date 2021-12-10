package org.pk.members.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    private String userid;

    private String name;
    private int jobsCompleted;
    private String preferredLocation;
    private boolean profileCompleted;

    public boolean getProfileCompleted() {
        return profileCompleted;
    }
}
