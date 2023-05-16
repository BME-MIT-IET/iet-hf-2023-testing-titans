import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vvv.AgentSlot;
import vvv.ChoreaAgent;

class AgentSlotTest {
    final int SLOT_SIZE = 3;

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
        agentSlot.addAgent(new ChoreaAgent());

        // Assert
        assertEquals(SLOT_SIZE, agentSlot.getValue());
    }

    @Test
    void fill_FromAgentSlot_Succeed() {
        // Arrange
        AgentSlot slot = getAgentSlotWithAgents(1);

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
        AgentSlot slot = getAgentSlotWithAgents(1);

        // Act
        agentSlot.fill(slot);

        // Assert
        assertEquals(SLOT_SIZE, agentSlot.getValue());
        assertEquals(1, slot.getValue());
    }

    void addOneAgentToSlot() {
        agentSlot.addAgent(new ChoreaAgent());
    }

    void addManyAgents(int agentCount) {
        for (int i = 0; i < agentCount; i++) {
            addOneAgentToSlot();
        }
    }

    AgentSlot getAgentSlotWithAgents(int agentCount) {
        AgentSlot slot = new AgentSlot();
        for (int i = 0; i < agentCount; i++) {
            slot.addAgent(new ChoreaAgent());
        }
        return slot;
    }
}
