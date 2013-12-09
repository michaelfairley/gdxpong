package com.m12y.gdxpong.html;

import com.m12y.gdxpong.core.PongGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class PongGameHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new PongGame();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(PongGame.WIDTH, PongGame.HEIGHT);
	}
}
