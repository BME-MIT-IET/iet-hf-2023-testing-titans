package vvv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChoreaAgentEffectTest {
    ChoreaAgentEffect choreaAgentEffect;
    Field field1, field2;
    Player player;

    @BeforeEach
    void init() {
        choreaAgentEffect = new ChoreaAgentEffect();
        field1 = new FreeField();
        field2 = new FreeField();
        field1.addNeighbor(field2);
        field2.addNeighbor(field1);
        player = new Player("Alice");
        field1.addPlayer(player);
        player.setField(field1);
    }

    @Test
    void handleTakeAction_UndeterministicTwoFields_Succeed() {
        // Arrange
        // No further arrangement needed

        // Act
        player.addAgentEffect(choreaAgentEffect);
        choreaAgentEffect.handleTakeAction();

        // Assert
        assertEquals(field2, player.getCurrentField());
    }

    @Test
    void handleTakeAction_UndeterministicManyFields_Succeed() {
        // Arrange
        Field field3 = new FreeField();
        Field field4 = new FreeField();
        field2.addNeighbor(field3);
        field3.addNeighbor(field2);
        field3.addNeighbor(field1);
        field1.addNeighbor(field3);
        field1.addNeighbor(field4);
        field4.addNeighbor(field1);
        List<Field> visited = new ArrayList<>();
        final int ROUND_COUNT = 5;

        // Act
        player.addAgentEffect(choreaAgentEffect);
        for (int i = 0; i < ROUND_COUNT; i++) {
            choreaAgentEffect.handleTakeAction();
            visited.add(player.getCurrentField());
        }

        // Assert
        assertEquals(ROUND_COUNT, visited.size());
    }

    @Test
    void handleAnointedBy_Deterministic_Succeed() {
        // Arrange
        List<Field> fields = new ArrayList<>();
        fields.add(field2);
        fields.add(field1);
        choreaAgentEffect.setFields(fields);

        // Act
        player.addAgentEffect(choreaAgentEffect);
        choreaAgentEffect.handleTakeAction();
        Field first = player.getCurrentField();
        choreaAgentEffect.handleTakeAction();
        Field second = player.getCurrentField();

        // Assert
        assertEquals(field2, first);
        assertEquals(field1, second);
    }
}
