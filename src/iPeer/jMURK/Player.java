package iPeer.jMURK;

import java.io.*;
import java.util.*;

public class Player {

	public Player(String n) {
			HP = 100;
			AP = 0;
			CC = 0;
			Wins = 0;
			Loses = 0;
			Coins = 0;
			FoundItems = 0;
			StealSuccess = 0;
			StealFail = 0;
			Name = n;
			SaveName = n;
	}
	public void save (int auto/*, Player plyr*/) 
	{
		System.out.println("Player: Attempting to save...");
		if (auto == 1)
			tempSaveName = "autosave";
		else
			tempSaveName = SaveName;	
	File sd = new File("saves/");
	if (!sd.exists())
		sd.mkdirs();
	File sf = new File(sd,tempSaveName+".msf");
	System.out.println(sd.getAbsolutePath());
	System.out.println(sf.getAbsolutePath());
	Properties sf2 = new Properties();
	try
	{
		sf2.put("HP", Integer.toString(HP));
		sf2.put("AP", Integer.toString(AP));
		sf2.put("CC", Integer.toString(CC));
		sf2.put("Wins", Integer.toString(Wins));
		sf2.put("Loses", Integer.toString(Loses));
		sf2.put("Coins", Integer.toString(Coins));
		sf2.put("FoundItems", Integer.toString(FoundItems));
		sf2.put("StealSuccess", Integer.toString(StealSuccess));
		sf2.put("StealFail", Integer.toString(StealFail));
		sf2.put("Name", Name);
		sf2.put("SaveName", tempSaveName);
		sf2.store(new FileOutputStream(sf), "jMURK Save Game");
		System.out.println("Player: Save successfull");
	}
	catch (Exception e) {
		ErrorHandler.e(1,"Unable to create save file: "+e);
		e.printStackTrace();
	}
	
	}



	public int HP, AP, CC, Wins, Loses, Coins, StealSuccess, StealFail, FoundItems;
	public String Name, SaveName, tempSaveName;
	
}
