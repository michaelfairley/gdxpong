package com.m12y.gdxpong.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.m12y.gdxpong.core.PongGame;

public class PongGameDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL20 = true;
        config.width = PongGame.WIDTH;
        config.height = PongGame.HEIGHT;
		new LwjglApplication(new PongGame(), config);
	}
}
