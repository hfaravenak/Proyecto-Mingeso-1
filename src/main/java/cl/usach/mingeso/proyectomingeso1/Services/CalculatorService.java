package cl.usach.mingeso.proyectomingeso1.Services;

import cl.usach.mingeso.proyectomingeso1.Entities.QuincenaEntity;
import cl.usach.mingeso.proyectomingeso1.Repositories.AcopioRepository;
import cl.usach.mingeso.proyectomingeso1.Repositories.LaboratorioRepository;
import cl.usach.mingeso.proyectomingeso1.Repositories.ProveedorRepository;
import cl.usach.mingeso.proyectomingeso1.Repositories.QuincenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CalculatorService {

    @Autowired
    QuincenaRepository quincenaRepository;

    @Autowired
    AcopioRepository acopioRepository;

    @Autowired
    LaboratorioRepository laboratorioRepository;

    @Autowired
    ProveedorService proveedorService;

    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    QuincenaService quincenaService;

    public int pagoCategoria(String categoria, int kilos) {
        return switch (categoria) {
            case "A" -> (700 * kilos);
            case "B" -> (550 * kilos);
            case "C" -> (400 * kilos);
            case "D" -> (250 * kilos);
            default -> 0;
        };
    }

}