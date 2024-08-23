package org.example.statecontroller;

import java.util.logging.Logger;

public class ExitState implements State{
    private Context context;
    private Logger logger = Logger.getLogger(ExitState.class.getName());
    public ExitState(Context context){
        this.context = context;
    }
    @Override
    public void handleInput() {
        logger.info("Exiting the application...");
    }
}
