package com.raven.swing.table;

import com.raven.model.User;
public class ModelAction {

    

    public ModelAction() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(User user, EventAction event) {
        this.user = user;
        this.event = event;
    }

    private User user;
    private EventAction event;
}
