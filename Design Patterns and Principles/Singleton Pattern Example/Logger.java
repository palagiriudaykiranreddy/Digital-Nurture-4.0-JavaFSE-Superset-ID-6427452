public class Logger {
    // Private static instance of the Logger class
    private static Logger instance;
    
    // Private constructor to prevent instantiation from other classes
    private Logger() {
        System.out.println("Logger instance created");
    }
    
    // Public static method to get the instance of Logger
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    // Example logging method
    public void log(String message) {
        System.out.println("Log: " + message);
    }
} 