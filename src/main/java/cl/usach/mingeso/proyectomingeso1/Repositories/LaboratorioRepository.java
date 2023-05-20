package cl.usach.mingeso.proyectomingeso1.Repositories;

import cl.usach.mingeso.proyectomingeso1.Entities.LaboratorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratorioRepository extends JpaRepository<LaboratorioEntity, Integer> {
    LaboratorioEntity findByProveedor(String codigo);

    @Query("SELECT l.porcentajeGrasa FROM LaboratorioEntity l WHERE l.proveedor = :codigoProveedor")
    List<Double> findPorcGrasaByProveedor(@Param("codigoProveedor") String codigoProveedor);

    @Query("SELECT e.porcSolidosTotales FROM LaboratorioEntity e WHERE e.proveedor = :codigoProveedor")
    List<Double> findPorcSolidosTotalesByProveedor(@Param("codigoProveedor") String codigoProveedor);

}
