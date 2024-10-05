package backend.ecommerce.ecommerceapi.controller;

import backend.ecommerce.ecommerceapi.service.pdf.PdfService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/generate")
    public ResponseEntity<InputStreamResource> generatePdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        pdfService.createPdf();
        pdfService.openDocument();
        pdfService.addTitle("Booking Report");
        pdfService.addParagraph("This is a sample booking report.");
        pdfService.jumpLine();
        pdfService.addBookingsTable();
        pdfService.closeDocument();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=bookings.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }
}