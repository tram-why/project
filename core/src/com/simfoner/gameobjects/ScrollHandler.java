package com.simfoner.gameobjects;

/**
 * Created by Valentin on 29.01.2017.
 */

public class ScrollHandler {
    private Pipe pipe1, pipe2, pipe3;

    public static final int SCROLL_SPEED = -80;
    public static final int PIPE_GAP = 220;

    public ScrollHandler(float yPos) {
        pipe1 = new Pipe(210, 0, 22, 400, SCROLL_SPEED);
        pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 460, SCROLL_SPEED);
        pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 0, 22, 360, SCROLL_SPEED);
    }

    public void update(float delta) {
        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);

        if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe3.getTailX() + PIPE_GAP);
        } else if (pipe2.isScrolledLeft()) {
            pipe2.reset(pipe1.getTailX() + PIPE_GAP);

        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_GAP);
        }
    }

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }
}
