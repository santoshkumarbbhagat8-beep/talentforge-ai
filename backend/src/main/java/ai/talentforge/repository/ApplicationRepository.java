package ai.talentforge.repository;
import ai.talentforge.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ApplicationRepository extends JpaRepository<JobApplication,Long> {
 List<JobApplication> findByJobId(Long jobId);
 List<JobApplication> findByCandidateId(Long candidateId);
 boolean existsByCandidateIdAndJobId(Long candidateId,Long jobId);
}