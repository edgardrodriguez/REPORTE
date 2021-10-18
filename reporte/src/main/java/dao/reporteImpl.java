/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static com.ibm.java.diagnostics.utils.Context.logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import modelo.reporteModel;

/**
 *
 * @author EDGARD
 */
public class reporteImpl extends Conexion{
    public List<reporteModel> listarFecha() throws Exception {
        List<reporteModel> lisFech = null;
        reporteModel fech;
        ResultSet rs;
        String sql = "select TO_CHAR( fecha, 'dd/MM/YY' )as fecha from v_fechaLibro";
        try {
            lisFech = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                fech = new reporteModel();
                fech.setFecha(rs.getString("fecha"));
                lisFech.add(fech);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al listar fecha cuot {0}", e.getMessage());
        }
        return lisFech;
    }
}
