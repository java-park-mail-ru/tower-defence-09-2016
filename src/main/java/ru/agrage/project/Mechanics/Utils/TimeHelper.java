package ru.agrage.project.Mechanics.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dmitry on 1/13/17.
 */

public class TimeHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeHelper.class);

    private TimeHelper() { }

    public static void sleep(long period){
        if (period <= 0) {
            return;
        }
        try{
            Thread.sleep(period);
        } catch (InterruptedException e) {
            LOGGER.debug("[Interrupted]");
        }
    }
}
