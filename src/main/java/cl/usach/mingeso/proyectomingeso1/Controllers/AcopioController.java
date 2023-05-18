package cl.usach.mingeso.proyectomingeso1.Controllers;

import cl.usach.mingeso.proyectomingeso1.Entities.AcopioEntity;
import cl.usach.mingeso.proyectomingeso1.Services.AcopioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/acopio")
public class AcopioController {
    @Autowired
    AcopioService acopioService;

    @GetMapping("/fileUpload")
    public String main() {
        return "fileUpload";
    }

    @PostMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        acopioService.guardarArchivo(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        acopioService.leerCsv("Acopio.csv");
        return "redirect:/fileUpload";
    }

    @GetMapping("/fileInformation")
    public String listar(Model model) {
        List<AcopioEntity> datas = acopioService.obtenerAcopios();
        model.addAttribute("datas", datas);
        return "fileInformation";
    }
}

