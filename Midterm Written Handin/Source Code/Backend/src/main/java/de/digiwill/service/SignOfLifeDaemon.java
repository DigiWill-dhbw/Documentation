package de.digiwill.service;

import de.digiwill.controller.GreetingController;
import de.digiwill.model.BaseAction;
import de.digiwill.model.UserHandle;
import de.digiwill.model.UserHandleManager;
import de.digiwill.repository.UserHandleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class SignOfLifeDaemon {

    /**
     * Check interval in minutes
     */
    public static final int checkInterval = 10;

    @Autowired
    UserHandleRepository userHandleRepository;

    @Autowired
    UserHandleManager userHandleManager;

    private boolean running = false;
    /**
     * Progress in percent
     */
    private float progress;

    private Logger logger = LoggerFactory.getLogger(SignOfLifeDaemon.class);

    @Async
    @Scheduled(fixedRate = checkInterval * 60000)
    public void check() {
        running = true;
        long currentTime = System.currentTimeMillis() / 1000L;
        ;
        List<UserHandle> userHandles = userHandleRepository.findAll();
        float amountOfUsersPerc = (float) userHandles.size() / 100;
        int processedUsers = 0;
        for (UserHandle user : userHandles) {
            if (!user.areAllActionsCompleted() && user.getLastSignOfLife() + user.getDeltaDeathTime() >= currentTime) {
                user.setDead(true);
                List<BaseAction> actions = user.getActions();
                boolean allCompleted = true;
                for (BaseAction action : actions) {
                    if (!action.wasCompleted()) {
                        allCompleted = allCompleted && action.execute().wasSuccessful();
                    }
                }
                user.setAllActionsCompleted(allCompleted);
                userHandleManager.updateUser(user);
            }
            processedUsers++;
            progress = processedUsers / amountOfUsersPerc;
            logger.debug("Progress: " + progress);
        }
        logger.info("Check finished in: " + ((System.currentTimeMillis() / 1000L) - currentTime));
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public float getProgress() {
        return progress;
    }
}