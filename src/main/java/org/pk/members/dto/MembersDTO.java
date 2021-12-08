package org.pk.members.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MembersDTO {
    private String userid;
    private String name;
    private String jobs_completed;
    private String preferred_location;
    private Boolean inactive;
    // getters/setters, custom hashcode/equals

    public String disp() {
        return "user code='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", jobs_completed='" + jobs_completed + '\'' +
                ", preferred_location='" + preferred_location + '\'' +
                ", inactive=" + inactive;
    }
}
