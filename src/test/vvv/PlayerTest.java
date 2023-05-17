package vvv;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void playerApplyAgent() {
        // Arrange
        Agent a = mock(NumbingAgent.class);
        Player player2anoint = mock(Player.class);

        // Act
        player.applyAgent(a, player2anoint);

        // Assert
        assertFalse(player.getAgents().contains(a), "The agent is no longer in the inventory of the player");
    }

    @Test
    void playerAnointedByHandled() {
        // Arrange
        Player player2anoint = new Player("TestPlayer2anoint");
        Agent a = new NumbingAgent();

        // Act
        player2anoint.anointedBy(player, a);

        // Assert
        assertEquals(
                1,
                player2anoint.getEffects().stream().filter(effect -> effect.getClass() == NumbingAgentEffect.class).count(),
                "The anointed player was anointed."
        );
        assertEquals(
                0,
                player.getEffects().stream().filter(effect -> effect.getClass() == NumbingAgentEffect.class).count(),
                "The player was not anointed."
        );
    }

    @Test
    void playerAnointedByUnHandledProtected() {
        // Arrange
        Player player2anoint = new Player("TestPlayer2anoint");
        Agent a = new NumbingAgent();
        Equipment e = new ProtectorEquipment();
        e.setDeterm(true, true);
        EquipmentSlot es = new EquipmentSlot();
        es.addEquipment(e);
        player2anoint.fillAll(es);

        // Act
        player2anoint.anointedBy(player, a);

        // Assert
        assertEquals(
                0,
                player2anoint.getEffects().stream().filter(effect -> effect.getClass() == NumbingAgentEffect.class).count(),
                "The anointed player was not anointed."
        );
        assertEquals(
                0,
                player.getEffects().stream().filter(effect -> effect.getClass() == NumbingAgentEffect.class).count(),
                "The player was not anointed."
        );
    }

    @Test
    void playerAnointedByUnHandledAnointedBack() {
        // Arrange
        Player player2anoint = new Player("TestPlayer2anoint");
        Agent a = new NumbingAgent();
        Equipment e = new GloveEquipment();
        EquipmentSlot es = new EquipmentSlot();
        es.addEquipment(e);
        player2anoint.fillAll(es);

        // Act
        player2anoint.anointedBy(player, a);

        // Assert
        assertEquals(
                0,
                player2anoint.getEffects().stream().filter(effect -> effect.getClass() == NumbingAgentEffect.class).count(),
                "The anointed player was not anointed."
        );
        assertEquals(
                1,
                player.getEffects().stream().filter(effect -> effect.getClass() == NumbingAgentEffect.class).count(),
                "The player was anointed back."
        );
    }

    @Test
    void playerAnointedByFinal() {
        // Arrange
        Agent a = new NumbingAgent();

        // Act
        player.anointedByFinal(a);

        // Assert
        assertEquals(
                1,
                player.getEffects().stream().filter(effect -> effect.getClass() == NumbingAgentEffect.class).count(),
                "The player was anointed."
        );
    }

    @Test
    void playerStealMaterialHandled() {
        // Arrange
        Player player2rob = new Player("TestPlayer2rob");
        MaterialSlot ms = new AminoAcidSlot(5);
        ms.fillToMax();
        player2rob.fillAll(ms);

        // Act
        player.stealMaterial(player2rob);

        // Assert
        assertEquals(0, player.getAminoAcidCount(), "The amino acid is not in the robber's inventory.");
        assertEquals(5, player2rob.getAminoAcidCount(), "The amino acid is still in the robbed player's inventory.");
    }

    @Test
    void playerStealMaterialUnHandled() {
        // Arrange
        Player player2rob = new Player("TestPlayer2rob");
        MaterialSlot ms = new AminoAcidSlot(5);
        ms.fillToMax();
        player2rob.fillAll(ms);
        AgentEffect ae = new NumbingAgentEffect();
        player2rob.addAgentEffect(ae);

        // Act
        player.stealMaterial(player2rob);

        // Assert
        assertEquals(5, player.getAminoAcidCount(), "The amino acid is in the robber's inventory.");
        assertEquals(0, player2rob.getAminoAcidCount(), "The amino acid is not in the robbed player's inventory.");
    }

    @Test
    void playerStealEquipmentHandled() {
        // Arrange
        Player player2rob = new Player("TestPlayer2rob");
        EquipmentSlot es = new EquipmentSlot();
        Equipment e = new AxeEquipment();
        es.addEquipment(e);
        player2rob.fillAll(es);

        // Act
        player.stealEquipment(player2rob, e);

        // Assert
        assertFalse(player.getEquipments().contains(e), "The equipment is not in the robber's inventory.");
        assertTrue(player2rob.getEquipments().contains(e), "The equipment is still in the robbed player's inventory.");
    }

    @Test
    void playerStealEquipmentUnHandled() {
        // Arrange
        Player player2rob = new Player("TestPlayer2rob");
        EquipmentSlot es = new EquipmentSlot();
        Equipment e = new AxeEquipment();
        es.addEquipment(e);
        player2rob.fillAll(es);
        AgentEffect ae = new NumbingAgentEffect();
        player2rob.addAgentEffect(ae);

        // Act
        player.stealEquipment(player2rob, e);

        // Assert
        assertTrue(player.getEquipments().contains(e), "The equipment is in the robber's inventory.");
        assertFalse(player2rob.getEquipments().contains(e), "The equipment is not in the robbed player's inventory.");
    }

    @Test
    void playerMoveToEmpty() {
        // Arrange
        Field f = new FreeField();

        // Act
        player.move(f);

        // Assert
        assertEquals(f, player.getCurrentField(), "The player's field is the field he stepped into.");
        assertTrue(f.getPlayers().contains(player), "The player is on the field");
    }

    @Test
    void playerMoveAndCollect() {
        // Arrange
        Field f = new StorageField();

        // Act
        player.move(f);

        // Assert
        assertEquals(f, player.getCurrentField(), "The player's field is the field he stepped into.");
        assertTrue(f.getPlayers().contains(player), "The player is on the field");
        assertNotEquals(0, player.getAminoAcidCount(), "The player picked up amino acids form the storage.");
    }

    @Test
    void playerAddAgentEffect() {
        // Arrange
        AgentEffect ae = new NumbingAgentEffect();

        // Act
        player.addAgentEffect(ae);

        // Assert
        assertTrue(player.getEffects().contains(ae), "The player has the effect.");
        assertEquals(player, ae.player, "The effect has the player.");
    }

    @Test
    void playerRemoveAgentEffect() {
        // Arrange
        AgentEffect ae = new NumbingAgentEffect();
        player.addAgentEffect(ae);

        // Act
        player.removeAgentEffect(ae);

        // Assert
        assertFalse(player.getEffects().contains(ae), "The player no longer has the effect.");
        assertNull(ae.player, "The effect no longer has the player.");
    }

    @Test
    void playerSwapEquipment() {
        // Arrange
        Equipment e1 = mock(AxeEquipment.class);
        Equipment e2 = mock(GloveEquipment.class);
        EquipmentSlot es = new EquipmentSlot();
        es.addEquipment(e1);
        player.fillAll(es);

        // Act
        player.swapEquipment(e1, e2);

        // Assert
        assertFalse(player.getEquipments().contains(e1), "The player no longer has the e1 equipment");
        assertTrue(player.getEquipments().contains(e2), "The player has the e2 equipment");
    }

    @Test
    void playerClearGeneticCodes() {
        // Arrange
        GeneticCodeSlot gcs = new GeneticCodeSlot();
        gcs.addGeneticCode(mock(NumbingGeneticCode.class));
        gcs.addGeneticCode(mock(NumbingGeneticCode.class));
        gcs.addGeneticCode(mock(NumbingGeneticCode.class));
        player.fillAll(gcs);

        // Act
        player.clearGeneticCodes();

        // Assert
        assertEquals(player.getGeneticCodes().size(), 0, "The player forgot all the genetic codes");
    }

    @Test
    void playerFillAllAgent() {
        // Arrange
        AgentSlot as = new AgentSlot();
        Agent a = mock(NumbingAgent.class);
        as.addAgent(a);

        // Act
        player.fillAll(as);

        // Assert
        assertTrue(player.getAgents().contains(a), "The player took the agent");
    }

    @Test
    void playerFillAllGeneticCode() {
        // Arrange
        GeneticCodeSlot gcs = new GeneticCodeSlot();
        GeneticCode gc = mock(NumbingGeneticCode.class);
        gcs.addGeneticCode(gc);

        // Act
        player.fillAll(gcs);

        // Assert
        assertTrue(player.getGeneticCodes().contains(gc), "The player learnt the genetic code");
    }

    @Test
    void playerFillAllEquipment() {
        // Arrange
        EquipmentSlot es = new EquipmentSlot();
        Equipment e = mock(AxeEquipment.class);
        es.addEquipment(e);

        // Act
        player.fillAll(es);

        // Assert
        assertTrue(player.getEquipments().contains(e), "The player picked up the equipment");
    }

    @Test
    void playerFillAllAminoAcid() {
        // Arrange
        AminoAcidSlot aas = new AminoAcidSlot(1);
        aas.fillToMax();

        // Act
        player.fillAll(aas);

        // Assert
        assertEquals(1, player.getAminoAcidCount(), "The player got all the amino acids");
    }

    @Test
    void playerFillAllNucleotide() {
        // Arrange
        NucleotideSlot ns = new NucleotideSlot(1);
        ns.fillToMax();

        // Act
        player.fillAll(ns);

        // Assert
        assertEquals(1, player.getNucleotideCount(), "The player got all the nucleotides");
    }

    @Test
    void playerGive() {
        // Arrange
        Inventory i = new Inventory();
        Equipment e = new AxeEquipment();
        EquipmentSlot es = new EquipmentSlot();
        es.addEquipment(e);
        AminoAcidSlot aas = new AminoAcidSlot(5);
        aas.fillToMax();
        i.addSlot(es);
        i.addSlot(aas);

        // Act
        player.give(i);

        // Assert
        assertTrue(player.getEquipments().contains(e), "The player got the equipment from the inventory");
        assertEquals(5, player.getAminoAcidCount(), "The player got the amino acid from the inventory");
    }

    @Test
    void playerRemoveAgent() {
        // Arrange
        AgentSlot as = new AgentSlot();
        Agent a = mock(NumbingAgent.class);
        as.addAgent(a);
        player.fillAll(as);

        // Act
        player.removeAgent(a);

        // Assert
        assertFalse(player.getAgents().contains(a), "The agent has been removed from the player's agents.");
    }

    @Test
    void playerTakeActionCanTake() {
        // Arrange

        // Act

        // Assert
        assertTrue(player.takeAction(), "The player naturally can take action.");
    }

    @Test
    void playerTakeActionCanNotTake() {
        // Arrange
        AgentEffect ae = new NumbingAgentEffect();

        // Act
        player.addAgentEffect(ae);

        // Assert
        assertFalse(player.takeAction(), "The numbed player can not take action.");
    }

    @Test
    void playerAddEffect() {
        // Arrange
        Effect e = new NumbingAgentEffect();

        // Act
        player.addEffect(e);

        // Assert
        assertTrue(player.getEffects().contains(e), "Effects contains the added effect.");
        assertSame(e.player, player, "The effect is attached to the player.");
    }

    @Test
    void playerRemoveEffect() {
        // Arrange
        Effect e = new NumbingAgentEffect();
        player.addEffect(e);

        // Act
        player.removeEffect(e);

        // Assert
        assertFalse(player.getEffects().contains(e), "Effects does not include the added effect.");
        assertNull(e.player, "The effect is detached from the player.");
    }

    @Test
    void playerThrowAwayEquipment() {
        // Arrange
        EquipmentSlot es = new EquipmentSlot();
        Equipment e = mock(AxeEquipment.class);
        es.addEquipment(e);
        player.fillAll(es);

        // Act
        player.throwAwayEquipment(e);

        // Assert
        assertFalse(player.getEquipments().contains(e), "The equipment is no longer among the players equipments");

    }

    @Test
    void playerKill() {
        // Arrange
        Player player2kill = new Player("TestPlayer2kill");
        EquipmentSlot es = new EquipmentSlot();
        Equipment e = new AxeEquipment();
        es.addEquipment(e);
        player.fillAll(es);
        Field f = new FreeField();
        player.move(f);
        player2kill.move(f);

        // Act
        player.kill(player2kill);

        // Assert
        assertTrue(player2kill.isDead(), "The other player died.");
        assertFalse(player.isDead(), "The player is still alive.");
        assertFalse(f.getPlayers().contains(player2kill), "The dead player is no longer on the field.");
        assertTrue(f.getPlayers().contains(player), "The player is still on the field.");
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
    void playerInfectHandled() {
        // Arrange
        BearEffect be = new BearEffect();

        // Act
        player.infect(be);

        // Assert
        assertTrue(player.getEffects().contains(be), "The player was infected by the bear effect");
    }

    @Test
    void playerInfectUnHandled() {
        // Arrange
        BearEffect be = new BearEffect();
        Equipment e = new ProtectorEquipment();
        e.setDeterm(true, true);
        EquipmentSlot es = new EquipmentSlot();
        es.addEquipment(e);
        player.fillAll(es);

        // Act
        player.infect(be);

        // Assert
        assertFalse(player.getEffects().contains(be), "The player was not infected by the bear effect");
    }
}
