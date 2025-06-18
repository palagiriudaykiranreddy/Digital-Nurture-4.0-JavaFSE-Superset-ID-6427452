public class DocumentManagementTest {
    public static void main(String[] args) {
        // Create factories for different document types
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        // Create and test Word document
        System.out.println("\nTesting Word Document:");
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();

        // Create and test PDF document
        System.out.println("\nTesting PDF Document:");
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.close();

        // Create and test Excel document
        System.out.println("\nTesting Excel Document:");
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.save();
        excelDoc.close();

        // Demonstrate using the template method
        System.out.println("\nTesting Template Method:");
        wordFactory.openDocument();
        pdfFactory.openDocument();
        excelFactory.openDocument();
    }
} 