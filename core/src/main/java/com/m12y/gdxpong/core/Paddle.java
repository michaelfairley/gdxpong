package com.m12y.gdxpong.core;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle {
    public enum Side {
        LEFT, RIGHT
    }

    public static final int WIDTH = 12;
    public static final int HEIGHT = 64;

    Side side;
    Vector2 position;

    public Paddle(Side side) {
        this.side = side;

        position = new Vector2(this.side == Side.LEFT ? 0 : PongGame.WIDTH - WIDTH, PongGame.HEIGHT/2 - HEIGHT/2);
    }

    public void draw() {
        PongGame.shapeRenderer.setColor(1, 1, 1, 0);
        PongGame.shapeRenderer.rect(position.x, position.y, WIDTH, HEIGHT);
    }

    public void moveUp() {
        position.add(0, 6);
        if (position.y + HEIGHT > PongGame.HEIGHT) position.y = PongGame.HEIGHT - HEIGHT;
    }

    public void moveDown() {
        position.sub(0, 6);
        if (position.y < 0) position.y = 0;
    }

    public float yCenter() {
        return position.y + HEIGHT/2;
    }

    public Rectangle rect() {
        return new Rectangle(position.x, position.y, WIDTH, HEIGHT);
    }
}
