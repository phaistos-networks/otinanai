package gr.phaistosnetworks.admin.otinanai;

import com.basho.riak.client.*;

import java.util.logging.*;
import java.util.*;

interface KeyWordTracker {
	public String getKeyWord() ;

   public short getType();

	public void put() ;

   public void delete() ;

   public void put(float value) ;

   public void put(long value) ;

   public void tick() ;

	public long getAlarm() ;

   public LinkedList<String> getMemory() ;

   public long getCurrentCount() ;
}
