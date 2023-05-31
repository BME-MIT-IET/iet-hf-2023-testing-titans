package vvv.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vvv.Controller;
import vvv.Game;
import vvv.MainFrame;

public class ApplyAgent {
    private Game game;
    private Controller controller;
    private MainFrame mainFrame;

    @Given("the user has a crafted agent")
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
        controller.craftEvent();
        controller.getCraftBox().getChooseButton().doClick();
    }

    @When("the user applies it to itself")
    public void pressesStartButton() {
        controller.applyEvent();
        controller.getApplyBox().getChooseButton().doClick();
    }

    @Then("the user gets effected")
    public void gameStarted() {
        assertEquals(0, game.getPlayers().get(0).getGeneticCodes().size());
        assertEquals("Felejtő", game.getPlayers().get(0).getEffects().get(0).toString());
    }
}
