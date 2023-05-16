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

class AxeEquipmentTest {
    AxeEquipment axeEquipment;

    @BeforeEach
    void init() {
        axeEquipment = new AxeEquipment();
    }

    @Test
    void handleAttack_NotYetUsed_Succeed() {
        // Arrange
        Player p = getMockPlayer();

        // Act
        axeEquipment.handleAttack(p);

        // Assert
        assertTrue(axeEquipment.isUsed());
        assertTrue(p.isDead());
    }

    @Test
    void handleAttack_AlreadyUsed_Fail() {
        // Arrange
        Player p1 = getMockPlayer();
        Player p2 = getMockPlayer();

        // Act
        axeEquipment.handleAttack(p1);
        axeEquipment.handleAttack(p2);

        // Assert
        assertTrue(axeEquipment.isUsed());
        assertTrue(p1.isDead());
        assertFalse(p2.isDead());
    }

    Player getMockPlayer() {
        Player p = mock(Player.class);
        try {
            java.lang.reflect.Field dead = Player.class.getDeclaredField("dead");
            dead.setAccessible(true);
            dead.set(p, false);

            doAnswer(invocation -> {
                dead.set(p, true);
                return null;
            }).when(p).killedBy();

            when(p.isDead()).thenAnswer(new Answer<Boolean>() {
                @Override
                public Boolean answer(InvocationOnMock invocation) throws Throwable {
                    return (boolean) dead.get(p);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }
}
