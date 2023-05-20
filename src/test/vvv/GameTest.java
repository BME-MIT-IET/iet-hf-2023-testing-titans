package vvv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GameTest {
    TestGame game;
    List<String> calledByHandlers;

    @BeforeEach
    void init() {
        game = new TestGame();
        calledByHandlers = new ArrayList<>();
        ModelPublisher.getModelPublisher().subscribe(getModelObserver());
        game.subscribe(getGameObserver());
    }

    @Test
    void gameAddPlayer() {
        // Arrange
        Player p1 = new Player("TestPlayer1");
        Player p2 = new Player("TestPlayer2");

        // Act
        game.addPlayer(p1);
        game.addPlayer(p2);

        // Assert
        assertTrue(game.getPlayers().contains(p1), "TestPlayer1 is in the game");
        assertTrue(game.getPlayers().contains(p2), "TestPlayer2 is in the game");
        assertSame(p1.getCurrentField(), p2.getCurrentField(), "TestPlayer1 and TestPlayer2 is on the same field");
    }

    @Test
    void gameStart() {
        // Arrange
        Player p1 = new Player("TestPlayer1");
        Player p2 = new Player("TestPlayer2");
        game.addPlayer(p1);
        game.addPlayer(p2);

        // Act
        game.start();

        // Assert
        assertEquals(p1, game.getCurrentPlayer(), "The current player is the first one after the start");
        assertTrue(calledByHandlers.contains("onNextPlayer"), "onNextPlayer was published");
        assertTrue(calledByHandlers.contains("onTakeAction"), "onTakeAction was published");
    }

    @Test
    void gameNextPlayerWon() {
        // Arrange
        Player p = new Player("TestPlayer2win");
        GeneticCodeSlot gcs = new GeneticCodeSlot();
        gcs.addGeneticCode(ChoreaGeneticCode.getInstance());
        gcs.addGeneticCode(NumbingGeneticCode.getInstance());
        gcs.addGeneticCode(ForgetGeneticCode.getInstance());
        gcs.addGeneticCode(ProtectorGeneticCode.getInstance());
        p.fillAll(gcs);
        game.addPlayer(p);

        // Act
        game.start();
        game.nextPlayer();

        // Assert
        assertTrue(calledByHandlers.contains("onWin"), "onWin was published");
        assertTrue(calledByHandlers.contains("onNextPlayer"), "onNextPlayer was published");
        assertTrue(calledByHandlers.contains("onTakeAction"), "onTakeAction was published");
    }

    @Test
    void gameNextPlayer() {
        // Arrange
        Player p1 = new Player("TestPlayer1");
        Player p2 = new Player("TestPlayer2");
        game.addPlayer(p1);
        game.addPlayer(p2);

        // Act
        game.start();
        game.nextPlayer();

        // Assert
        assertEquals(p2, game.getCurrentPlayer(), "The current player is the second one");
        assertEquals(2, calledByHandlers.stream().filter(called -> called.equals("onNextPlayer")).count(),
                "onNextPlayer was published twice");
        assertEquals(2, calledByHandlers.stream().filter(called -> called.equals("onTakeAction")).count(),
                "onTakeAction was published twice");
    }

    @Test
    void gameNewRound() {
        // Arrange
        Player p1 = new Player("TestPlayer1");
        Player p2 = new Player("TestPlayer2");
        game.addPlayer(p1);
        game.addPlayer(p2);

        // Act
        game.start();
        game.nextPlayer();
        game.nextPlayer();

        // Assert
        assertEquals(p1, game.getCurrentPlayer(), "The current player is the second one");
        assertEquals(3, calledByHandlers.stream().filter(called -> called.equals("onNextPlayer")).count(),
                "onNextPlayer was published twice");
        assertEquals(3, calledByHandlers.stream().filter(called -> called.equals("onTakeAction")).count(),
                "onTakeAction was published twice");
        assertTrue(calledByHandlers.contains("handleNewRound"), "handleNewRound was published");
    }

    @Test
    void gameNewRoundPlayerDead() {
        // Arrange
        Player p1 = new Player("TestPlayer1");
        Player p2 = new Player("TestPlayer2");
        Player p3 = new Player("TestPlayer3");
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);
        p2.killedBy();

        // Act
        game.start();
        game.nextPlayer();

        // Assert
        assertEquals(p3, game.getCurrentPlayer(), "The current player is the third one");
    }

    @Test
    void gameNewRoundSkip() {
        // Arrange
        Player p1 = new Player("TestPlayer1");
        Player p2 = new Player("TestPlayer2");
        p2.addAgentEffect(new NumbingAgentEffect());
        game.addPlayer(p1);
        game.addPlayer(p2);

        // Act
        game.start();
        game.nextPlayer();

        // Assert
        assertEquals(p2, game.getCurrentPlayer(), "The current player is the third one");
        assertTrue(calledByHandlers.contains("onSkip"), "onSkip was published");
    }

    ModelObserver getModelObserver() {

        return new ModelObserver() {

            @Override
            public void onWin() {
                calledByHandlers.add("onWin");
            }

            @Override
            public void onNextPlayer() {
                calledByHandlers.add("onNextPlayer");
            }

            @Override
            public void onTakeAction() {
                calledByHandlers.add("onTakeAction");
            }

            @Override
            public void onSwap(Equipment equipmentToSwap) {
                calledByHandlers.add("onSwap");
            }

            @Override
            public void onSkip() {
                calledByHandlers.add("onSkip");
            }
        };
    }

    GameObserver getGameObserver() {
        return () -> calledByHandlers.add("handleNewRound");
    }

    static class TestGame extends Game {
        @Override
        protected void init() {
            this.fields.add(new FreeField());
        }
    };
}
