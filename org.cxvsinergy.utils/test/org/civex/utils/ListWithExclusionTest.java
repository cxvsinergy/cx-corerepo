package org.civex.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.civex.demo.*;
import org.civex.utils.collections.ListWithExclusion;
import org.civex.utils.collections.SeqList;

import junit.framework.TestCase;

public class ListWithExclusionTest extends TestCase
{
		public void testMe2()
		{
			List<Double> l=new ArrayList<Double>(10);
			for (int ii=0;ii<52;ii++) l.add(ii*1D);
			ListWithExclusion<Double> exl=new ListWithExclusion<Double>(l);
			assertEquals(exl.size(),52);
			exl.remove(new Double(10));
			exl.remove(new Double(20));
			assertEquals(exl.size(),50);
			assertEquals(exl.get(10),new Double(51));
			assertEquals(exl.get(20),new Double(50));
			assertTrue(exl.contains(new Double(50)));
			assertFalse(exl.contains(new Double(10)));
			assertFalse(exl.contains(new Double(20)));
			exl.recover(new Double(20));
			assertEquals(exl.size(),51);
			assertEquals(exl.get(50),new Double(20));
		}
		
		
		
		
		public void testPocker()
		{
			for (int ii=3;ii<8;ii++)
				playPockerPairStats(ii,10000);
		}	
			public void playPockerPairStats(int players, int trials)
			{
			List<Integer> l=new ArrayList(SeqList.createCardDeckList());
			ListWithExclusion<Integer> deck=new ListWithExclusion<Integer>(l);
			final PokerPlayer player[]=new PokerPlayer[players];
			for (int ii=0;ii<player.length;ii++) player[ii]=new PokerPlayer();
			int flop_pair=0;
			int pre_flop=0;
			int flop_flop=0;
			int tripplet=0;
			
			final Random r=new Random();
			for (int ii=0;ii<trials;ii++)
			{
				
				//player[0].k1=deck.remove(10);
				//player[0].k2=deck.remove(36);
				//assertEquals(50,deck.size());
				//deck.remove(Integer.valueOf(11));
				//deck.remove(Integer.valueOf(37));
				
				
				for (int p=0;p<players;p++)
				{
					player[p].k1=deck.removeRandomElement(r);
					player[p].k2=deck.removeRandomElement(r);
				}
				//
				int flop1=deck.removeRandomElement(r);
				int flop2=deck.removeRandomElement(r);
				int flop3=deck.removeRandomElement(r);
				//
				
				//assertEquals(45,deck.size());
				
				if (flop1%13==flop2%13 || flop2%13==flop3%13 || flop1%13==flop3%13) flop_pair++;
				
				// play flop
				for (int p=0;p<players;p++)
				{
					if (player[p].hasPair()) pre_flop++;
					if (player[p].hasPair(flop1)) { flop_flop++;break;}
					if (player[p].hasPair(flop2)) { flop_flop++;break;}
					if (player[p].hasPair(flop3)) { flop_flop++;break;}
				}
				
				int turn=deck.removeRandomElement(r);
				int river=deck.removeRandomElement(r);
				
				deck.renew();
				assertEquals(52,deck.size());
			}
			StringBuilder bld=new StringBuilder(1000);
			bld.append(" players=").append(players).append(' ');
			appendPct(bld,"pairs0",pre_flop*1D/trials);
			appendPct(bld,"flop-pair",flop_pair*1D/trials);
			appendPct(bld,"flop-flop",flop_flop*1D/(trials*3));
			System.out.println(bld);

		}
			
			
		protected void appendPct(StringBuilder bld, String name, double v)
		{
			v=((int)(v*1000))/10D;
			bld.append(name).append('=').append(v).append("% ");
		}
			
	 
}
