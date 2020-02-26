package com.walkgis.tiles.util;

import com.walkgis.tiles.entity.ProgressModelProperty;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProgressBar {
    private final Logger logger = LoggerFactory.getLogger(ProgressBar.class);

    private double STEMP;
    private int total_items;
    private int nb_items_done;
    private int current_progress;
    private ProgressModelProperty modelProperty;

    public ProgressBar(int total_items, ProgressModelProperty modelProperty) {
        this.total_items = total_items;
        this.nb_items_done = 0;
        this.current_progress = 0;
        this.STEMP = 2.5;
        this.modelProperty = modelProperty;
    }

    public void start() {
        Platform.runLater(() -> modelProperty.setValue(0));
        logger.debug("0");
    }

    public void log_progress() {
        this.nb_items_done += 1;
        double progress = (double)this.nb_items_done / this.total_items * 100.0;
        if (progress >= (this.current_progress + this.STEMP)) {
            boolean done = false;
            while (!done) {
                if (this.current_progress + this.STEMP <= progress) {
                    this.current_progress += this.STEMP;
                    if (this.current_progress % 10 == 0) {
                        Platform.runLater(() -> modelProperty.setValue(this.current_progress));
                        if (this.current_progress == 100) {
                            logger.debug("\n");
                        }
                    } else {
                        logger.debug(".");
                    }
                } else done = true;
            }
        }
    }

    public void updateTop(String s) {

    }
}
