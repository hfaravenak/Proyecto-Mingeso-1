package cl.usach.mingeso.proyectomingeso1;
import cl.usach.mingeso.proyectomingeso1.Entities.AcopioEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.LaboratorioEntity;
import cl.usach.mingeso.proyectomingeso1.Services.LaboratorioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LaboratorioServiceTests {
    @Autowired
    LaboratorioService laboratorioService;

    @Test
    void obtenerDataLabTest1(){
        assertNotNull(laboratorioService.obtenerDataLab());
    }

    @Test
    void guardarDataLabTest1(){
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setProveedor("00003");
        laboratorio.setPorcentajeGrasa(200);
        laboratorio.setPorcSolidosTotales(300);
        laboratorioService.guardarDataLab(laboratorio);
        assertNotNull(laboratorioService.obtenerDataLab());
        laboratorioService.eliminarLaboratorio("00003");
    }

    @Test
    void guardarDataBDLabTest1(){
        laboratorioService.guardarDataDB("00001", "100", "200");
        assertNotNull(laboratorioService.obtenerDataLab());
        laboratorioService.eliminarLaboratorio("00001");
    }

}
