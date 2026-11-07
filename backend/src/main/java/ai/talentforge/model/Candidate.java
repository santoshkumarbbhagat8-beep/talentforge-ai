package ai.talentforge.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="candidates")
public class Candidate {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 @Column(nullable=false, unique=true) private String email;
 private String phone;
 @Column(length=4000) private String skills;
 private Integer experienceYears;
 private String location;
 private String resumeUrl;
 @Column(nullable=false) private String status="NEW";
 private Instant createdAt=Instant.now();
 @PreUpdate public void touch(){ }
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public String getSkills(){return skills;} public void setSkills(String v){skills=v;}
 public Integer getExperienceYears(){return experienceYears;} public void setExperienceYears(Integer v){experienceYears=v;}
 public String getLocation(){return location;} public void setLocation(String v){location=v;}
 public String getResumeUrl(){return resumeUrl;} public void setResumeUrl(String v){resumeUrl=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public Instant getCreatedAt(){return createdAt;}
}