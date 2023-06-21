package com.raven.swing.table;

import com.raven.model.User;

public interface EventAction {

    public void delete(User student);

    public void update(User student);
}
