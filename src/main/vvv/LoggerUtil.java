package vvv;

import java.util.logging.*;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger("Logger");

    static {
        logger.setLevel(Level.ALL);
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);
    }

    public static Logger getLogger() {
        return logger;
    }
}