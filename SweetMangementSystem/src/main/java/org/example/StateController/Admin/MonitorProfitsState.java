package org.example.StateController.Admin;

import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

public class MonitorProfitsState implements State {
    private Context context;
    public MonitorProfitsState(Context context) {
            this.context = context;
    }

    @Override
    public void handleInput() {

    }

    private void filterState(String input){
        if(context.isBack(input)){
            context.setCurrentState(new AdminState(context));
            context.handleInput();
        }
        else if(context.isExit(input)){
            context.setCurrentState(new ExitState(context));
            context.handleInput();
        }
    }
}
