package backend.ecommerce.ecommerceapi.service.pdf;

public interface PdfService {

    public void createPdf();
    public void openDocument();
    public void addTitle(String title);
    public void addParagraph(String paragraph);
    public void jumpLine();
    public void addBookingsTable();
    public void closeDocument();

}
