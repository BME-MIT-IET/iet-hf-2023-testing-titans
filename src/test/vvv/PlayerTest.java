package vvv;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class PlayerTest {
    Player player;

    @BeforeEach
    void init() {
        player = new Player("TestPlayer");
    }

    @Test
    void playerLearnGeneticCode() {
        // Arrange
        GeneticCodeSlot gcs = new GeneticCodeSlot();
        NumbingGeneticCode ngc = mock(NumbingGeneticCode.class);
        gcs.addGeneticCode(ngc);

        // Act
        player.fillAll(gcs);

        // Assert
        assertTrue(player.getGeneticCodes().contains(ngc));
    }

    @Test
    void playerCreateAgentSuccess() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerCreateAgentFailed() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerApplyAgent() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerAnointedByHandled() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerAnointedByUnHandled() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerAnointedByFinal() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerStealMaterialHandled() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerStealMaterialUnHandled() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerStealEquipmentHandled() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerStealEquipmentUnHandled() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerMoveToEmpty() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerMoveAndCollect() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerAddAgentEffect() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerRemoveAgentEffect() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerSwapEquipment() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerAddSlot() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerRemoveSlot() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerClearGeneticCodes() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerFillAll() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerGive() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerRemoveAgent() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerTakeActionCanTake() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerTakeActionCanNotTake() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerAddEffect() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerRemoveEffect() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerThrowAwayEquipment() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerKill() {
        // Arrange

        // Act

        // Assert

    }

    @Test
    void playerKilled() {
        // Arrange
        Field f = new FreeField();
        player.move(f);

        // Act
        player.killedBy();

        // Assert
        assertTrue(player.isDead(), "The player is dead");
        assertFalse(f.getPlayers().contains(player), "The player has been removed from the field");
    }

    @Test
    void playerInfect() {
        // Arrange

        // Act

        // Assert

    }
}
