package org.example.StateController;

public class Context {
    private State currentState;
    private final static String exit = "EXIT";
    private final static String back = "BACK";

    public void setCurrentState(State currentState){
        this.currentState = currentState;
    }

    public void handleInput(){
        currentState.handleInput();
    }

    public boolean isBack(String s){
        return s.toUpperCase().equals(back);
    }

    public boolean isExit(String s){
        return s.toUpperCase().equals(exit);
    }
    public State getCurrentState(){
        return currentState;
    }
}
