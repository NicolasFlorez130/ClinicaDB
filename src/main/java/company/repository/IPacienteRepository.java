package company.repository;

import company.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query("SELECT p FROM Pacientes p WHERE dni = ?1")
    Optional<Paciente> findByDni(int dni);

    @Modifying
    @Query("DELETE FROM Pacientes p WHERE dni = ?1")
    void deleteByDni(int dni);
}
