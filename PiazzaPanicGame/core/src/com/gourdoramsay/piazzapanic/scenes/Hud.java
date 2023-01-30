package com.gourdoramsay.piazzapanic.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gourdoramsay.piazzapanic.PiazzaPanic;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    private Integer customerCount;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label recipeLabel;

    Label customersLabel;

    Label customersLabel2;

    public Hud (SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        customerCount = 5;
        viewport = new FitViewport(PiazzaPanic.V_WIDTH, PiazzaPanic.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label(String.format("TIME"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        recipeLabel = new Label(String.format("RECIPE"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        customersLabel = new Label(String.format("%01d", customerCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        customersLabel2 = new Label(String.format("CUSTOMERS REMAINING"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(recipeLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(customersLabel2).expandX().padTop(10);

        table.row();
        table.add(scoreLabel).expandX();
        table.add(countdownLabel).expandX();
        table.add(customersLabel).expandX();
        stage.addActor(table);
    }
}
