package vvv.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vvv.Controller;
import vvv.Field;
import vvv.Game;
import vvv.MainFrame;

public class CanStep {

  private Game game;
  private Controller controller;
  private MainFrame mainFrame;
  private Field firstField;

  @Given("the game has started")
  public void gameHasPlayers() {
    game = new Game();
    controller = new Controller(game);
    mainFrame = new MainFrame(game, controller, controller::createPlayer,
        controller::startGame);
    mainFrame.getJMenuBar().getMenu(0).getItem(0).doClick();
    mainFrame.getCreatePlayerBox().getPlayerNameBox().setText("Player1");
    mainFrame.getCreatePlayerBox().getAddButton().doClick();
    mainFrame.getCreatePlayerBox().getStartButton().doClick();
    
  }

  @When("the user steps to new field")
  public void pressesStartButton() {
    firstField = game.getPlayers().get(0).getCurrentField();
    controller.moveEvent();
    controller.getMoveBox().getChooseButton().doClick();

  }

  @Then("the user is on new field")
  public void gameStarted() {
    assertNotEquals(firstField, game.getPlayers().get(0).getCurrentField());
  }
  
}
