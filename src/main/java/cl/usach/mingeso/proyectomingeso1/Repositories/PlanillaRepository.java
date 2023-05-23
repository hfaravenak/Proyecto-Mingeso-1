package cl.usach.mingeso.proyectomingeso1.Repositories;

import cl.usach.mingeso.proyectomingeso1.Entities.PlanillaEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanillaRepository extends JpaRepository<PlanillaEntity, Integer> {

    List<PlanillaEntity> findByCodigoProveedor(String codigoProveedor);


    /*
    @Query("select e from PlanillaEntity e where e.codigoProveedor = :codigo")
    PlanillaEntity findByCodigoProveedor(@Param("codigo")String codigo);

     */

}
