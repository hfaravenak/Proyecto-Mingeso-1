package cl.usach.mingeso.proyectomingeso1;

import cl.usach.mingeso.proyectomingeso1.Entities.AcopioEntity;
import cl.usach.mingeso.proyectomingeso1.Services.AcopioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AcopioServiceTests {

    @Autowired
    AcopioService acopioService;


    @Test
    void obtenerAcopiosTest1(){
        assertNotNull(acopioService.obtenerAcopios());
    }

    @Test
    void guardarDatosAcopiosTest1(){
        AcopioEntity acopio = new AcopioEntity();
        acopio.setProveedor("00001");
        acopio.setKls_leche("100");
        acopio.setTurno("M");
        acopio.setFecha("2021/06/01");
        acopioService.guardarDatosAcopios(acopio);
        assertNotNull(acopioService.obtenerAcopios());
        acopioService.eliminarAcopio("00001");
    }

    @Test
    void guardarDataDBTest1(){
        acopioService.guardarDataDB("2023/01/01", "M", "00002", "100");
        assertNotNull(acopioService.obtenerAcopios());
        acopioService.eliminarAcopio("00001");
    }

}


