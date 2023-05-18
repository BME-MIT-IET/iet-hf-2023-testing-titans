package vvv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {
    final int ITEM_COUNT_PER_SLOT = 2;
    final int EQUIPMENT_COUNT_PER_SLOT = 3;
    Inventory inventory;

    @BeforeEach
    void init() {
        inventory = new Inventory();
        inventory.addSlot(new AminoAcidSlot(ITEM_COUNT_PER_SLOT));
        inventory.addSlot(new EquipmentSlot());
    }

    @Test
    void fillAll_OneSlot_Succeed() {
        // Arrange
        Slot s = getFilledSlot();

        // Act
        inventory.fillAll(s);

        // Assert
        assertEquals(ITEM_COUNT_PER_SLOT, inventory.getAminoAcidCount());
    }

    @Test
    void fillAll_TwoSlots_Fail() {
        // Arrange
        Slot s1 = getFilledSlot();
        Slot s2 = getFilledSlot();

        // Act
        inventory.fillAll(s1);
        inventory.fillAll(s2);

        // Assert
        assertEquals(ITEM_COUNT_PER_SLOT, inventory.getAminoAcidCount());
    }

    @Test
    void fillFrom_OneInventory_Succeed() {
        // Arrange
        Inventory i = getFilledInventory();

        // Act
        inventory.fillFrom(i);

        // Assert
        assertEquals(0, i.getAminoAcidCount());
        assertEquals(ITEM_COUNT_PER_SLOT, inventory.getAminoAcidCount());
    }

    @Test
    void fillFrom_InventoryWithEquipment_Succeed() {
        // Arrange
        EquipmentSlot es = new EquipmentSlot();
        es.addEquipment(mock(Equipment.class));
        Inventory i = new Inventory();
        i.addSlot(es);

        // Act
        inventory.fillFrom(i);

        // Assert
        assertNotNull(inventory.getEquipment());
        assertNull(i.getEquipment());
    }

    @Test
    void fillFrom_InventoryWithManyEquipments_Succeed() {
        // Arrange
        EquipmentSlot es = new EquipmentSlot();
        for (int i = 0; i < ITEM_COUNT_PER_SLOT; i++) {
            es.addEquipment(mock(Equipment.class));
        }
        Inventory i = new Inventory();
        i.addSlot(es);

        // Act
        inventory.fillFrom(i);

        // Assert
        assertEquals(ITEM_COUNT_PER_SLOT, inventory.getEquipments().size());
        assertEquals(0, i.getEquipments().size());
    }

    @Test
    void fillFrom_InventoryWithManyEquipments_Fail() {
        // Arrange
        Inventory inv = new Inventory();
        for (int i = 0; i < 2; i++) {
            EquipmentSlot es = new EquipmentSlot();
            for (int j = 0; j < ITEM_COUNT_PER_SLOT; j++) {
                es.addEquipment(mock(Equipment.class));
            }
            inv.addSlot(es);
        }

        // Act
        inventory.fillFrom(inv);

        // Assert
        assertEquals(EQUIPMENT_COUNT_PER_SLOT, inventory.getEquipments().size());
        assertNotEquals(0, inv.getEquipments().size());
    }

    @Test
    void checkMaterial_InventoryFull_Succeed() {
        // Arrange
        inventory.addSlot(getFilledSlot());
        Inventory i = new Inventory();
        i.addSlot(new AminoAcidSlot(ITEM_COUNT_PER_SLOT));

        // Act
        boolean success = inventory.checkMaterial(i);

        // Assert
        assertTrue(success);
        assertTrue(i.isFull());
        assertEquals(0, inventory.getAminoAcidCount());
    }

    @Test
    void checkMaterial_InventoryEmpty_Fail() {
        // Arrange
        inventory.addSlot(getFilledSlot());
        Inventory i = new Inventory();
        i.addSlot(new AminoAcidSlot(ITEM_COUNT_PER_SLOT));
        i.addSlot(new NucleotideSlot(ITEM_COUNT_PER_SLOT));

        // Act
        boolean success = inventory.checkMaterial(i);

        // Assert
        assertFalse(success);
        assertEquals(0, i.getAminoAcidCount());
        assertEquals(0, i.getNucleotideCount());
        assertEquals(ITEM_COUNT_PER_SLOT, inventory.getAminoAcidCount());
    }

    @Test
    void takeOutEquipment_HasEquipment_Succeed() {
        // Arrange
        EquipmentSlot es = new EquipmentSlot();
        es.addEquipment(mock(Equipment.class));
        inventory.fillAll(es);

        // Act
        Equipment e = inventory.takeOutEquipment(0).getEquipment();

        // Assert
        assertNotNull(e);
        assertNull(inventory.getEquipment());
    }

    @Test
    void takeOutEquipment_NoEquipment_Fail() {
        // Arrange
        // Nothing to do here

        // Act
        assertThrows(IndexOutOfBoundsException.class,
                () -> inventory.takeOutEquipment(0));

        // Assert
        assertNull(inventory.getEquipment());
    }

    Slot getFilledSlot() {
        Slot s = new AminoAcidSlot(ITEM_COUNT_PER_SLOT);
        s.fillToMax();
        return s;
    }

    Inventory getFilledInventory() {
        Inventory i = new Inventory();
        i.addSlot(new AminoAcidSlot(ITEM_COUNT_PER_SLOT));
        Slot s = getFilledSlot();
        i.fillAll(s);
        return i;
    }
}
