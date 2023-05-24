package vvv.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vvv.Controller;
import vvv.Game;
import vvv.MainFrame;
import vvv.ModelObserver;

public class StartGame {
  
  private Game game;
  private MainFrame mainFrame;

  @Given("the game has players")
  public void gameHasPlayers() {
    game = new Game();
    Controller controller = new Controller(game);
    mainFrame = new MainFrame(game, controller, controller::createPlayer,
        controller::startGame);
    mainFrame.getJMenuBar().getMenu(0).getItem(0).doClick();
    mainFrame.getCreatePlayerBox().getPlayerNameBox().setText("Player1");
    mainFrame.getCreatePlayerBox().getAddButton().doClick();
    
  }

  @When("the user pressess the start button")
  public void pressesStartButton() {
    mainFrame.getCreatePlayerBox().getStartButton().doClick();
  }

  @Then("the game starts")
  public void gameStarted() {
    assertTrue(game.isStarted());
  }

}
