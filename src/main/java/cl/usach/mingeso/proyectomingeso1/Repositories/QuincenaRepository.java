package cl.usach.mingeso.proyectomingeso1.Repositories;

import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.QuincenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuincenaRepository extends JpaRepository<QuincenaEntity, Integer> {
    List<QuincenaEntity> findQuincenaEntityByProveedorOrderByFechaDesc(ProveedorEntity proveedor);

}
