package com.microfocus.interApp.tasks;

import com.microfocus.interApp.domain.Task;
import java.util.*;
import static com.microfocus.interApp.InterAppApplication.logger;

public class DefaultCriteria implements Critera {

    public Set<String> runningGid = new HashSet<>();
    public Set<Task.OperType> runningType = new HashSet<>();

    static boolean[] defaultKey;

    public DefaultCriteria() {
        //task can be run
        defaultKey = new boolean[]{true, true};

    }

    public boolean[] generateKey(Task task) {
        boolean[] generatedKey = new boolean[]{false,false};
        logger.debug("current critera GID: {}, TYPE:{}",runningGid.toString(), runningType.toString());
        if (runningGid.contains(task.getGid())) {
            //no point of carrying on the same group
            return generatedKey;
        }
        if (runningType.isEmpty()) {
            generatedKey[1] = runningType.add(task.getType());
            generatedKey[0] = runningGid.add(task.getGid());
            logger.trace("current critera GID: {}, TYPE:{}",runningGid.toString(), runningType.toString());
            return generatedKey;
        }
        if (runningType.contains(task.getType())) {
            generatedKey[1] = true;
            generatedKey[0] = runningGid.add(task.getGid());
            logger.trace("current critera GID: {}, TYPE:{}",runningGid.toString(), runningType.toString());
            return generatedKey;

        }
        return generatedKey;
    }

    public boolean compareKeys(boolean[] generatedKey) {
        return Objects.equals(defaultKey[0],generatedKey[0]) &&
                Objects.equals(defaultKey[1], generatedKey[1]);
    }


    public void updateCriteria(Task completedTask) {
        runningGid.remove(completedTask.getGid());
        runningType.remove(completedTask.getType());
        logger.trace("current critera GID: {}, TYPE:{}",runningGid.toString(), runningType.toString());
    }


}
