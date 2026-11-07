package ai.talentforge.controller;
import ai.talentforge.model.Job;
import ai.talentforge.repository.JobRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/jobs")
public class JobController {
 private final JobRepository repo;
 public JobController(JobRepository repo){this.repo=repo;}
 public record JobInput(@NotBlank String title,@NotBlank String department,String location,String description,@NotBlank String requiredSkills,@Min(0) Integer minExperienceYears,String employmentType,String status){}
 @GetMapping public List<Job> all(@RequestParam(required=false) String status){return status==null?repo.findAll():repo.findByStatus(status.toUpperCase());}
 @GetMapping("/{id}") public Job get(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Job not found"));}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Job create(@Valid @RequestBody JobInput in){Job j=new Job();apply(j,in);return repo.save(j);}
 @PutMapping("/{id}") public Job update(@PathVariable Long id,@Valid @RequestBody JobInput in){Job j=get(id);apply(j,in);return repo.save(j);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){repo.delete(get(id));}
 private void apply(Job j,JobInput in){j.setTitle(in.title());j.setDepartment(in.department());j.setLocation(in.location());j.setDescription(in.description());j.setRequiredSkills(in.requiredSkills());j.setMinExperienceYears(in.minExperienceYears());if(in.employmentType()!=null)j.setEmploymentType(in.employmentType());if(in.status()!=null)j.setStatus(in.status().toUpperCase());}
}