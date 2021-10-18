/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.reporteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.reporteModel;
import servicios.Reporte;

/**
 *
 * @author EDGARD
 */
@Named(value = "reporteC")
@SessionScoped
public class reporteC implements Serializable {
    private reporteModel cuot;
    private reporteImpl dao;
   private List<reporteModel> listadoFecha;
    public reporteC() {
         cuot = new reporteModel();
        dao = new reporteImpl();
    }
    public void reporteCuota() throws Exception {

        try {
            if (cuot.getFecha()== null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar la fecha en el reporte"));
            }
            if (cuot.getFecha()!= null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = cuot.getFecha();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", sts);
                report.exportarPDFGlobal(parameters, "primerReporte.jasper", fechSystem + " reporte.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }
    public List<reporteModel> getListadoFecha() {
        try {     
            listadoFecha = dao.listarFecha();
        } catch (SQLException ex) {
            Logger.getLogger(reporteC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(reporteC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoFecha;
    }

    public void setListadoFecha(List<reporteModel> listadoFecha) {
        this.listadoFecha = listadoFecha;
    }

    public reporteModel getCuot() {
        return cuot;
    }

    public void setCuot(reporteModel cuot) {
        this.cuot = cuot;
    }

    public reporteImpl getDao() {
        return dao;
    }

    public void setDao(reporteImpl dao) {
        this.dao = dao;
    }
    
}
