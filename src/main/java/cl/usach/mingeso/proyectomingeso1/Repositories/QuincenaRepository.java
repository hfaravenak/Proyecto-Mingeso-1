package cl.usach.mingeso.proyectomingeso1.Repositories;

import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.QuincenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuincenaRepository extends JpaRepository<QuincenaEntity, Integer> {
    List<QuincenaEntity> findQuincenaEntityByProveedorOrderByFechaDesc(ProveedorEntity proveedor);

    @Query("SELECT q FROM QuincenaEntity q WHERE q.proveedor.codigo = :codigoProveedor ORDER BY q.fecha DESC")
    List<QuincenaEntity> findAllByProveedorOrderByFechaDesc(String codigoProveedor);

}


