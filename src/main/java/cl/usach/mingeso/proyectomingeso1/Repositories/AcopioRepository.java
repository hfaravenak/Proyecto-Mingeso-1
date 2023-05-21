package cl.usach.mingeso.proyectomingeso1.Repositories;

import cl.usach.mingeso.proyectomingeso1.Entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcopioRepository extends JpaRepository<AcopioEntity, Integer> {

    @Query("SELECT a.kls_leche FROM AcopioEntity a WHERE a.proveedor = :codigoProveedor")
    List<String> findKlsLecheByProveedor(@Param("codigoProveedor") String codigoProveedor);

    @Query("SELECT a.turno FROM AcopioEntity a WHERE a.proveedor = :codigoProveedor")
    List<String> findTurnoByProveedor(@Param("codigoProveedor") String codigoProveedor);

    @Query("SELECT a FROM AcopioEntity a WHERE a.proveedor = :codigo")
    List<AcopioEntity> findAllByProveedor(@Param("codigo") String codigo);

    @Query("SELECT COUNT(a) FROM AcopioEntity a WHERE a.proveedor = :codigoProveedor")
    int countByProveedor(@Param("codigoProveedor") String codigoProveedor);

}
