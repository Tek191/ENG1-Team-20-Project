package com.gourdoramsay.piazzapanic.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gourdoramsay.piazzapanic.PiazzaPanic;
import com.gourdoramsay.piazzapanic.scenes.Hud;
import org.w3c.dom.Text;

import javax.naming.spi.StateFactory;
import javax.swing.text.View;

public class GameScreen implements Screen {

    private PiazzaPanic game;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    //private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private SpriteBatch sbatch;
    private Texture chef1Image;
    private Texture chef2Image;

    private Rectangle chef1;

    private Rectangle chef2;

    private Rectangle currentChef;

    private Stage newStage;

    public GameScreen(PiazzaPanic game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(PiazzaPanic.V_WIDTH, PiazzaPanic.V_HEIGHT, gameCam);
        //hud = new Hud(game.batch);
        sbatch = new SpriteBatch();
        newStage = new Stage();
        Gdx.input.setInputProcessor(newStage);


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tilemap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        gameCam.position.set(gamePort.getWorldWidth()/2 , gamePort.getWorldHeight()/2, 0);
        chef1Image = new Texture(Gdx.files.internal("characters/chefI.png"));
        chef2Image = new Texture(Gdx.files.internal("characters/chef2I.png"));
        chef1 = new Rectangle();
        chef2 = new Rectangle();
        chef1.x = PiazzaPanic.V_WIDTH / 2 - 32 / 2;
        chef1.y = PiazzaPanic.V_HEIGHT /2 - 32 /2;
        chef1.width = 32;
        chef1.height = 32;

        chef2.x = PiazzaPanic.V_WIDTH / 2 + 32 / 2;
        chef2.y = PiazzaPanic.V_HEIGHT / 2 + 32 /2;
        chef2.width = 32;
        chef2.height = 32;
        currentChef = chef1;
    }


    public void handleInput(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)) currentChef = chef1;
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) currentChef = chef2;


        //Logic to make the chef move
        if(Gdx.input.isKeyPressed(Input.Keys.A)) currentChef.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D)) currentChef.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.S)) currentChef.y -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.W)) currentChef.y += 200 * Gdx.graphics.getDeltaTime();
        //Prevents the chef from leaving the map
        if(currentChef.x < 0) currentChef.x = 0;
        if(currentChef.x > gamePort.getScreenWidth()) currentChef.x = gamePort.getScreenWidth();
        if(currentChef.y < 0) currentChef.y = 0;
        if(currentChef.y > gamePort.getScreenHeight() -32) currentChef.y = gamePort.getScreenHeight() - 32;

    }


    public void update(float dt) {
        handleInput(dt);
        gameCam.update();
        mapRenderer.setView(gameCam);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Renders map to screen
        ///game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        //hud.stage.draw();
        mapRenderer.render();

        //Draws chef image to screen
        sbatch.begin();
        sbatch.draw(chef1Image, chef1.x, chef1.y);
        sbatch.draw(chef2Image, chef2.x, chef2.y);
        sbatch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
        chef1Image.dispose();
        chef2Image.dispose();
        sbatch.dispose();
        map.dispose();
        mapRenderer.dispose();
        newStage.dispose();
    }
}
