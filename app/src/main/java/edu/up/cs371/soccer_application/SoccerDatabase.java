package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {
    private Hashtable<String,SoccerPlayer> players = new Hashtable<String,SoccerPlayer>();
    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        String name = firstName + "_" + lastName;
        if(players.containsKey(name)) { return false; }
        SoccerPlayer newPlayer = new SoccerPlayer(firstName,lastName,uniformNumber,teamName);
        players.put(name,newPlayer);
        return true;
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String name = firstName + "_" + lastName;
        if(getPlayer(firstName,lastName)!=null) {
            players.remove(name);
            return true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        String name = firstName + "_" + lastName;
        return players.get(name);
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        SoccerPlayer player = getPlayer(firstName,lastName);
        if(player!=null) {
            player.bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        SoccerPlayer player = getPlayer(firstName,lastName);
        if(player!=null) {
            player.bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        SoccerPlayer player = getPlayer(firstName,lastName);
        if(player!=null) {
            player.bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        SoccerPlayer player = getPlayer(firstName,lastName);
        if(player!=null) {
            player.bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        SoccerPlayer player = getPlayer(firstName,lastName);
        if(player!=null) {
            player.bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        SoccerPlayer player = getPlayer(firstName,lastName);
        if(player!=null) {
            player.bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        SoccerPlayer player = getPlayer(firstName,lastName);
        if(player!=null) {
            player.bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        int count = 0;
        if(teamName==null)
            return players.size();
        for(SoccerPlayer s : players.values()) {
            if(s.getTeamName().equals(teamName)){
                count++;
            }
        }
        return count;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int count = 0;
        for(SoccerPlayer s : players.values()) {
            if(count == idx && (s.getTeamName().equals(teamName) || teamName == null)) {
                return s;
            }
            if(teamName == null) {
                count++;
            }
            else if(s.getTeamName().equals(teamName)){
                count++;
            }

        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        try {
            Scanner s = new Scanner(file);
            while(s.hasNext()) {
                String first = s.nextLine();
                String last = s.nextLine();
                int uni = s.nextInt();
                s.nextLine();
                String team = s.nextLine();
                SoccerPlayer sp = new SoccerPlayer(first,last,uni,team);
                int goals = s.nextInt();
                s.nextLine();
                int fouls = s.nextInt();
                s.nextLine();
                int assists = s.nextInt();
                s.nextLine();
                int shots = s.nextInt();
                s.nextLine();
                int yellowCards = s.nextInt();
                s.nextLine();
                int redCards = s.nextInt();
                s.nextLine();
                int saves = s.nextInt();
                s.nextLine();
                for(int i=0;i<goals;i++) {
                    sp.bumpGoals();
                }
                for(int i=0;i<fouls;i++){
                    sp.bumpFouls();
                }
                for(int i=0;i<assists;i++) {
                    sp.bumpAssists();
                }
                for(int i=0;i<shots;i++) {
                    sp.bumpShots();
                }
                for(int i=0;i<yellowCards;i++) {
                    sp.bumpYellowCards();
                }
                for(int i=0;i<redCards;i++) {
                    sp.bumpRedCards();
                }
                for(int i=0;i<saves;i++) {
                    sp.bumpSaves();
                }
                players.put(first+"_"+last, sp);
            }
            s.close();
            return file.exists();
        }
        catch (Exception e) {
            Log.i("EXCEPTION",e.toString());
        }
        Log.i("stuff:", ""+file.exists());
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for(SoccerPlayer s : players.values()) {
                pw.println(logString(s.getFirstName()));
                pw.println(logString(s.getLastName()));
                pw.println(logString(s.getUniform()+""));
                pw.println(logString(s.getTeamName()));
                pw.println(logString(s.getGoals()+""));
                pw.println(logString(s.getFouls()+""));
                pw.println(logString(s.getAssists()+""));
                pw.println(logString(s.getShots()+""));
                pw.println(logString(s.getYellowCards()+""));
                pw.println(logString(s.getRedCards()+""));
                pw.println(logString(s.getSaves()+""));
            }
            pw.close();
            return true;
        }
        catch(Exception e) {
        }
        return false;
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        //Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        HashSet<String> teams = new HashSet<String>();
        for(SoccerPlayer s : players.values()) {
            String tm = s.getTeamName();
            if(!teams.contains(tm)) {
                teams.add(tm);
            }
        }
        return teams;
	}

}
