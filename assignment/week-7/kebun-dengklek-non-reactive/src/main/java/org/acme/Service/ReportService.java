package org.acme.Service;

import org.acme.Model.Kebun; 

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.scheduler.Scheduled;

import io.smallrye.mutiny.Uni;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ApplicationScoped
public class ReportService {

  @Inject
  Mailer mailer;

  private static final Logger LOG = Logger.getLogger(ReportService.class);

  private void genHasilReport() {

    // mengambil template
    final InputStream reportStream = getClass().getResourceAsStream("/Template/Report.jrxml");
    try {
      List<PanacheEntityBase> entities = Kebun.listAll();
      JasperReport report = JasperCompileManager.compileReport(reportStream);
      JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(entities);
      Map<String, Object> param = new HashMap<>();

      JasperPrint print = JasperFillManager.fillReport(report, param, data);
      JasperExportManager.exportReportToPdfFile(print, "Report.pdf");

      LOG.info("Success create report");
    } catch (JRException e) {
      LOG.error(e.getMessage());
    }

  }

  // berjalan setiap tanggal 30
  @Scheduled(cron = "0 0 0 30 * ?")
  public void sendEmailUsingReactiveMailer() {

    LOG.info("Creating Report..");
    genHasilReport();

    LOG.info("Sending Email...");
    mailer.send(Mail.withText("muhamadaviv14@gmail.com",
                    "Report Hasil Panen Akhir Bulan",
                    "Report Hasil Panen"
                ).addAttachment("Report-Hasil-Panen", new File("Report.pdf"), "application/pdf")
            );

  }

}
