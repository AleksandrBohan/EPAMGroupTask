package com.epam.jwd.service.logic;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IdGenerator {

    private static Long id = 0L;
    private static final String GENERATE_ID_LOG_MESSAGE = "Generate id method works...";
    private static final Logger logger = LogManager.getLogger(IdGenerator.class);


    public static Long generateId() {
        logger.debug(GENERATE_ID_LOG_MESSAGE);
        return ++id;
    }
}
