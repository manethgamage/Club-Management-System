package jasper;



import com.example.oopp.Event;
import com.example.oopp.ScheduleActivity;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventScheduleReport {

    public void generateAttendanceReport(List<Event> allAttendanceData) {
        try {
            // Load JRXML template
            String reportTemplatePath = "src/main/resources/com/example/oopp/clubevent.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplatePath);

            // Fill the report with data
            Map<String, Object> parameters = new HashMap<>();
            // parameters
            parameters.put("ReportTitle", "Event Report");

            // Convert the entire attendance data list to a JRDataSource
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allAttendanceData);

            // Create JasperPrint (filled report)
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Show the report using JasperViewer
            JasperViewer.viewReport(jasperPrint, false);

            System.out.println("Report displayed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error displaying the report.");
        }
    }
}
