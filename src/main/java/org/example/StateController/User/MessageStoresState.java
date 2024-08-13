package org.example.StateController.User;

import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

public class MessageStoresState implements State {
    private Context context;

    public MessageStoresState(Context context) {
        this.context = context;
    }


    @Override
    public void handleInput() {

    }

    private void filterState(String input){
        if(context.isBack(input)){
            context.setCurrentState(new UserState(context));
            context.handleInput();
        }
        else if(context.isExit(input)){
            context.setCurrentState(new ExitState(context));
            context.handleInput();
        }
    }
}
