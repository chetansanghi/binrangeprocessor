package com.worldpay;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import com.worldpay.model.BinRange;

public class BinRangeProcessor {

	private Set<BinRange> fileBinRangeSet = new TreeSet<BinRange>();
	private Set<BinRange> dbBinRangeSet = new TreeSet<BinRange>();

	private Set<BinRange> matchingDBAndFileBinRangeSet = new LinkedHashSet<BinRange>();
	private Set<BinRange> newInFileBinRangeSet = new LinkedHashSet<BinRange>();
	private Set<BinRange> potentialNewSplitRangeInFile = new LinkedHashSet<BinRange>();
	private Set<BinRange> potentialBroadenedBinRangeInFile = new LinkedHashSet<BinRange>();
	private Set<BinRange> rangeInDBButNotInFile = new LinkedHashSet<BinRange>();

	private int countDBBinRangeAccountedProcessed = 0;
	private int countDBBinRangeToAccountProcess = 0;	
	

	public void test2(){
		initializeFileBinRanges_test2();
		initializeDBBinRanges_test2();
		processBinRange();
/*		Num DB Ranges accounted/processed == 4
				Num DB Ranges to be accounted/processed == 4

				======= Matching DB and File Bin Ranges ===========
			BinRange [startIIN=456453, endIIN=456745, sizeIIN=6, issuerCode=null]
			BinRange [startIIN=454742, endIIN=454742, sizeIIN=6, issuerCode=null]
			BinRange [startIIN=454432, endIIN=454435, sizeIIN=6, issuerCode=null]
			BinRange [startIIN=454313, endIIN=455279, sizeIIN=6, issuerCode=null]
			====================END============================


			======= New in File and Not in DB =================
			BinRange [startIIN=459000, endIIN=459001, sizeIIN=6, issuerCode=null]
			BinRange [startIIN=457719, endIIN=457720, sizeIIN=6, issuerCode=null]
			BinRange [startIIN=453978, endIIN=453979, sizeIIN=6, issuerCode=null]
			BinRange [startIIN=453978, endIIN=453987, sizeIIN=6, issuerCode=null]
			====================END============================


			===== New Range Split in File from Range in DB ====
			BinRange [startIIN=456725, endIIN=456744, sizeIIN=6, issuerCode=null]
			BinRange [startIIN=454313, endIIN=454313, sizeIIN=6, issuerCode=null]
			====================END============================
*/
	}	
	
