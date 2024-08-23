package org.example.statecontroller;

public class Context {
    private State currentState;

    public void setCurrentState(State state) {
        this.currentState = state;
    }



    public State getCurrentState() {
        return currentState;
    }

    public void handleInput() {
        if (currentState != null) {
            currentState.handleInput();
        }
    }

    public boolean isBack(String input) {
        return "back".equalsIgnoreCase(input.trim());
    }

    public boolean isExit(String input) {
        return "exit".equalsIgnoreCase(input.trim());
    }


    public void filterState(String input) {
        if(isExit(input)) {
            this.setCurrentState(new ExitState(this));
            this.handleInput();
        }
    }

    public boolean getIt() {
        return true;
    }
}