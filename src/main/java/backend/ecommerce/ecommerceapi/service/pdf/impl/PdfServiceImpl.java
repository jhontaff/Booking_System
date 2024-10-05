package backend.ecommerce.ecommerceapi.service.pdf.impl;

import backend.ecommerce.ecommerceapi.config.exception.PdfException;
import backend.ecommerce.ecommerceapi.entity.booking.Booking;
import backend.ecommerce.ecommerceapi.entity.booking.ExtraResource;
import backend.ecommerce.ecommerceapi.entity.room.Room;
import backend.ecommerce.ecommerceapi.service.booking.BookingService;
import backend.ecommerce.ecommerceapi.service.pdf.PdfService;
import backend.ecommerce.ecommerceapi.service.room.RoomService;
import com.itextpdf.text.Font;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PdfServiceImpl implements PdfService {

    Document document;
    FileOutputStream fileOutputStream;
    private final BookingService bookingService;
    private final RoomService roomService;

    private final Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private final Font paragraphFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
    private final Font tableFont = new Font(Font.FontFamily.TIMES_ROMAN, 10);

    public PdfServiceImpl(BookingService bookingService,
                          RoomService roomService){
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    @Override
    public void createPdf() {
        String path = System.getProperty("user.home") + "/OneDrive/Desktop/Bookings.pdf";
        try {
        document = new Document(PageSize.A4, 15, 20, 50, 50);
        fileOutputStream = new FileOutputStream(path);
        PdfWriter.getInstance(document, fileOutputStream);
        } catch (FileNotFoundException | DocumentException e) {
            throw new PdfException("Error creating pdf");
        }
    }

    @Override
    public void openDocument() {
        document.open();
    }

    @Override
    public void addTitle(String title) {
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell(new Phrase(title, titleFont));
        cell.setColspan(5);
        cell.setBorderColor(BaseColor.WHITE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        try {
            document.add(table);
        } catch (DocumentException e) {
            throw new PdfException("Error adding title to document");
        }
    }

    @Override
    public void addParagraph(String paragraph) {
        Paragraph p = new Paragraph();
        p.add(new Phrase(paragraph, paragraphFont));
        try {
            document.add(p);
        } catch (DocumentException e) {
            throw new PdfException("Error adding paragraph to document");
        }
    }

    @Override
    public void jumpLine() {
        Paragraph p = new Paragraph();
        p.add(new Phrase(Chunk.NEWLINE));
        p.add(new Phrase(Chunk.NEWLINE));
        try {
            document.add(p);
        } catch (DocumentException e) {
            throw new PdfException("Error adding paragraph to document");
        }
    }


    public PdfPCell createColoredCell(String text, BaseColor color){
        PdfPCell cell = new PdfPCell(new Phrase(text, tableFont));
        cell.setBackgroundColor(color);
        return cell;
    }

    public PdfPCell customCell(String text){
        return new PdfPCell(new Phrase(text, tableFont));
    }

    @Override
    public void addBookingsTable(){
        PdfPTable table = new PdfPTable(7);
        Color color = new Color(35, 227, 254 );
        table.addCell(createColoredCell("ID", new BaseColor(color.getRGB())));
        table.addCell(createColoredCell("User", new BaseColor(color.getRGB())));
        table.addCell(createColoredCell("Room", new BaseColor(color.getRGB())));
        table.addCell(createColoredCell("Extra Resources", new BaseColor(color.getRGB())));
        table.addCell(createColoredCell("Check In", new BaseColor(color.getRGB())));
        table.addCell(createColoredCell("Check Out", new BaseColor(color.getRGB())));
        table.addCell(createColoredCell("State", new BaseColor(color.getRGB())));

        List<Booking> bookings = this.bookingService.getAllBookings();

        for(Booking booking : bookings){
            Room room = this.roomService.getRoomById(booking.getBookingId());
            List<ExtraResource> extraResources = booking.getExtraResource();
            table.addCell(customCell(booking.getBookingId().toString()));
            table.addCell(customCell(booking.getBookedBy().getUsername() + " " + booking.getBookedBy().getLastname()));
            table.addCell(customCell(room.getRoomName()));
            table.addCell(customCell(extraResources.stream().map(
                    ExtraResource::getResourceName)
                    .collect(Collectors.joining(", "))));
            table.addCell(customCell(booking.getCheckIn().toString()));
            table.addCell(customCell(booking.getCheckOut().toString()));
            table.addCell(customCell(booking.getBookingState().getState().name()));

        }

        try {
            document.add(table);
        } catch (DocumentException e) {
            throw new PdfException("Error adding booking table");
        }
    }

    @Override
    public void closeDocument() {
        document.close();
    }
}
