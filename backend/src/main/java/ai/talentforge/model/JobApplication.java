package ai.talentforge.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="applications", uniqueConstraints=@UniqueConstraint(columnNames={"candidate_id","job_id"}))
public class JobApplication {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="candidate_id") private Candidate candidate;
 @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="job_id") private Job job;
 @Column(nullable=false) private String stage="APPLIED";
 private Integer matchScore;
 @Column(length=2000) private String notes;
 private Instant appliedAt=Instant.now();
 public Long getId(){return id;} public Candidate getCandidate(){return candidate;} public void setCandidate(Candidate v){candidate=v;}
 public Job getJob(){return job;} public void setJob(Job v){job=v;}
 public String getStage(){return stage;} public void setStage(String v){stage=v;}
 public Integer getMatchScore(){return matchScore;} public void setMatchScore(Integer v){matchScore=v;}
 public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
 public Instant getAppliedAt(){return appliedAt;}
}