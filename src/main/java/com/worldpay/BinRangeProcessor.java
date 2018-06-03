package com.worldpay;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import com.worldpay.model.BinRange;

public class BinRangeProcessor {

	private Set<BinRange> fileBinRangeSet = new TreeSet<BinRange>();
	private Set<BinRange> dbBinRangeSet = new TreeSet<BinRange>();

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

/*        BinRange[] binRangesFile = (BinRange[])fileBinRangeSet.toArray(new BinRange[0]);
        Arrays.sort(binRangesFile);
        for(BinRange binRangeObj : binRangesFile){
        	System.out.println("Bin Range: " + binRangeObj);
        }
*/
        
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

/*		Iterator<BinRange> dbBinRangeIterator = dbBinRangeSet.iterator();

		BinRange dbBinRangeCurrentPointer = null;

		Set<BinRange> matchingDBAndFileBinRangeSet = new LinkedHashSet<BinRange>();
		Set<BinRange> newInFileBinRangeSet = new LinkedHashSet<BinRange>();
		Set<BinRange> potentialNewSplitRangeInFile = new LinkedHashSet<BinRange>();
		Set<BinRange> potentialBroadenedBinRangeInFile = new LinkedHashSet<BinRange>();
		Set<BinRange> rangeInDBButNotInFile = new LinkedHashSet<BinRange>();
		
		
		int countDBBinRangeAccountedProcessed = 0;

		int countDBBinRangeToAccountProcess = dbBinRangeSet.size();
		System.out.println("Num DB Ranges to Account/Process = " + countDBBinRangeToAccountProcess);

		int countForNumFileRangesAnalyzedForADBRange = 0;
		Iterator<BinRange> fileBinRangeIterator = fileBinRangeSet.iterator();
		
		while (dbBinRangeIterator.hasNext()) {
			dbBinRangeCurrentPointer = dbBinRangeIterator.next();
			BinRange fileBinRangeCurrentPointer = null;
			while (fileBinRangeIterator.hasNext()) {
				fileBinRangeCurrentPointer = fileBinRangeIterator.next();
				// case of matching range as it is in DB and file and not known
				// whether to add or delete
				if ((dbBinRangeCurrentPointer.getStartIIN() == fileBinRangeCurrentPointer.getStartIIN())
						&& dbBinRangeCurrentPointer.getEndIIN() == fileBinRangeCurrentPointer.getEndIIN()) {
					// Log/System Out which range processed
					System.out.println("DB Range Accounted/Processed(Same as File) == " + dbBinRangeCurrentPointer);
					System.out.println("File Range Accounted/Processed(Same as DB) == " + fileBinRangeCurrentPointer);					
					countDBBinRangeAccountedProcessed++;
					matchingDBAndFileBinRangeSet.add(dbBinRangeCurrentPointer);
					dbBinRangeCurrentPointer = dbBinRangeIterator.next();
					fileBinRangeCurrentPointer = fileBinRangeIterator.next();
				} else if ((dbBinRangeCurrentPointer.getStartIIN() == fileBinRangeCurrentPointer.getStartIIN())
						&& (dbBinRangeCurrentPointer.getEndIIN() < fileBinRangeCurrentPointer.getEndIIN())) {
					// case of broadened range in file in comparison to DB
					System.out.println("DB Range Accounted/Processed(Broadened) == " + dbBinRangeCurrentPointer);
					System.out.println("File Range Accounted/Processed(Broadened) == " + fileBinRangeCurrentPointer);					

				}else if(
						(dbBinRangeCurrentPointer.getStartIIN() < fileBinRangeCurrentPointer.getStartIIN()) && 
						(dbBinRangeCurrentPointer.getEndIIN() < fileBinRangeCurrentPointer.getEndIIN()) &&
						(countForNumFileRangesAnalyzedForADBRange == 0)){
					
					countForNumFileRangesAnalyzedForADBRange = 0;
					//case of new range in file which is not in DB as it is
					System.out.println("New BIN Range in File == " + fileBinRangeCurrentPointer);
					newInFileBinRangeSet.add(fileBinRangeCurrentPointer);
					fileBinRangeCurrentPointer = fileBinRangeIterator.next();
				}else if (
						(dbBinRangeCurrentPointer.getStartIIN() <= fileBinRangeCurrentPointer.getStartIIN()) &&
						(dbBinRangeCurrentPointer.getEndIIN() <= fileBinRangeCurrentPointer.getEndIIN())
						){
					//case of potential bin range which is in DB but spilt in file for finer granulity
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("Bin Range Splitted in File than DB == " + fileBinRangeCurrentPointer);
					potentialNewSplitRangeInFile.add(fileBinRangeCurrentPointer);
					fileBinRangeCurrentPointer = fileBinRangeIterator.next();
				}else if(
						//debatable whether >= or just >, preferred >
						(dbBinRangeCurrentPointer.getStartIIN() >= fileBinRangeCurrentPointer.getStartIIN())&&
						(dbBinRangeCurrentPointer.getEndIIN() >= fileBinRangeCurrentPointer.getEndIIN())
						){
					//case of broaden bin range in file, could be add or delete
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("Broadened Bin Range in File than DB == " + fileBinRangeCurrentPointer);
					potentialBroadenedBinRangeInFile.add(fileBinRangeCurrentPointer);
					fileBinRangeCurrentPointer = fileBinRangeIterator.next();
				}else if(
						(dbBinRangeCurrentPointer.getStartIIN() == fileBinRangeCurrentPointer.getStartIIN()) &&
						(dbBinRangeCurrentPointer.getEndIIN() > fileBinRangeCurrentPointer.getEndIIN()) &&
						( countForNumFileRangesAnalyzedForADBRange == 0 )
						){

					//case of new BIN Range in File
					countForNumFileRangesAnalyzedForADBRange = 0;
					System.out.println("New Bin Range in File == " + fileBinRangeCurrentPointer);
					newInFileBinRangeSet.add(fileBinRangeCurrentPointer);
					fileBinRangeCurrentPointer = fileBinRangeIterator.next();
				}else if(
						(dbBinRangeCurrentPointer.getStartIIN() <= fileBinRangeCurrentPointer.getStartIIN()) &&
						(dbBinRangeCurrentPointer.getEndIIN() >= fileBinRangeCurrentPointer.getEndIIN())
						){
					
					//case of split bin range only in file but not in DB
					countForNumFileRangesAnalyzedForADBRange++;
					System.out.println("Bin Range Splitted in File than DB == " + fileBinRangeCurrentPointer);
					potentialNewSplitRangeInFile.add(fileBinRangeCurrentPointer);
					fileBinRangeCurrentPointer = fileBinRangeIterator.next();
				}else if(
						//debatable >= or >, preferred would be >
						(dbBinRangeCurrentPointer.getStartIIN() >= fileBinRangeCurrentPointer.getStartIIN() ) &&
						(dbBinRangeCurrentPointer.getEndIIN() >= fileBinRangeCurrentPointer.getEndIIN())
						){
					
					//case of new range in file and not in DB
					countForNumFileRangesAnalyzedForADBRange = 0;
					System.out.println("New Bin Range in File == " + fileBinRangeCurrentPointer);
					newInFileBinRangeSet.add(fileBinRangeCurrentPointer);
					fileBinRangeCurrentPointer = fileBinRangeIterator.next();
					//confusion with regards to moving DB or File Pointer
					//confusion with regards to what is meant by add range in file set
					
				}else if(
						//debatable whether >= or >, preferred >
						(dbBinRangeCurrentPointer.getStartIIN() >= fileBinRangeCurrentPointer.getStartIIN()) &&
						(dbBinRangeCurrentPointer.getEndIIN() >= fileBinRangeCurrentPointer.getEndIIN())
						){
					
					countForNumFileRangesAnalyzedForADBRange = 0;
					System.out.println("*****Range in DB and not in File == " + dbBinRangeCurrentPointer);
					rangeInDBButNotInFile.add(dbBinRangeCurrentPointer);
					countDBBinRangeAccountedProcessed++;
					dbBinRangeCurrentPointer = dbBinRangeIterator.next();
					//need to check when this happens
					//if last db range in set, do we add the existing file bin range and remaining to the rangeInDBButNotInFile
					if ( dbBinRangeCurrentPointer == null){
						System.out.println("*****Range in DB and not in File == " + fileBinRangeCurrentPointer);						
						rangeInDBButNotInFile.add(fileBinRangeCurrentPointer);
						while(fileBinRangeIterator.hasNext()){
							fileBinRangeCurrentPointer = fileBinRangeIterator.next();
							System.out.println("*****Range in DB and not in File == " + fileBinRangeCurrentPointer);
							rangeInDBButNotInFile.add(fileBinRangeCurrentPointer);
						}
					}
				}else{
					System.out.println("Unhandled case of BIN Range for File and DB.");
					System.out.println("Bin Range in DB == " + dbBinRangeCurrentPointer);
					System.out.println("Bin Range in File == " + fileBinRangeCurrentPointer);
				}

			}// end of FileBinRangeIterator
			System.out.println("Going for next DB Bin Range.............");
		}// end of DB Bin Range Iterator

		System.out.println("Num DB Ranges accounted/processed == " + countDBBinRangeAccountedProcessed);
		System.out.println("Num DB Ranges to be accounted/processed == " + countDBBinRangeToAccountProcess);

		
//		Set<BinRange> matchingDBAndFileBinRangeSet = new LinkedHashSet<BinRange>();
//		Set<BinRange> newInFileBinRangeSet = new LinkedHashSet<BinRange>();
//		Set<BinRange> potentialNewSplitRangeInFile = new LinkedHashSet<BinRange>();
//		Set<BinRange> potentialBroadenedBinRangeInFile = new LinkedHashSet<BinRange>();
//		Set<BinRange> rangeInDBButNotInFile = new LinkedHashSet<BinRange>();

		
		System.out.println("======= Matching DB and File Bin Ranges ===========");
		matchingDBAndFileBinRangeSet.forEach(binRange -> System.out.println(binRange));
		System.out.println("====================END============================");
		System.out.println();System.out.println();
		
		System.out.println("======= New in File and Not in DB =================");
		newInFileBinRangeSet.forEach(binRange -> System.out.println(binRange));
		System.out.println("====================END============================");
		System.out.println();System.out.println();

		System.out.println("===== New Range Split in File from Range in DB ====");
		potentialNewSplitRangeInFile.forEach(binRange -> System.out.println(binRange));
		System.out.println("====================END============================");
		System.out.println();System.out.println();

		System.out.println("======= Bin Range broaded in File for Range in DB== ");
		potentialBroadenedBinRangeInFile.forEach(binRange -> System.out.println(binRange));
		System.out.println("====================END============================");
		System.out.println();System.out.println();

		System.out.println("======= Not in File but in DB ===================== ");
		rangeInDBButNotInFile.forEach(binRange -> System.out.println(binRange));
		System.out.println("====================END============================");
		System.out.println();System.out.println();
*/
	}
	
	public static void main(String[ ]args){
		
		BinRangeProcessor binRangeProcessor = new BinRangeProcessor();
		binRangeProcessor.initializeFileBinRanges();
		binRangeProcessor.initializeDBBinRanges();
//		binRangeProcessor.processBinRange();
	}

}
