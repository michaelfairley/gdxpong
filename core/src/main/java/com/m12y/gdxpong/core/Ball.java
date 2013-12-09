package com.m12y.gdxpong.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    private static final int SIZE = 12;

    final Vector2 position;
    final Vector2 velocity;

    public Ball() {
        position = new Vector2(PongGame.WIDTH/2, PongGame.HEIGHT/2);
        velocity = new Vector2(0, 5);
        velocity.rotate(MathUtils.random(30, 150));
        if (MathUtils.randomBoolean()) velocity.rotate(180);
    }

    public void draw() {
        PongGame.shapeRenderer.setColor(Color.RED);
        PongGame.shapeRenderer.rect(position.x, position.y, SIZE, SIZE);
    }

    public void move() {
        position.add(velocity);

        if (position.y + SIZE > PongGame.HEIGHT) {
            position.y = PongGame.HEIGHT - SIZE;
            velocity.scl(1, -1);
        }

        if (position.y < 0) {
            position.y = 0;
            velocity.scl(1, -1);
        }
    }

    public boolean isOffLeft() {
        return position.x < 0;
    }


    public boolean isOffRight() {
        return position.x + SIZE > PongGame.WIDTH;
    }

    public Rectangle rect() {
        return new Rectangle(position.x, position.y, SIZE, SIZE);
    }

    public float yCenter() {
        return position.y + SIZE/2;
    }

    public void bounceOffPaddle(Paddle paddle) {
        switch (paddle.side) {
            case LEFT: position.x = paddle.position.x + Paddle.WIDTH;
                break;
            case RIGHT: position.x = paddle.position.x - SIZE;
                break;
        }

        float ratio = (yCenter() - paddle.yCenter()) / Paddle.HEIGHT;

        velocity.setAngle(120 * ratio);
        if (paddle.side == Paddle.Side.RIGHT) velocity.scl(-1, 1);

    }
}
