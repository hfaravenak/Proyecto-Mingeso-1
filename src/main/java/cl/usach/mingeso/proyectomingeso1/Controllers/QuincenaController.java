package cl.usach.mingeso.proyectomingeso1.Controllers;

import cl.usach.mingeso.proyectomingeso1.Entities.QuincenaEntity;
import cl.usach.mingeso.proyectomingeso1.Services.QuincenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quincena")
public class QuincenaController {

    @Autowired
    QuincenaService quincenaService;

    @GetMapping("/listar_quincena")
    public List<QuincenaEntity> obtenerQuincenas() {
        return quincenaService.obtenerQuincenas();
    }
}
