package cl.usach.mingeso.proyectomingeso1;

import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProveedorServiceTests {

    @Autowired
    ProveedorService proveedorService;

    @Test
    void obtenerProveedoresTest1(){
        assertNotNull(proveedorService.obtenerProveedores());
    }

    @Test
    void guardarProveedorTest1(){
        proveedorService.guardarProveedor("00004", "ProveedorX", "D","Si");
        assertNotNull(proveedorService.obtenerProveedores());
        proveedorService.eliminarProveedor("00004");
    }
}
