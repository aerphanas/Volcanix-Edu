package org.acme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;

@Path("/poi")
public class GreetingResource {

    @GET
    public void hello() throws IOException {
        FileInputStream file = new FileInputStream(new File("test.xlsx"));

        // Membuat objek workbook dari file
        Workbook workbook = new XSSFWorkbook(file);

        // Mengambil sheet pertama dari workbook
        Sheet sheet = workbook.getSheet("shit 2");

        // Mengubah nilai di sel (1,1)
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("Hello World");

        // Menyimpan perubahan
        FileOutputStream fileOut = new FileOutputStream("test.xlsx");
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();

        // Menutup file
        file.close();

        Log.info("normal");

    }

    @GET
    @Path("uni")
    public Uni<Response> unithread() {
        return Uni.createFrom().item(() -> {
            try {
                FileInputStream file = new FileInputStream(new File("test.xlsx"));
                // Membuat objek workbook dari file
                Workbook workbook = new XSSFWorkbook(file);
                // Mengambil sheet pertama dari workbook
                Sheet sheet = workbook.getSheet("sheet 2");
                // Mengubah nilai di sel (1,1)
                Row row = sheet.createRow(1);
                Cell cell = row.createCell(1);
                cell.setCellValue("Hello World");
                // Menyimpan perubahan
                FileOutputStream fileOut = new FileOutputStream("test.xlsx");
                workbook.write(fileOut);
                workbook.close();
                fileOut.close();
                file.close();
                // Menutup file
                Log.info("uni");
                return Response.ok().build();
            } catch (IOException e) {
                return Response.serverError().build();
            }
        });
    }

}