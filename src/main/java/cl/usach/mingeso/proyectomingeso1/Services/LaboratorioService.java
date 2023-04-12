package cl.usach.mingeso.proyectomingeso1.Services;

import cl.usach.mingeso.proyectomingeso1.Entities.AcopioEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.LaboratorioEntity;
import cl.usach.mingeso.proyectomingeso1.Repositories.LaboratorioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorioService {
    @Autowired
    LaboratorioRepository laboratorioRepository;

    private final Logger logg = LoggerFactory.getLogger(LaboratorioService.class);

    public List<LaboratorioEntity> obtenerDataLab() { return laboratorioRepository.findAll(); }

    public LaboratorioEntity guardarDataLab(LaboratorioEntity laboratorio) { return laboratorioRepository.save(laboratorio); }

}
