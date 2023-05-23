package cl.usach.mingeso.proyectomingeso1.Services;

import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public List<ProveedorEntity> obtenerProveedores(){
        return proveedorRepository.findAll();
    }
    public void guardarProveedor(String codigo, String nombre, String categoria, String retencion){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCodigo(codigo);
        proveedor.setNombre(nombre);
        proveedor.setCategoria(categoria);
        proveedor.setRetencion(retencion);
        proveedorRepository.save(proveedor);
    }

    public void eliminarProveedor(String codigo){
        ProveedorEntity proveedores = proveedorRepository.findByCodigo(codigo);
        proveedorRepository.delete(proveedores);
    }



}
