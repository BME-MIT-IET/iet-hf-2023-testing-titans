package vvv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapLoaderTest {
    MapLoader mapLoader;

    @BeforeEach
    void init() {
        mapLoader = new MapLoader();
    }

    @Test
    void findField_DoesntExist_Fail() {
        // Arrange
        // Nothing to arrange

        // Act
        Field field = mapLoader.findField("some-id");

        // Assert
        assertNull(field);
    }

    @Test
    void createField_OneFreeField_Succeed() {
        // Arrange
        final String ID = "Szabad1";

        // Act
        mapLoader.createField("free", ID);

        // Assert
        assertEquals(1, mapLoader.getFields().size());
        assertNotNull(mapLoader.findField(ID));
    }

    @Test
    void connect_TwoFields_Succeed() {
        // Arrange
        final String ID1 = "Szabad1";
        final String ID2 = "Szabad2";
        mapLoader.createField("free", ID1);
        mapLoader.createField("free", ID2);

        // Act
        mapLoader.connect(ID1, ID2);

        // Assert
        assertEquals(mapLoader.findField(ID1), mapLoader.findField(ID2).getNeighbors().get(0));
        assertEquals(mapLoader.findField(ID2), mapLoader.findField(ID1).getNeighbors().get(0));
    }

    @Test
    void connect_OneFieldToItself_Fail() {
        // Arrange
        final String ID1 = "Szabad1";
        mapLoader.createField("free", ID1);

        // Act
        mapLoader.connect(ID1, ID1);

        // Assert
        assertTrue(mapLoader.findField(ID1).getNeighbors().isEmpty());
    }

    @Test
    void interpretCommand_CreateShelterFieldAddAxe_Succeed() {
        // Arrange
        final String ID = "Óvóhely1";
        final String CMD1 = "create field shel " + ID;
        final String CMD2 = "add equi axe " + ID + " Balta1";
        Player p = new Player("Alice");

        // Act
        mapLoader.interpretCommand(CMD1);
        mapLoader.interpretCommand(CMD2);
        mapLoader.findField(ID).collect(p);

        // Assert
        assertNotNull(p.getEquipments().get(0));
    }

    @Test
    void interpretCommand_CreateShelterFieldAddGeneticCode_Succeed() {
        // Arrange
        final String ID = "Labor1";
        final String CMD1 = "create field labo " + ID;
        final String CMD2 = "add gene chor " + ID;
        Player p = new Player("Alice");

        // Act
        mapLoader.interpretCommand(CMD1);
        mapLoader.interpretCommand(CMD2);
        mapLoader.findField(ID).collect(p);

        // Assert
        assertNotNull(p.getGeneticCodes().get(0));
    }

    @Test
    void interpretCommand_CreateFreeFieldAddEquipment_Fail() {
        // Arrange
        final String ID = "Szabad1";
        final String CMD1 = "create field free " + ID;
        final String CMD2 = "add equi glov " + ID + " Kesztyű1";
        Player p = new Player("Alice");

        // Act
        mapLoader.interpretCommand(CMD1);
        mapLoader.interpretCommand(CMD2);
        mapLoader.findField(ID).collect(p);

        // Assert
        assertEquals(0, p.getEquipments().size());
    }

    @Test
    void interpretCommand_CreateFreeFieldAddGeneticCode_Fail() {
        // Arrange
        final String ID = "Szabad1";
        final String CMD1 = "create field free " + ID;
        final String CMD2 = "add gene numb " + ID;
        Player p = new Player("Alice");

        // Act
        mapLoader.interpretCommand(CMD1);
        mapLoader.interpretCommand(CMD2);
        mapLoader.findField(ID).collect(p);

        // Assert
        assertEquals(0, p.getGeneticCodes().size());
    }

    @Test
    void interpretCommand_ConnectTwoFields_Succeed() {
        // Arrange
        final String ID1 = "Szabad1";
        final String ID2 = "Szabad2";
        final String CMD1 = "create field free " + ID1;
        final String CMD2 = "create field free " + ID2;
        final String CMD3 = "connect " + ID1 + " " + ID2;

        // Act
        mapLoader.interpretCommand(CMD1);
        mapLoader.interpretCommand(CMD2);
        mapLoader.interpretCommand(CMD3);

        // Assert
        assertEquals(mapLoader.findField(ID1), mapLoader.findField(ID2).getNeighbors().get(0));
        assertEquals(mapLoader.findField(ID2), mapLoader.findField(ID1).getNeighbors().get(0));
    }
}
