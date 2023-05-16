package cl.usach.mingeso.proyectomingeso1.Services;

import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.QuincenaEntity;
import cl.usach.mingeso.proyectomingeso1.Repositories.QuincenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuincenaService {

    @Autowired
    QuincenaRepository quincenaRepository;

    public List<QuincenaEntity> obtenerQuincenas(){ return quincenaRepository.findAll(); }


    //Como obtener la variacion porcentual de los kilos de un proveedor de la quincena actual respecto
    //a la quincena anterior
    public double obtenerVariacionKilos(ProveedorEntity proveedor){
        List<QuincenaEntity> quincenas = quincenaRepository.findQuincenaEntityByProveedorOrderByFechaDesc(proveedor);
        if(quincenas.size() > 1){
            double kilosActual = quincenas.get(0).getKilos();
            double kilosAnterior = quincenas.get(1).getKilos();
            double variacionKilos = (kilosActual - kilosAnterior)/kilosAnterior;
            return variacionKilos;
        }
        else{
            return 0;
            }
        }

    //Como obtener la variacion porcentual de grasa de un proveedor de la quincena actual respecto
    //a la quincena anterior

    public double obtenerVariacionGrasa(ProveedorEntity proveedor){
        List<QuincenaEntity> quincenas = quincenaRepository.findQuincenaEntityByProveedorOrderByFechaDesc(proveedor);
        if(quincenas.size() > 1){
            double grasaActual = quincenas.get(0).getPorcentajeGrasa();
            double grasaAnterior = quincenas.get(1).getPorcentajeGrasa();
            double variacionGrasa = (grasaActual - grasaAnterior)/grasaAnterior;
            return variacionGrasa;
        }
        else{
            return 0;
        }
    }

    //Como obtener la variacion porcentual de solidos totales de un proveedor de la quincena actual respecto
    //a la quincena anterior

    public double obtenerVariacionSolidos(ProveedorEntity proveedor){
        List<QuincenaEntity> quincenas = quincenaRepository.findQuincenaEntityByProveedorOrderByFechaDesc(proveedor);
        if(quincenas.size() > 1){
            double solidosActual = quincenas.get(0).getPorcentajeSolidosTotales();
            double solidosAnterior = quincenas.get(1).getPorcentajeSolidosTotales();
            double variacionSolidos = (solidosActual - solidosAnterior)/solidosAnterior;
            return variacionSolidos;
        }
        else{
            return 0;
        }
    }

}
