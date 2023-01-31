package com.gourdoramsay.piazzapanic.screens;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gourdoramsay.piazzapanic.PiazzaPanic;
import com.gourdoramsay.piazzapanic.screens.GameScreen;
import org.w3c.dom.Text;

public class MainMenu implements Screen {
    private PiazzaPanic parent;
    private Stage stage;

    private Texture background;
    private SpriteBatch spriteBatch;

    public MainMenu(PiazzaPanic piazzapanic) {
        parent = piazzapanic;
        //Create stage and set as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);
        // Functional at the moment but would be better if we had asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        //create buttons
        TextButton newGame = new TextButton("Scenario Mode", skin);
        TextButton instructions = new TextButton("Instructions", skin);
        TextButton exit = new TextButton("Exit", skin);

        //add buttons to table
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
        table.add(instructions).fillX().uniformX();
        table.row();
        table.row().pad(10, 0, 10, 0);
        table.add(exit).fillX().uniformX();

        // create button listeners
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new GameScreen(parent));
            }
        });

        instructions.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new InstructionScreen(parent));
            }
        });

    }

    @Override
    public void render(float delta){

        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        background = new Texture(Gdx.files.internal("TileMap.png"));
        spriteBatch.begin();
        spriteBatch.draw(background, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        spriteBatch.dispose();
    }
}

