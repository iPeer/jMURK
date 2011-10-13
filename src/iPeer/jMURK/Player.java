package iPeer.jMURK;

import java.io.*;
import java.util.*;

public class Player {
	@SuppressWarnings("unchecked") // It's mixed data, so this shuts the compiler up about errors.
	public Player(String n) {
			p.put("HP", Integer.toString(100));
			p.put("AP", Integer.toString(0));
			p.put("CC", Integer.toString(0));
			p.put("Wins", Integer.toString(0));
			p.put("Loses", Integer.toString(0));
			p.put("Coins", Integer.toString(0));
			p.put("FoundItems", Integer.toString(0));
			p.put("StealSuccess", Integer.toString(0));
			p.put("StealFail", Integer.toString(0));
			p.put("Name", n);
			p.put("SaveName", n);
			/*CC = 0;
			Wins = 0;
			Loses = 0;
			Coins = 0;
			FoundItems = 0;
			StealSuccess = 0;
			StealFail = 0;
			Name = n;
			SaveName = n;*/
	}
	@SuppressWarnings("unchecked")
	public void save (int auto) 
	{
		System.out.println("Player: Attempting to save...");
		if (auto == 1)
			tempSaveName = "autosave";
		else
			tempSaveName = getSaveName();	
	File sd = new File("saves/");
	if (!sd.exists())
		sd.mkdirs();
	File sf = new File(sd,tempSaveName+".msf");
	System.out.println(sd.getAbsolutePath());
	System.out.println(sf.getAbsolutePath());
	Properties sf2 = new Properties();
	try
	{
		sf2.putAll(p);
		sf2.store(new FileOutputStream(sf), "jMURK Save Game");
		System.out.println("Player: Save successfull");
	}
	catch (Exception e) {
		ErrorHandler.e(1,"Unable to create save file: "+e);
		e.printStackTrace();
	}
	
	}

	public String getSaveName() {
		return p.get("Save Name").toString();
	}


	public int HP, AP, CC, Wins, Loses, Coins, StealSuccess, StealFail, FoundItems;
	public String Name, SaveName, tempSaveName;
	@SuppressWarnings("rawtypes")
	public Hashtable p = new Hashtable();
	
}
