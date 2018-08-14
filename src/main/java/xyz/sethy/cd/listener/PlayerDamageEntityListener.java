package xyz.sethy.cd.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import xyz.sethy.cd.Main;
import xyz.sethy.cd.extended.ExtendedPlayer;

public class PlayerDamageEntityListener {
	private final float energyNeededToAttack = 0.8f;
	
	@ForgeSubscribe
	public void onPlayerDamageEntity(final LivingAttackEvent event) {
		// Checks if the source of the entity is the instanceof an EntityPlayer, if its not then it turns
		if ((event.source.getEntity() instanceof EntityPlayer))
			return;
		
		// Checks if the name of the player who attacked is the same as the player who's playing on this client, if it's not then it returns
		EntityPlayer attacked = (EntityPlayer) event.source.getEntity();
		if (!attacked.getEntityName().equals(Minecraft.getMinecraft().thePlayer.getEntityName()))
			return;
		
		// Gets the ExtenedPlayer object, and gets if they have enough energy to damage an entity, if not a message is sent to the player, and the event is cancelled
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		ExtendedPlayer extendedPlayer = ExtendedPlayer.get(player);
		if (extendedPlayer.getCurrentExerciseLevel() < this.energyNeededToAttack) {
			player.addChatMessage("You are too tired to hurt anybody; please rest or have something to eat.");
			event.setCanceled(true);
			return;
		}
		
		// Increments the amount of times that the player has attacked an entity
		Main.getInstance().getExerciseLearner().getTimesAttacked().addAndGet(1);
		// Works out how much energy to use when the player attacked the entity
		float energyUsed = Main.getInstance().getExerciseLearner().getAttackToUseEnergy();
		// Will deduct how much energy is used from the player
		extendedPlayer.useExerciseEnergy(energyUsed);
	}
}
