package cl.usach.mingeso.proyectomingeso1.Repositories;

import cl.usach.mingeso.proyectomingeso1.Entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcopioRepository extends JpaRepository<AcopioEntity, Integer> {
}
