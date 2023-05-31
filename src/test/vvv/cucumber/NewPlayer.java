package vvv.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import vvv.Controller;
import vvv.Game;
import vvv.MainFrame;
import io.cucumber.java.en.Then;

public class NewPlayer {

  private Game game;
  private MainFrame mainFrame;

  @Given("the add player screen is displayed")
  public void addPlayerScreen() {
    game = new Game();
    Controller controller = new Controller(game);
    mainFrame = new MainFrame(game, controller, controller::createPlayer,
        controller::startGame);
    mainFrame.getJMenuBar().getMenu(0).getItem(0).doClick();
  }

  @When("the user adds a player")
  public void userAddsPlayer() {
    mainFrame.getCreatePlayerBox().getPlayerNameBox().setText("Player1");
    mainFrame.getCreatePlayerBox().getAddButton().doClick();
  }

  @Then("a new player is added")
  public void newPlayerAdded() {
    assertEquals("Player1", game.getPlayers().get(0).getName());
  }

}
