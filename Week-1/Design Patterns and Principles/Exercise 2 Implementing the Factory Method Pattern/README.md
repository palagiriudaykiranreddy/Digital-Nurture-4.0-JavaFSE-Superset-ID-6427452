# Factory Method Pattern Example

This project demonstrates the implementation of the Factory Method design pattern for a document management system.

## Project Structure
- `Document.java`: Interface defining document operations
- `WordDocument.java`, `PdfDocument.java`, `ExcelDocument.java`: Concrete document classes
- `DocumentFactory.java`: Abstract factory class
- `WordDocumentFactory.java`, `PdfDocumentFactory.java`, `ExcelDocumentFactory.java`: Concrete factory classes
- `DocumentManagementTest.java`: Test class demonstrating the pattern

## How to Run
1. Compile all Java files:
   ```
   javac *.java
   ```

2. Run the test class:
   ```
   java DocumentManagementTest
   ```

## Expected Output
The program will demonstrate:
1. Creation and usage of different document types (Word, PDF, Excel)
2. Each document's operations (open, save, close)
3. Usage of the template method pattern combined with factory method

## Implementation Details
- Uses Factory Method Pattern to create different types of documents
- Each document type has its own factory
- Includes a template method (openDocument) in the abstract factory
- Demonstrates both direct usage of factory method and template method
- All document operations are properly encapsulated 