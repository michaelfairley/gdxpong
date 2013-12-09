package com.m12y.gdxpong.core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PongGame implements ApplicationListener {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public static ShapeRenderer shapeRenderer;
    public static SpriteBatch spriteBatch;

    private Ball ball;
    private int leftScore;
    private int rightScore;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private BitmapFont font;

    @Override
	public void create () {
        ball = new Ball();
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        leftScore = 0;
        rightScore = 0;
        font = new BitmapFont();
        leftPaddle = new Paddle(Paddle.Side.LEFT);
        rightPaddle = new Paddle(Paddle.Side.RIGHT);
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
        update();

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw();
        leftPaddle.draw();
        rightPaddle.draw();
        shapeRenderer.end();

        spriteBatch.begin();
        font.draw(spriteBatch, Integer.toString(leftScore), 20, HEIGHT - 20);
        font.draw(spriteBatch, Integer.toString(rightScore), WIDTH - 30, HEIGHT - 20);
        spriteBatch.end();
	}

    private void update() {
        ball.move();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) leftPaddle.moveUp();
        if (Gdx.input.isKeyPressed(Input.Keys.S)) leftPaddle.moveDown();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) rightPaddle.moveUp();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) rightPaddle.moveDown();

        if (leftPaddle.rect().overlaps(ball.rect())) ball.bounceOffPaddle(leftPaddle);
        if (rightPaddle.rect().overlaps(ball.rect())) ball.bounceOffPaddle(rightPaddle);

        if (ball.isOffLeft()) {
            rightScore++;
            ball = new Ball();
        }

        if (ball.isOffRight()) {
            leftScore++;
            ball = new Ball();
        }
    }

    @Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
        shapeRenderer.dispose();
        spriteBatch.dispose();
	}
}
