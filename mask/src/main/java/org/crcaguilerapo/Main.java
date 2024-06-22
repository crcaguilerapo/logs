package org.crcaguilerapo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("username:12345");
        logger.info("password:secretPassword");
        logger.info("username:1111111111");
        logger.info("password:{}", 12345);
        logger.info("username:{}", "secretPassword");

    }
}