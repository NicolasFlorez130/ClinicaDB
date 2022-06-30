package company.repository;

import company.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {

    @Query("SELECT o FROM Odontologos o WHERE matricula = ?1")
    Optional<Odontologo> findByMatricula(int matricula);

    @Modifying
    @Query("DELETE FROM Odontologos o WHERE matricula = ?1")
    void deleteByMatricula(int matricula);
}
