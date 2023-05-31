package vvv.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vvv.Controller;
import vvv.Game;
import vvv.MainFrame;

public class CreateAgent {
    private Game game;
    private Controller controller;
    private MainFrame mainFrame;

    @Given("the user knows a genetic code and has enough amino and nucleotid")
    public void gameInit() {
        game = new Game();
        Game.makePlayerPlacementDeterministic();
        controller = new Controller(game);
        mainFrame = new MainFrame(game, controller, controller::createPlayer,
                controller::startGame);
        mainFrame.getJMenuBar().getMenu(0).getItem(0).doClick();
        mainFrame.getCreatePlayerBox().getPlayerNameBox().setText("Player1");
        mainFrame.getCreatePlayerBox().getAddButton().doClick();
        mainFrame.getCreatePlayerBox().getStartButton().doClick();
        controller.moveEvent();
        controller.getMoveBox().getChooseButton().doClick();
        controller.moveEvent();
        controller.getMoveBox().getFields().setSelectedIndex(1);
        controller.getMoveBox().getChooseButton().doClick();
        controller.moveEvent();
        controller.getMoveBox().getFields().setSelectedIndex(1);
        controller.getMoveBox().getChooseButton().doClick();
        controller.moveEvent();
        controller.getMoveBox().getFields().setSelectedIndex(2);
        controller.getMoveBox().getChooseButton().doClick();
    }

    @When("the user creates an agent")
    public void pressesStartButton() {
        controller.craftEvent();
        controller.getCraftBox().getChooseButton().doClick();
    }

    @Then("the user gets the agent")
    public void gameStarted() {
        assertEquals("Felejtő", game.getPlayers().get(0).getAgents().get(0).toString());
    }
}
