package cl.usach.mingeso.proyectomingeso1.Repositories;

import cl.usach.mingeso.proyectomingeso1.Entities.PlanillaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanillaRepository extends JpaRepository<PlanillaEntity, Integer> {

}