	public void test1(){
		initializeFileBinRanges_test1();
		initializeDBBinRanges_test1();
		processBinRange();
		//test-1
		//3 DB ranges should be in matchingDBAndFileBinRangeSet
		//BinRange [startIIN=456453, endIIN=456745, sizeIIN=6, issuerCode=null]
		//BinRange [startIIN=454742, endIIN=454742, sizeIIN=6, issuerCode=null]
		//BinRange [startIIN=454432, endIIN=454435, sizeIIN=6, issuerCode=null]
		//1 range in potentialNewSplitRangeInFile BinRange [startIIN=456725, endIIN=456744, sizeIIN=6, issuerCode=null]
		//12 ranges in newInFileBinRangeSet
	/*	BinRange [startIIN=459000, endIIN=459001, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=457719, endIIN=457720, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=454313, endIIN=454313, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=454313, endIIN=455279, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=453978, endIIN=453979, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=453978, endIIN=453987, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=452044, endIIN=452046, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=450875, endIIN=450875, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=446200, endIIN=446299, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=445803, endIIN=445803, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=445025, endIIN=445025, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=444001, endIIN=444008, sizeIIN=6, issuerCode=null]
	*/
	}

	
	public void initializeFileBinRanges_test1(){
		fileBinRangeSet.add(new BinRange(new Integer(459000), new Integer(459001), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(457719), new Integer(457720), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(456725), new Integer(456744), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(456453), new Integer(456745), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454742), new Integer(454742), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454432), new Integer(454435), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454313), new Integer(454313), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454313), new Integer(455279), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(453978), new Integer(453979), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(453978), new Integer(453987), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(452044), new Integer(452046), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(450875), new Integer(450875), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(446200), new Integer(446299), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(445803), new Integer(445803), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(445025), new Integer(445025), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(444001), new Integer(444008), new Integer(6)));
		// Iterating Set using forEach() in Java 8
		System.out.println("Iterating Set using forEach() in Java 8\n");
		fileBinRangeSet.forEach(binRange -> System.out.println(binRange));
	}
	

	public void test0(){
		initializeFileBinRanges();
		initializeDBBinRanges();
		processBinRange();
/*		Num DB Ranges accounted/processed == 7
				Num DB Ranges to be accounted/processed == 7
				======= Matching DB and File Bin Ranges ===========
				BinRange [startIIN=456453, endIIN=456745, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=454742, endIIN=454742, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=454432, endIIN=454435, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=454313, endIIN=455279, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=453978, endIIN=453987, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=446200, endIIN=446299, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=444001, endIIN=444008, sizeIIN=6, issuerCode=null]
				====================END============================
				======= New in File and Not in DB =================
				BinRange [startIIN=459000, endIIN=459001, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=457719, endIIN=457720, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=452044, endIIN=452046, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=450875, endIIN=450875, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=445803, endIIN=445803, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=445025, endIIN=445025, sizeIIN=6, issuerCode=null]
				====================END============================
				===== New Range Split in File from Range in DB ====
				BinRange [startIIN=456725, endIIN=456744, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=454313, endIIN=454313, sizeIIN=6, issuerCode=null]
				BinRange [startIIN=453978, endIIN=453979, sizeIIN=6, issuerCode=null]
				====================END============================
*/		
	}

	// initialize fileBinRangeSet, sorted by startIIN descending
	public void initializeFileBinRanges_test2() {

		fileBinRangeSet.add(new BinRange(new Integer(459000), new Integer(459001), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(457719), new Integer(457720), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(456725), new Integer(456744), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(456453), new Integer(456745), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454742), new Integer(454742), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454432), new Integer(454435), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454313), new Integer(454313), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454313), new Integer(455279), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(453978), new Integer(453979), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(453978), new Integer(453987), new Integer(6)));
		// Iterating Set using forEach() in Java 8
		System.out.println("Iterating Set using forEach() in Java 8\n");
		fileBinRangeSet.forEach(binRange -> System.out.println(binRange));
	}
	
	// initialize fileBinRangeSet, sorted by startIIN descending
	public void initializeFileBinRanges() {

		fileBinRangeSet.add(new BinRange(new Integer(459000), new Integer(459001), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(457719), new Integer(457720), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(456725), new Integer(456744), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(456453), new Integer(456745), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454742), new Integer(454742), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454432), new Integer(454435), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454313), new Integer(454313), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(454313), new Integer(455279), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(453978), new Integer(453979), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(453978), new Integer(453987), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(452044), new Integer(452046), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(450875), new Integer(450875), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(446200), new Integer(446299), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(445803), new Integer(445803), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(445025), new Integer(445025), new Integer(6)));
		fileBinRangeSet.add(new BinRange(new Integer(444001), new Integer(444008), new Integer(6)));

		// Iterating Set using forEach() in Java 8
		System.out.println("Iterating Set using forEach() in Java 8\n");
		fileBinRangeSet.forEach(binRange -> System.out.println(binRange));

		
