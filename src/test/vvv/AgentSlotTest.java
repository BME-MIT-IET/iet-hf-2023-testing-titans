package vvv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgentSlotTest {
    static final int SLOT_SIZE = 3;

    AgentSlot agentSlot;

    @BeforeEach
    void init() {
        agentSlot = new AgentSlot();
    }

    @Test
    void addAgent_OneAgent_Succeed() {
        // Arrange
        addOneAgentToSlot();

        // Assert
        assertEquals(1, agentSlot.getValue());
    }

    @Test
    void addAgent_TooMany_Fail() {
        // Arrange
        addManyAgents(SLOT_SIZE);

        // Act
        addOneAgentToSlot();

        // Assert
        assertEquals(SLOT_SIZE, agentSlot.getValue());
    }

    @Test
    void fill_FromAgentSlot_Succeed() {
        // Arrange
        Slot slot = getAgentSlotWithAgents(1);

        // Act
        agentSlot.fill(slot);

        // Assert
        assertEquals(1, agentSlot.getValue());
        assertEquals(0, slot.getValue());
    }

    @Test
    void fill_FromAgentSlot_Fail() {
        // Arrange
        addManyAgents(SLOT_SIZE);
        Slot slot = getAgentSlotWithAgents(1);

        // Act
        agentSlot.fill(slot);

        // Assert
        assertEquals(SLOT_SIZE, agentSlot.getValue());
        assertEquals(1, slot.getValue());
    }

    @Test
    void fill_FromNonAgentSlot_Fail() {
        // Arrange
        EquipmentSlot slot = mock(EquipmentSlot.class);
        slot.addEquipment(mock(Equipment.class));

        // Act
        agentSlot.fill(slot);

        // Assert
        assertTrue(agentSlot.getAgents().isEmpty());
    }

    @Test
    void handleRemoveAgent_NoAgent_NoError() {
        // Act
        agentSlot.handleRemoveAgent(mock(Agent.class));

        // Assert
        assertTrue(agentSlot.getAgents().isEmpty());
    }

    void addOneAgentToSlot() {
        agentSlot.addAgent(mock(Agent.class));
    }

    void addManyAgents(int agentCount) {
        for (int i = 0; i < agentCount; i++) {
            addOneAgentToSlot();
        }
    }

    AgentSlot getAgentSlotWithAgents(int agentCount) {
        AgentSlot slot = new AgentSlot();
        for (int i = 0; i < agentCount; i++) {
            slot.addAgent(mock(Agent.class));
        }
        return slot;
    }
}
