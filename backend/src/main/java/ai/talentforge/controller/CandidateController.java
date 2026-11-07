package ai.talentforge.controller;
import ai.talentforge.model.Candidate;
import ai.talentforge.repository.CandidateRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/candidates")
public class CandidateController {
 private final CandidateRepository repo;
 public CandidateController(CandidateRepository repo){this.repo=repo;}
 public record CandidateInput(@NotBlank String name,@Email @NotBlank String email,String phone,String skills,@Min(0) Integer experienceYears,String location,String resumeUrl,String status){}
 @GetMapping public List<Candidate> all(){return repo.findAll();}
 @GetMapping("/{id}") public Candidate get(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Candidate not found"));}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Candidate create(@Valid @RequestBody CandidateInput in){
  Candidate c=new Candidate(); apply(c,in); return repo.save(c);
 }
 @PutMapping("/{id}") public Candidate update(@PathVariable Long id,@Valid @RequestBody CandidateInput in){
  Candidate c=get(id); apply(c,in); return repo.save(c);
 }
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){repo.delete(get(id));}
 private void apply(Candidate c,CandidateInput in){c.setName(in.name());c.setEmail(in.email());c.setPhone(in.phone());c.setSkills(in.skills());c.setExperienceYears(in.experienceYears());c.setLocation(in.location());c.setResumeUrl(in.resumeUrl());if(in.status()!=null)c.setStatus(in.status());}
}