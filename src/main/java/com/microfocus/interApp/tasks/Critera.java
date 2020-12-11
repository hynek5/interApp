package com.microfocus.interApp.tasks;

import com.microfocus.interApp.domain.Task;

public interface Critera {

    boolean[] generateKey(Task task);

    boolean compareKeys(boolean[] generatedKey);
    void updateCriteria(Task task);
}
