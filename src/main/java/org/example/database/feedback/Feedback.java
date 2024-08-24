package org.example.database.feedback;

public class Feedback {
    private String id;
    private String userName;
    private String message;
    private boolean responded;

    public Feedback(String id, String userName, String message) {
        this.id = id;
        this.userName = userName;
        this.message = message;
        this.responded = false;
    }

    public String getId() {
        return id;
    }
    
    public void setResponded(boolean responded) {
        this.responded = responded;
    }

    @Override
    public String toString() {
        return "Feedback [ID =" + id + ", UserName =" + userName + ", Message =" + message + ", Responded =" + responded + "]";
    }
}
