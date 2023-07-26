package Integration.IntegrateOpenAI.repository;

import Integration.IntegrateOpenAI.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatGptRepository extends JpaRepository<Chat,Long> {

}
