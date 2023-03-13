package org.acme.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.acme.Model.Kebun;
import org.jboss.logging.Logger;

import io.quarkus.scheduler.Scheduled;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ApplicationScoped
public class ReportService {

  private static final Logger LOG = Logger.getLogger(ReportService.class);

  // @Scheduled(every="10s")   
  @Scheduled(cron = "0 0 0 30 * ?")  
  void hasilReport() {

    final InputStream reportStream = getClass().getResourceAsStream("/Template/Report.jrxml");
  
    Kebun.listAll().subscribe().with( entities -> {
        try {
          JasperReport report = JasperCompileManager.compileReport(reportStream);
          JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(entities);
          Map<String, Object> param = new HashMap<>();
  
          JasperPrint print = JasperFillManager.fillReport(report, param, data);
          JasperExportManager.exportReportToPdfFile(print, "Report.pdf");
  
          LOG.info("Success create report");
        } catch (JRException e) {
          LOG.error(e.getMessage());
        }
      },
      error -> LOG.error(error.getMessage())
    );
  }
  

}
