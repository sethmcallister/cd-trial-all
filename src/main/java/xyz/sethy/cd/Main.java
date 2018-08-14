package xyz.sethy.cd;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import xyz.sethy.cd.exercise.ExerciseLearner;
import xyz.sethy.cd.gui.ExerciseGUI;
import xyz.sethy.cd.gui.WaterGUI;
import xyz.sethy.cd.item.EnergyDrinkItem;
import xyz.sethy.cd.listener.EntityConstructingListener;
import xyz.sethy.cd.listener.EntityJoinWorldListener;
import xyz.sethy.cd.listener.GUIOpenListener;
import xyz.sethy.cd.listener.PlayerBlockBreakListener;
import xyz.sethy.cd.listener.PlayerInteractListener;
import xyz.sethy.cd.listener.WorldSaveListener;
import xyz.sethy.cd.packet.WaterPacketHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

// Crates a Network mod that requires a client side, but not a server side, it opens the channel 'extendendPlayer', and the packet hander is that of the WaterPacketHander class
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels = {"extendedPlayer"}, packetHandler = WaterPacketHandler.class)
// Creates the mod, with the id of the MODID variable, with the name of the same variable, and the version with the variable of VERSION
@Mod(modid = Main.MODID, name = Main.MODID, version = Main.VERSION)
public class Main {
	// Assign the MODID variable to 'CD'
    public static final String MODID = "CD";
    // Assigns the VERSION variable to '1.0'
    public static final String VERSION = "1.0";
    // Assigns the LOGGER variable to a new logger with the name of 'CD'
    public static final Logger LOGGER = Logger.getLogger(MODID); 
    // Creates a variable with the name instance, and the type of the Main class
    private static Main instance;
    // Creates a variable of the ExerciseLearner, to be set later
    private ExerciseLearner exerciseLearner;
    // Creates an Item variable, with the name energyDrink, to be set later
    private Item energyDrink;
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	// Sets the instance to this class
    	setInstance(this);
    	// Sets the exerciseLeaner to a new ExerciseLearner class instance
    	this.exerciseLearner = new ExerciseLearner();
    	
    	// Registers both the GUIS
    	MinecraftForge.EVENT_BUS.register(new WaterGUI());
    	MinecraftForge.EVENT_BUS.register(new ExerciseGUI());

    	// Registers all the of listeners inside the listeners package
    	MinecraftForge.EVENT_BUS.register(new EntityConstructingListener());
    	MinecraftForge.EVENT_BUS.register(new EntityJoinWorldListener());
    	MinecraftForge.EVENT_BUS.register(new PlayerInteractListener());
    	MinecraftForge.EVENT_BUS.register(new PlayerBlockBreakListener());
    	MinecraftForge.EVENT_BUS.register(new GUIOpenListener());
    	MinecraftForge.EVENT_BUS.register(new WorldSaveListener());
   
    	// Assigns the energyDrink variable to a instance of an EnergyDrinkItem class
    	this.energyDrink = new EnergyDrinkItem();
    }
   
    // Just getters and setters from here
    private static void setInstance(Main newInstance) {
    	instance = newInstance;
    }
    
    public static Main getInstance() {
    	return instance;
    }
    
    public ExerciseLearner getExerciseLearner() {
    	return this.exerciseLearner;
    }
}
