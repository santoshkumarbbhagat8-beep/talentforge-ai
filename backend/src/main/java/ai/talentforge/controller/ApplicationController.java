package ai.talentforge.controller;
import ai.talentforge.model.*;
import ai.talentforge.repository.*;
import ai.talentforge.service.MatchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/applications")
public class ApplicationController {
 private final ApplicationRepository applications; private final CandidateRepository candidates; private final JobRepository jobs; private final MatchService matcher;
 public ApplicationController(ApplicationRepository a,CandidateRepository c,JobRepository j,MatchService m){applications=a;candidates=c;jobs=j;matcher=m;}
 public record ApplicationInput(@NotNull Long candidateId,@NotNull Long jobId,String stage,String notes){}
 @GetMapping public List<JobApplication> all(@RequestParam(required=false) Long jobId,@RequestParam(required=false) Long candidateId){
  if(jobId!=null)return applications.findByJobId(jobId);if(candidateId!=null)return applications.findByCandidateId(candidateId);return applications.findAll();
 }
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public JobApplication create(@Valid @RequestBody ApplicationInput in){
  if(applications.existsByCandidateIdAndJobId(in.candidateId(),in.jobId()))throw new ResponseStatusException(HttpStatus.CONFLICT,"Candidate has already applied to this job");
  Candidate c=candidates.findById(in.candidateId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Candidate not found"));
  Job j=jobs.findById(in.jobId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Job not found"));
  JobApplication a=new JobApplication();a.setCandidate(c);a.setJob(j);a.setMatchScore(matcher.score(c,j));a.setNotes(in.notes());if(in.stage()!=null)a.setStage(in.stage().toUpperCase());return applications.save(a);
 }
 @PatchMapping("/{id}/stage") public JobApplication stage(@PathVariable Long id,@RequestParam @NotBlank String value){
  JobApplication a=applications.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Application not found"));a.setStage(value.toUpperCase());return applications.save(a);
 }
}