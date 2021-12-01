package com.pk.member.dto;

public class MembersDTO {
    private String userid;
    private String name;
    private String jobs_completed;
    private String preferred_location;
    private Boolean inactive;
    // getters/setters, custom hashcode/equals

    public MembersDTO() {
    }

    public MembersDTO(String userid, String name, String jobs_completed, String preferred_location, Boolean inactive) {
        this.userid = userid;
        this.name = name;
        this.jobs_completed = jobs_completed;
        this.preferred_location = preferred_location;
        this.inactive = inactive;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String usercode) {
        this.userid = usercode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobs_completed() {
        return jobs_completed;
    }

    public void setJobs_completed(String jobs_completed) {
        this.jobs_completed = jobs_completed;
    }

    public String getPreferred_location() {
        return preferred_location;
    }

    public void setPreferred_location(String preffered_location) {
        this.preferred_location = preffered_location;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public String disp() {
        return "user code='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", jobs_completed='" + jobs_completed + '\'' +
                ", preferred_location='" + preferred_location + '\'' +
                ", inactive=" + inactive;
    }
}
