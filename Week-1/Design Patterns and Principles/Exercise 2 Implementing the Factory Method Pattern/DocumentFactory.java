public abstract class DocumentFactory {
    // Factory method
    public abstract Document createDocument();
    
    // Template method that uses the factory method
    public Document openDocument() {
        Document document = createDocument();
        document.open();
        return document;
    }
} 