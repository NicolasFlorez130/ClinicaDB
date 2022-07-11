package company.repository;

import company.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {

    @Query("SELECT t FROM Turnos t WHERE paciente_dni = ?1")
    List<Turno> findAllByDni(int dni);

    @Query("SELECT t FROM Turnos t WHERE odontologo_matricula = ?1")
    List<Turno> findAllByMatricula(int matricula);
}
