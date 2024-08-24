package org.example.statecontroller;

import java.util.logging.Logger;

public class ExitState implements State{
    private Logger logger ;
    public ExitState(){
        logger = Logger.getLogger(ExitState.class.getName());
    }
    @Override
    public void handleInput() {
        logger.info("Exiting the application...");
    }
}
