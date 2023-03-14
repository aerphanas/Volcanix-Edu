package org.acme.Service;

import java.sql.Date;
import java.time.LocalDate;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import org.acme.Model.Kebun;
import org.jboss.logging.Logger;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class WeeklyService {
  
  private static final Logger LOG = Logger.getLogger(ReportService.class);

  @Transactional
  @Scheduled(cron = "0 0 0 ? * SUN")
  public void weekReport() {

    String komoditas = "tomat";
    Integer total = 500;
    // mendapatkan tanggal sekarang dan 
    // parsing ke format date untuk sql
    LocalDate localDate = LocalDate.now();
    Date sqlDate = Date.valueOf(localDate);

    LOG.info("creating weekly report");
    Kebun inputKebun = new Kebun.KebunBuilder()
                                .addKomoditas(komoditas)
                                .addTotal(total)
                                .addCreated(sqlDate)
                                .addUpdated(sqlDate)
                                .build();
    if ( Kebun.find("created", sqlDate).firstResult() == null ) {
      inputKebun.persist();
      LOG.info("POST Ok");
    } else {
      LOG.error("POST Err");
    }
  }
}
