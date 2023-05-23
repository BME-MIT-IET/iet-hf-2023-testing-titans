package vvv.cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vvv.Controller;
import vvv.Game;
import vvv.MainFrame;

public class NewGame {

  private Game game;
  private Controller controller;
  private MainFrame mainFrame;

  @Given("the game has not started yet")
  public void theGameIsNotStartedYet() {
    game = new Game();
    controller = new Controller(game);
  }

  @When("the user pressess the new game button")
  public void userPressesNewGameButton() {
    mainFrame = new MainFrame(game, controller, controller::createPlayer,
        controller::startGame);
    mainFrame.getJMenuBar().getMenu(0).getItem(0).doClick();
  }

  @Then("a new game starts")
  public void newGameStarted() {
    assertTrue(mainFrame.getCreatePlayerBox().isVisible());
  }
}
