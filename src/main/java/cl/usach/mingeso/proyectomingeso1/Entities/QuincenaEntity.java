package cl.usach.mingeso.proyectomingeso1.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "quincena")
@NoArgsConstructor
@AllArgsConstructor
public class QuincenaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private  Integer ID;

    private Integer quincena;

    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "codigo")
    private ProveedorEntity proveedor;

    private Double kilos;

    private Integer diasAcopioMT;

    private Integer diasAcopioM;

    private Integer diasAcopioT;

    private Double porcentajeGrasa;

    private Double porcentajeSolidosTotales;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getQuincena() {
        return quincena;
    }

    public void setQuincena(Integer quincena) {
        this.quincena = quincena;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

    public Double getKilos() {
        return kilos;
    }

    public void setKilos(Double kilos) {
        this.kilos = kilos;
    }

    public Integer getDiasAcopioMT() {
        return diasAcopioMT;
    }

    public void setDiasAcopioMT(Integer diasAcopioMT) {
        this.diasAcopioMT = diasAcopioMT;
    }

    public Integer getDiasAcopioM() {
        return diasAcopioM;
    }

    public void setDiasAcopioM(Integer diasAcopioM) {
        this.diasAcopioM = diasAcopioM;
    }

    public Integer getDiasAcopioT() {
        return diasAcopioT;
    }

    public void setDiasAcopioT(Integer diasAcopioT) {
        this.diasAcopioT = diasAcopioT;
    }

    public Double getPorcentajeGrasa() {
        return porcentajeGrasa;
    }

    public void setPorcentajeGrasa(Double porcentajeGrasa) {
        this.porcentajeGrasa = porcentajeGrasa;
    }

    public Double getPorcentajeSolidosTotales() {
        return porcentajeSolidosTotales;
    }

    public void setPorcentajeSolidosTotales(Double porcentajeSolidosTotales) {
        this.porcentajeSolidosTotales = porcentajeSolidosTotales;
    }
}
