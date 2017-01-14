package ru.agrage.project.Mechanics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agrage.project.Mechanics.Utils.TimeHelper;
import ru.agrage.project.Service.UserService;
import ru.agrage.project.Websocket.RemoteService;

import javax.annotation.PostConstruct;
import java.time.Clock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Service
public class MechanicsExecutor {

//    private static final long STEP_TIME = 30;
//    private static final int THREADS_NUM = 3;
//
//    @Autowired
//    private UserService userService;
//
//    // Массив
//    private final GameMechanics[] gameMechanics = new GameMechanics[THREADS_NUM];
//
//    @Autowired
//    private RemoteService remotePointService;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private final ThreadFactory threadFactory = new ThreadFactory(){
//        @Override
//        public Thread newThread(Runnable r) {
//            final Thread thread = new Thread(r);
//            thread.setName("Game");
//            return thread;
//        };
//    };

//    // Асинхрон и многопоточность
//    private final ExecutorService tickExecutors = Executors.newFixedThreadPool(THREADS_NUM, threadFactory);
//
//    private static class Worker implements Runnable {
//
//        private final GameMechanics gameMechanics;
//
//        Worker(GameMechanics gameMechanics) {
//            this.gameMechanics = gameMechanics;
//        }
//
//        private final Clock clock = Clock.systemDefaultZone();
//
//        @Override
//        public void run() {
//            long lastFrameMillis = STEP_TIME;
//            while (true) {
//                final long before = clock.millis();
//
//                gameMechanics.gmStep(lastFrameMillis);
//
//                final long after = clock.millis();
//                TimeHelper.sleep(STEP_TIME - (after - before));
//
//                if (Thread.currentThread().isInterrupted()) {
//                    gameMechanics.reset();
//                    return;
//                }
//                final long afterSleep = clock.millis();
//                lastFrameMillis = afterSleep - before;
//            }
//        }
//    }
//
//    @PostConstruct
//    public void initAfterStartup() {
//        for (int i = 0; i < gameMechanics.length; ++i) {
//            gameMechanics[i] = new GameMechanics(userService,
//                    serverSnapshotService, remotePointService, clientSnapshotsService, objectMapper);
//            final Runnable worker = new Worker(gameMechanics[i]);
//            tickExecutors.execute(worker);
//        }
//    }

//
//    public void addUser (long user) {
//        for (GameMechanics gameMechanic: gameMechanics) {
//            final boolean hasSlots = gameMechanic.hasFreeSlots();
//            if (hasSlots) {
//                gameMechanic.addUser(user);
//                return;
//            }
//        }
//
//        int hasLessSessions = 0;
//        int currentMin = gameMechanics[0].getSessionsNum();
//        for (int i = 1; i < gameMechanics.length; ++i) {
//            if (gameMechanics[i].getSessionsNum() < currentMin) {
//                currentMin = gameMechanics[i].getSessionsNum();
//                hasLessSessions = i;
//            }
//        }
//        gameMechanics[hasLessSessions].addUser(user);
//    }
//
//    public void addClientSnapshot(long forUser, UserSnap message) {
//        for (GameMechanics gameMechanic: gameMechanics) {
//            if (gameMechanic.isPlaying(forUser)) {
//                gameMechanic.addClientSnapshot(forUser, message);
//                return;
//            }
//        }
//    }
//
//    public void removeUser(long user) {
//        int i = 0;
//        while (i < gameMechanics.length && !gameMechanics[i].removeUser(user)) {
//            i++;
//        }
//    }
}
