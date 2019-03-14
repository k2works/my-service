package my.service.repository;

import my.service.model.MsgData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgDataRepository extends JpaRepository<MsgData, Long> {
}
