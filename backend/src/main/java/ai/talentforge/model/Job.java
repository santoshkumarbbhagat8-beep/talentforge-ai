package ai.talentforge.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="jobs")
public class Job {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false) private String department;
 private String location;
 @Column(length=4000) private String description;
 @Column(nullable=false,length=2000) private String requiredSkills;
 private Integer minExperienceYears;
 private String employmentType="FULL_TIME";
 private String status="OPEN";
 private Instant createdAt=Instant.now();
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getDepartment(){return department;} public void setDepartment(String v){department=v;}
 public String getLocation(){return location;} public void setLocation(String v){location=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;}
 public String getRequiredSkills(){return requiredSkills;} public void setRequiredSkills(String v){requiredSkills=v;}
 public Integer getMinExperienceYears(){return minExperienceYears;} public void setMinExperienceYears(Integer v){minExperienceYears=v;}
 public String getEmploymentType(){return employmentType;} public void setEmploymentType(String v){employmentType=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public Instant getCreatedAt(){return createdAt;}
}