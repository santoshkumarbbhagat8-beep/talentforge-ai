package ai.talentforge.controller;
import ai.talentforge.model.*;
import ai.talentforge.repository.*;
import ai.talentforge.service.MatchService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;
@RestController @RequestMapping("/api/matches")
public class MatchController {
 private final CandidateRepository candidates;private final JobRepository jobs;private final MatchService matcher;
 public MatchController(CandidateRepository c,JobRepository j,MatchService m){candidates=c;jobs=j;matcher=m;}
 public record MatchResult(Candidate candidate,int score){}
 @GetMapping("/jobs/{jobId}") public List<MatchResult> forJob(@PathVariable Long jobId){
  Job job=jobs.findById(jobId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Job not found"));
  return candidates.findAll().stream().map(c->new MatchResult(c,matcher.score(c,job))).sorted(Comparator.comparingInt(MatchResult::score).reversed()).toList();
 }
}