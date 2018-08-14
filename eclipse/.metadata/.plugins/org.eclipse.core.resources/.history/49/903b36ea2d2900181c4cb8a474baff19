package xyz.sethy.cd.exercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ExerciseLearner {
	private final File filePath;
	private final float easierPerBlockWalk;
	private final float easierPerBlockPlaced;
	private final float easierPerAttack;
	private final AtomicInteger blocksWalked;
	private final AtomicInteger blocksPlaced;
	private final AtomicInteger timesAttacked;
	
	public ExerciseLearner() {
		this.filePath = new File("./cd/exercise.json");
		this.easierPerBlockWalk = 1.03f;
		this.easierPerBlockPlaced = 1.02f;
		this.easierPerAttack = 1.01f;
		this.blocksWalked = new AtomicInteger();
		this.blocksPlaced = new AtomicInteger();
		this.timesAttacked = new AtomicInteger();
		
		load();
	}

	public void load() {
		File dir = new File("./cd/");
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		try (FileInputStream inputStream = new FileInputStream(this.filePath)) {
			String json = IOUtils.toString(inputStream);
			JsonObject object = new JsonParser().parse(json).getAsJsonObject();
			
			this.blocksWalked.set(object.get("blocksWalked").getAsInt());
			this.blocksPlaced.set(object.get("blocksPlaced").getAsInt());
			this.timesAttacked.set(object.get("timesAttacked").getAsInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		JsonObject object = new JsonObject();
		object.addProperty("blocksWalked", this.blocksWalked.get());
		object.addProperty("blocksPlaced", this.blocksPlaced.get());
		object.addProperty("timesAttacked", this.timesAttacked.get());
		String json = object.getAsJsonObject().getAsString();
		
		try (FileWriter writer = new FileWriter(this.filePath)) {
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public float getBlockWalkToUseEnergy() {
		 return 1 - ((this.blocksWalked.get() / this.easierPerBlockWalk) / (this.blocksWalked.get()  + 1));
	}
	
	public float getBlockPlaceToUseEnergy() {
		 return 1 - ((this.blocksPlaced.get() / this.easierPerBlockPlaced) / (this.blocksPlaced.get()  + 1));
	}
	
	public float getAttackToUseEnergy() {
		 return 1 - ((this.timesAttacked.get() / this.easierPerBlockWalk) / (this.timesAttacked.get()  + 1));
	}
	
	public AtomicInteger getBlocksWalked() {
		return this.blocksWalked;
	}
	
	public AtomicInteger getBlockedPlaced() {
		return this.blocksPlaced;
	}
	
	public AtomicInteger getTimesAttacked() {
		return this.timesAttacked;
	}
}
