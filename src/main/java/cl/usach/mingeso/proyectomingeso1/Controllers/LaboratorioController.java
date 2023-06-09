package cl.usach.mingeso.proyectomingeso1.Controllers;

import cl.usach.mingeso.proyectomingeso1.Entities.LaboratorioEntity;
import cl.usach.mingeso.proyectomingeso1.Services.LaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/laboratorio")
public class LaboratorioController {

    @Autowired
    LaboratorioService laboratorioService;

    @GetMapping("/fileUpload")
    public String main() { return "lab-file-upload"; }

    @PostMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        laboratorioService.guardarArchivo(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        laboratorioService.leerCsv("Laboratorio.csv");
        return "redirect:/laboratorio/fileUpload";
    }

    @GetMapping("/fileInformation")
    public String listar(Model model) {
        List<LaboratorioEntity> datas = laboratorioService.obtenerDataLab();
        model.addAttribute("datas", datas);
        return "lab-file-information";
    }
}
