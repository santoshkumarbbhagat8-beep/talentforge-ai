package ai.talentforge.service;
import ai.talentforge.model.Candidate;
import ai.talentforge.model.Job;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class MatchService {
 public int score(Candidate candidate, Job job) {
  Set<String> candidateSkills=split(candidate.getSkills());
  Set<String> required=split(job.getRequiredSkills());
  if(required.isEmpty()) return 0;
  long matched=required.stream().filter(candidateSkills::contains).count();
  int skillScore=(int)Math.round(100.0*matched/required.size());
  int experience=candidate.getExperienceYears()==null?0:candidate.getExperienceYears();
  int min=job.getMinExperienceYears()==null?0:job.getMinExperienceYears();
  int experienceScore=min==0?100:Math.min(100,(int)(100.0*experience/min));
  return (int)Math.round(skillScore*0.8+experienceScore*0.2);
 }
 private Set<String> split(String value) {
  if(value==null||value.isBlank()) return Set.of();
  return Arrays.stream(value.toLowerCase(Locale.ROOT).split("[,;]"))
   .map(String::trim).filter(s->!s.isBlank()).collect(Collectors.toSet());
 }
}