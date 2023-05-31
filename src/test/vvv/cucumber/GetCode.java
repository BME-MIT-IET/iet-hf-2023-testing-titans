package vvv.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vvv.Controller;
import vvv.Game;
import vvv.MainFrame;

public class GetCode {
    private Game game;
    private Controller controller;
    private MainFrame mainFrame;

    @Given("the game has started4")
    public void gameHasPlayers() {
        game = new Game();
        Game.makePlayerPlacementDeterministic();
        controller = new Controller(game);
        mainFrame = new MainFrame(game, controller, controller::createPlayer,
                controller::startGame);
        mainFrame.getJMenuBar().getMenu(0).getItem(0).doClick();
        mainFrame.getCreatePlayerBox().getPlayerNameBox().setText("Player1");
        mainFrame.getCreatePlayerBox().getAddButton().doClick();
        mainFrame.getCreatePlayerBox().getStartButton().doClick();
    }

    @When("the user steps to a laboratory")
    public void pressesStartButton() {
        controller.moveEvent();
        controller.getMoveBox().getChooseButton().doClick();
        controller.moveEvent();
        controller.getMoveBox().getFields().setSelectedIndex(1);
        controller.getMoveBox().getChooseButton().doClick();
    }

    @Then("the user receieves a genetic code")
    public void gameStarted() {
        assertEquals("Felejtő", game.getPlayers().get(0).getGeneticCodes().get(0).toString());
    }
}