/*		BinRange[] binRangesFile = (BinRange[]) fileBinRangeSet.toArray(new BinRange[0]);
		Arrays.sort(binRangesFile);
		for (BinRange binRangeObj : binRangesFile) {
			System.out.println("Bin Range: " + binRangeObj);
		}*/		 

	}

	// initialize dbBinRangeSet, sorted by startIIN descending
	public void initializeDBBinRanges_test2() {

		dbBinRangeSet.add(new BinRange(new Integer(456453), new Integer(456745), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(454742), new Integer(454742), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(454432), new Integer(454435), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(454313), new Integer(455279), new Integer(6)));
		// Iterating Set using forEach() in Java 8
		System.out.println("Iterating Set using forEach() in Java 8\n");
		dbBinRangeSet.forEach(binRange -> System.out.println(binRange));
	}
	
	
	// initialize dbBinRangeSet, sorted by startIIN descending
	public void initializeDBBinRanges_test1(){

		dbBinRangeSet.add(new BinRange(new Integer(456453), new Integer(456745), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(454742), new Integer(454742), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(454432), new Integer(454435), new Integer(6)));

		// Iterating Set using forEach() in Java 8
		System.out.println("Iterating Set using forEach() in Java 8\n");
		dbBinRangeSet.forEach(binRange -> System.out.println(binRange));

	}

	// initialize dbBinRangeSet, sorted by startIIN descending
	public void initializeDBBinRanges() {

		dbBinRangeSet.add(new BinRange(new Integer(456453), new Integer(456745), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(454742), new Integer(454742), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(454432), new Integer(454435), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(454313), new Integer(455279), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(453978), new Integer(453987), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(446200), new Integer(446299), new Integer(6)));
		dbBinRangeSet.add(new BinRange(new Integer(444001), new Integer(444008), new Integer(6)));

		// Iterating Set using forEach() in Java 8
		System.out.println("Iterating Set using forEach() in Java 8\n");
		dbBinRangeSet.forEach(binRange -> System.out.println(binRange));

	}

	public void processBinRange() {

		Iterator<BinRange> dbBinRangeIterator = dbBinRangeSet.iterator();

		BinRange dbBinRangeCurrentPointer = null;
		countDBBinRangeToAccountProcess = dbBinRangeSet.size();
		System.out.println("Num DB Ranges to Account/Process = " + countDBBinRangeToAccountProcess);

		int countForNumFileRangesAnalyzedForADBRange = 0;
		Iterator<BinRange> fileBinRangeIterator = fileBinRangeSet.iterator();

		while (dbBinRangeIterator.hasNext()) {
			dbBinRangeCurrentPointer = dbBinRangeIterator.next();
			System.out.println("DB Bin Range being accounted/processed == " + dbBinRangeCurrentPointer);

			BinRange fileBinRangeCurrentPointer = null;
			boolean donotChangeFilBinRangePointer = false;
			boolean dbRangeAnalysisComplete = false;
			
			while (fileBinRangeIterator.hasNext()) {

				// if( donotChangeFilBinRangePointer == false ){
				// fileBinRangeCurrentPointer = fileBinRangeIterator.next();
				// }

				//for above code, not sure when the flag once set to true would become false
				fileBinRangeCurrentPointer = fileBinRangeIterator.next();

				System.out.println("File Bin Range being compared/processed == " + fileBinRangeCurrentPointer);
				System.out.println(
						"DB Bin Range being accounted/processed(inside file loop) == " + dbBinRangeCurrentPointer);

				// case of matching range as it is in DB and file and not known
				// whether to add or delete
				if ((dbBinRangeCurrentPointer.getStartIIN().intValue() == fileBinRangeCurrentPointer.getStartIIN()
						.intValue())
						&& dbBinRangeCurrentPointer.getEndIIN().intValue() == fileBinRangeCurrentPointer.getEndIIN()
								.intValue()) {
					// Log/System Out which range processed
					System.out.println("DB Range Accounted/Processed(Same as File) == " + dbBinRangeCurrentPointer);
					System.out.println("File Range Accounted/Processed(Same as DB) == " + fileBinRangeCurrentPointer);
					countDBBinRangeAccountedProcessed++;
					countForNumFileRangesAnalyzedForADBRange = 0;
					System.out.println("Matching DB Ranges in File and DB == " + dbBinRangeCurrentPointer);
					matchingDBAndFileBinRangeSet.add(dbBinRangeCurrentPointer);
					if (dbBinRangeIterator.hasNext()) {
						dbBinRangeCurrentPointer = dbBinRangeIterator.next();
					} else {
						System.out.println("No more DB Ranges to process/validate");
						// what if there are file ranges in set , what to do,
						// which set to add
						//add to new file set

						//a flag to set that DB ranges analyzed have been completed to true
						dbRangeAnalysisComplete = true;
						
						while(fileBinRangeIterator.hasNext()){
							fileBinRangeCurrentPointer = fileBinRangeIterator.next();
							System.out.println("New Bin Range in File Not in DB == " + fileBinRangeCurrentPointer);
							newInFileBinRangeSet.add(fileBinRangeCurrentPointer);
						}
						break;
					}
				} 
				//Start - Broadened Ranges in File than range present in DB 
				//Comment - added >= instead of == for StartIIN comparison
				else if ((dbBinRangeCurrentPointer.getStartIIN().intValue() >= fileBinRangeCurrentPointer
						.getStartIIN().intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() < fileBinRangeCurrentPointer.getEndIIN()
								.intValue())
						&& (countForNumFileRangesAnalyzedForADBRange == 0)) {
					// case of broadened range in file in comparison to DB
					System.out.println(
							"File Range Accounted/Processed(Broadened - cnt == 0) == " + fileBinRangeCurrentPointer);
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("countForNumFileRangesAnalyzedForADBRange == " + countForNumFileRangesAnalyzedForADBRange);					
					potentialBroadenedBinRangeInFile.add(fileBinRangeCurrentPointer);
				} 
				//Comment - added >= instead of == for StartIIN comparison				
				else if ((dbBinRangeCurrentPointer.getStartIIN().intValue() >= fileBinRangeCurrentPointer
						.getStartIIN().intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() < fileBinRangeCurrentPointer.getEndIIN()
								.intValue())
						&& (countForNumFileRangesAnalyzedForADBRange != 0)) {
					// case of broadened range in file in comparison to DB
					System.out.println(
							"File Range Accounted/Processed(Broadened - cnt != 0) == " + fileBinRangeCurrentPointer);
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("countForNumFileRangesAnalyzedForADBRange == " + countForNumFileRangesAnalyzedForADBRange);
				
					potentialBroadenedBinRangeInFile.add(fileBinRangeCurrentPointer);
				} 
				//End - Broadened Ranges in File than range present in DB
				else if ((dbBinRangeCurrentPointer.getStartIIN().intValue() < fileBinRangeCurrentPointer.getStartIIN()
						.intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() < fileBinRangeCurrentPointer.getEndIIN()
								.intValue())
						&& (countForNumFileRangesAnalyzedForADBRange == 0)) {

					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("countForNumFileRangesAnalyzedForADBRange == " + countForNumFileRangesAnalyzedForADBRange);					
					// case of new range in file which is not in DB as it is
					System.out.println("New BIN Range in File(cnt == 0) == " + fileBinRangeCurrentPointer);
					newInFileBinRangeSet.add(fileBinRangeCurrentPointer);
				} else if ((dbBinRangeCurrentPointer.getStartIIN().intValue() < fileBinRangeCurrentPointer.getStartIIN()
						.intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() < fileBinRangeCurrentPointer.getEndIIN()
								.intValue())
						&& (countForNumFileRangesAnalyzedForADBRange != 0)) {

					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("countForNumFileRangesAnalyzedForADBRange == " + countForNumFileRangesAnalyzedForADBRange);					
					// case of new range in file which is not in DB as it is
					System.out.println("New BIN Range in File(cnt != 0) == " + fileBinRangeCurrentPointer);
					newInFileBinRangeSet.add(fileBinRangeCurrentPointer);
				} else if ((dbBinRangeCurrentPointer.getStartIIN().intValue() < fileBinRangeCurrentPointer.getStartIIN()
						.intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() < fileBinRangeCurrentPointer.getEndIIN()
								.intValue())
						&& (countForNumFileRangesAnalyzedForADBRange != 0)) {
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("countForNumFileRangesAnalyzedForADBRange == " + countForNumFileRangesAnalyzedForADBRange);					
					// case of new range in file which is not in DB as it is
					System.out.println("New BIN Range in File(cnt!=0) == " + fileBinRangeCurrentPointer);
					newInFileBinRangeSet.add(fileBinRangeCurrentPointer);
				} else if ((dbBinRangeCurrentPointer.getStartIIN().intValue() <= fileBinRangeCurrentPointer
						.getStartIIN().intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() <= fileBinRangeCurrentPointer.getEndIIN()
								.intValue())) {
					// case of potential bin range which is in DB but spilt in
					// file for finer granulity
					// do we need comparison for cnt as 0 and cnt as not 0 as
					// two separate else if here???
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("countForNumFileRangesAnalyzedForADBRange == " + countForNumFileRangesAnalyzedForADBRange);					
					System.out.println("Bin Range Splitted in File than DB == " + fileBinRangeCurrentPointer);
					potentialNewSplitRangeInFile.add(fileBinRangeCurrentPointer);
				} else if (
				// debatable whether >= or just >, preferred >
				//Comment - Redundant as covered in broadened range cases above
				(dbBinRangeCurrentPointer.getStartIIN().intValue() > fileBinRangeCurrentPointer.getStartIIN()
						.intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() < fileBinRangeCurrentPointer.getEndIIN()
								.intValue())) {
					// case of broaden bin range in file, could be add or delete
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("countForNumFileRangesAnalyzedForADBRange == " + countForNumFileRangesAnalyzedForADBRange);					
					System.out.println("Broadened Bin Range in File than DB == " + fileBinRangeCurrentPointer);
					potentialBroadenedBinRangeInFile.add(fileBinRangeCurrentPointer);
				} else if ((dbBinRangeCurrentPointer.getStartIIN().intValue() == fileBinRangeCurrentPointer
						.getStartIIN().intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() > fileBinRangeCurrentPointer.getEndIIN()
								.intValue())
						&& (countForNumFileRangesAnalyzedForADBRange == 0)) {

					// case of new BIN Range in File
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("countForNumFileRangesAnalyzedForADBRange == " + countForNumFileRangesAnalyzedForADBRange);					
					System.out.println("Bin Range Splitted in File than DB(cnt == 0) == " + fileBinRangeCurrentPointer);
					potentialNewSplitRangeInFile.add(fileBinRangeCurrentPointer);
				} else if ((dbBinRangeCurrentPointer.getStartIIN().intValue() == fileBinRangeCurrentPointer
						.getStartIIN().intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() > fileBinRangeCurrentPointer.getEndIIN()
								.intValue())
						&& (countForNumFileRangesAnalyzedForADBRange != 0)) {

					// case of new BIN Range in File
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("countForNumFileRangesAnalyzedForADBRange == " + countForNumFileRangesAnalyzedForADBRange);					
					System.out.println("Bin Range Splitted in File than DB(cnt != 0) == " + fileBinRangeCurrentPointer);
					potentialNewSplitRangeInFile.add(fileBinRangeCurrentPointer);
				} else if ((dbBinRangeCurrentPointer.getStartIIN().intValue() <= fileBinRangeCurrentPointer
						.getStartIIN().intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() >= fileBinRangeCurrentPointer.getEndIIN()
								.intValue())) {

					// case of split bin range only in file but not in DB
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("countForNumFileRangesAnalyzedForADBRange == " + countForNumFileRangesAnalyzedForADBRange);					
					System.out.println("Bin Range Splitted in File than DB == " + fileBinRangeCurrentPointer);
					potentialNewSplitRangeInFile.add(fileBinRangeCurrentPointer);
				} else if (
				// debatable >= or >, preferred would be >
				(dbBinRangeCurrentPointer.getStartIIN().intValue() < fileBinRangeCurrentPointer.getStartIIN()
						.intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() < fileBinRangeCurrentPointer.getEndIIN()
								.intValue())) {

					// case of new range in file and not in DB
					countForNumFileRangesAnalyzedForADBRange = 0;
					System.out.println("New Bin Range in File == " + fileBinRangeCurrentPointer);
					newInFileBinRangeSet.add(fileBinRangeCurrentPointer);
					// confusion with regards to moving DB or File Pointer
					// confusion with regards to what is meant by add range in
					// file set

				} else if (
				// debatable whether >= or >, preferred >
				(dbBinRangeCurrentPointer.getStartIIN().intValue() > fileBinRangeCurrentPointer.getStartIIN()
						.intValue())
						&& (dbBinRangeCurrentPointer.getEndIIN().intValue() > fileBinRangeCurrentPointer.getEndIIN()
								.intValue())
						&& (dbRangeAnalysisComplete == false )) {
					// not sure whether in condition above we should check
					// cntNumRangeAnalyzedDB == 0 or != 0
					countForNumFileRangesAnalyzedForADBRange = 0;
					System.out.println("*****Range in DB and not in File == " + dbBinRangeCurrentPointer);
					rangeInDBButNotInFile.add(dbBinRangeCurrentPointer);
					countDBBinRangeAccountedProcessed++;
					if (dbBinRangeIterator.hasNext()) {
						dbBinRangeCurrentPointer = dbBinRangeIterator.next();
						donotChangeFilBinRangePointer = true;
					} else {
						// no more ranges in DB left
						// need to check when this happens
						// if last db range in set, do we add the existing file
						// bin range and remaining to the rangeInDBButNotInFile
						System.out.println("*****Range in DB and not in File == " + fileBinRangeCurrentPointer);
						rangeInDBButNotInFile.add(fileBinRangeCurrentPointer);
						while (fileBinRangeIterator.hasNext()) {
							fileBinRangeCurrentPointer = fileBinRangeIterator.next();
							System.out.println("*****Range in DB and not in File == " + fileBinRangeCurrentPointer);
							rangeInDBButNotInFile.add(fileBinRangeCurrentPointer);
						}
					}
				} else {
					System.out.println("Unhandled case of BIN Range for File and DB.");
					System.out.println("Bin Range in DB == " + dbBinRangeCurrentPointer);
					System.out.println("Bin Range in File == " + fileBinRangeCurrentPointer);
				}

			} // end of FileBinRangeIterator
			System.out.println("End of File Bin Range, Going for next DB Bin Range.............");
			//Add basically current range for DB being iterated and remaining into DB Range not in File
			//when dbanalysis is not complete, if complete don't need to add to DB ranges not in File
			//as the current range has been accounted for DB in matching DB and File else if
			if( dbRangeAnalysisComplete == false ){
				System.out.println("*****Range in DB and not in File == " + dbBinRangeCurrentPointer);
				rangeInDBButNotInFile.add(dbBinRangeCurrentPointer);
				countDBBinRangeAccountedProcessed++;
			}
		} // end of DB Bin Range Iterator

		System.out.println("Num DB Ranges accounted/processed == " + countDBBinRangeAccountedProcessed);
		System.out.println("Num DB Ranges to be accounted/processed == " + countDBBinRangeToAccountProcess);
		System.out.println("======= Matching DB and File Bin Ranges ===========");
		matchingDBAndFileBinRangeSet.forEach(binRange -> System.out.println(binRange));
		System.out.println("====================END============================");
		System.out.println();
		System.out.println();

		System.out.println("======= New in File and Not in DB =================");
		newInFileBinRangeSet.forEach(binRange -> System.out.println(binRange));
		System.out.println("====================END============================");
		System.out.println();
		System.out.println();

		System.out.println("===== New Range Split in File from Range in DB ====");
		potentialNewSplitRangeInFile.forEach(binRange -> System.out.println(binRange));
		System.out.println("====================END============================");
		System.out.println();
		System.out.println();

		System.out.println("======= Bin Range broaded in File for Range in DB== ");
		potentialBroadenedBinRangeInFile.forEach(binRange -> System.out.println(binRange));
		System.out.println("====================END============================");
		System.out.println();
		System.out.println();

		System.out.println("======= Not in File but in DB ===================== ");
		rangeInDBButNotInFile.forEach(binRange -> System.out.println(binRange));
		System.out.println("====================END============================");
		System.out.println();
		System.out.println();

	}

	public static void main(String[] args) {

		BinRangeProcessor binRangeProcessor = new BinRangeProcessor();
//		binRangeProcessor.initializeFileBinRanges();
//		binRangeProcessor.initializeDBBinRanges();
//		binRangeProcessor.processBinRange();
		
//		binRangeProcessor.test0();
		binRangeProcessor.test1();
//		binRangeProcessor.test2();
		
		
	}

}
