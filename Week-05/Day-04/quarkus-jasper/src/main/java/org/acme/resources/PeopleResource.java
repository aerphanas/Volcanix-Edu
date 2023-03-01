package org.acme.resources;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.acme.entity.People;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Path("/report")
public class PeopleResource {
    
    People peeps = new People();

    @GET
    public Response genReport() {
        final InputStream reportStream = getClass().getResourceAsStream("/Report/people.jrxml");
        try {
            JasperReport report = JasperCompileManager.compileReport(reportStream);
            JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(People.listAll());
            Map<String, Object> param = new HashMap<>();
            param.put("Created by", "Adivin");


            JasperPrint print = JasperFillManager.fillReport(report, param, data);
            JasperExportManager.exportReportToPdfFile(print, "/home/adivin/Report.pdf");

        } catch (JRException e) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}
