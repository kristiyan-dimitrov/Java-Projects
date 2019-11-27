package msiaThomasKristiyanProj2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;  

public class TestMyPandas {
	public static void main(String[] args) throws IOException {
		String filepath = "mybabies.csv";
		MyDataFrame mydf = MyPandas.readCSV(filepath);
		
		//test concat lol -YAY
		MyDataFrame twoBabies = MyPandas.readCSV("twobabies.csv");
		MyDataFrame eightBabies = MyPandas.readCSV("eightbabies.csv");
		
		System.out.println("-----------------------");
		System.out.println("Testing .concat");
		System.out.println("-----------------------");
		MyDataFrame shouldBeTenBabies = MyPandas.concat(twoBabies,eightBabies);
		System.out.println(shouldBeTenBabies.headers);
		System.out.println(shouldBeTenBabies.rows);
		System.out.println(shouldBeTenBabies.columns);
		System.out.println(shouldBeTenBabies.rows.size());
		
		//test writeCSV - YAY
		MyPandas.writeCSV(shouldBeTenBabies,"writemybabiestest.csv");
		
		
		//test head - YAY
		System.out.println("-----------------------");
		System.out.println("Testing .head");
		System.out.println("-----------------------");
		MyDataFrame top3babies = eightBabies.head(3);
		System.out.println(top3babies.headers);
		System.out.println(top3babies.rows);
		System.out.println(eightBabies.rows);
		
		//test tail - YAY
		System.out.println("-----------------------");
		System.out.println("Testing .tail");
		System.out.println("-----------------------");
		MyDataFrame last3babies = eightBabies.tail(3);
		System.out.println(last3babies.headers);
		System.out.println(last3babies.rows);
		System.out.println(eightBabies.rows);
		
		// Test dType (int index)
		System.out.println("-----------------------");
		System.out.println("Testing .dType(int index)");
		System.out.println("-----------------------");
		eightBabies.dType(2);
		System.out.println(eightBabies.dType(0));
		System.out.println(eightBabies.dType(1));
		System.out.println(eightBabies.dType(2));
		System.out.println(eightBabies.dType(3));
		System.out.println(eightBabies.dType(4));
		
		// Test dType (String name)
		System.out.println("-----------------------");
		System.out.println("Testing .dType(String name)");
		System.out.println("-----------------------");
		System.out.println(eightBabies.headers);
		System.out.println(eightBabies.dType("State"));
		System.out.println(eightBabies.dType("Gender"));
		System.out.println(eightBabies.dType("Year"));
		System.out.println(eightBabies.dType("Name"));
		System.out.println(eightBabies.dType("Births"));
		System.out.println(eightBabies.dType("Shouldn't find column"));
	
		// Test slice (int index)
		System.out.println("-----------------------");
		System.out.println("Testing .slice(int index)");
		System.out.println("-----------------------");
		System.out.println(eightBabies.slice(0).headers);
		System.out.println(eightBabies.slice(0).rows);
		System.out.println(eightBabies.slice(0).columns);
		MyPandas.writeCSV(eightBabies.slice(0),"eightBabiesSliceIndex0.csv");
		System.out.println(eightBabies.slice(2).headers);
		System.out.println(eightBabies.slice(2).rows);
		System.out.println(eightBabies.slice(2).columns);
		MyPandas.writeCSV(eightBabies.slice(2),"eightBabiesSliceIndex2.csv");
		

		// Test slice (String name)
		// Test dType (String name)
		System.out.println("-----------------------");
		System.out.println("Testing .slice(String name)");
		System.out.println("-----------------------");
		System.out.println(eightBabies.slice("State").headers);
		System.out.println(eightBabies.slice("State").rows);
		System.out.println(eightBabies.slice("State").columns);
		MyPandas.writeCSV(eightBabies.slice("State"),"eightBabiesSliceState.csv");
		System.out.println(eightBabies.slice("Year").headers);
		System.out.println(eightBabies.slice("Year").rows);
		System.out.println(eightBabies.slice("Year").columns);
		MyPandas.writeCSV(eightBabies.slice("Year"),"eightBabiesSliceYear.csv");
		
		// Test slice (int[] indexArr)
		System.out.println("-----------------------");
		System.out.println("Testing .slice(int[] indexArr)");
		System.out.println("-----------------------");
		int[] intArray = new int[]{0,1,3};
		eightBabies.slice(intArray);
		System.out.println(eightBabies.slice(intArray).headers);
		System.out.println(eightBabies.slice(intArray).rows);
		System.out.println(eightBabies.slice(intArray).columns);
		MyPandas.writeCSV(eightBabies.slice(intArray),"eightBabiesSliceIndicesArr.csv");
		int[] intArray2 = new int[]{2,3,4};
		eightBabies.slice(intArray);
		System.out.println(eightBabies.slice(intArray2).headers);
		System.out.println(eightBabies.slice(intArray2).rows);
		System.out.println(eightBabies.slice(intArray2).columns);
		MyPandas.writeCSV(eightBabies.slice(intArray2),"eightBabiesSliceIndicesArr2.csv");
		
		// Test slice (String[] nameArr)
		System.out.println("-----------------------");
		System.out.println("Testing .slice(String[] nameArr)");
		System.out.println("-----------------------");
		String[] nameArray = new String[]{"Year","State","Name"};
		eightBabies.slice(nameArray);
		System.out.println(eightBabies.slice(nameArray).headers);
		System.out.println(eightBabies.slice(nameArray).rows);
		System.out.println(eightBabies.slice(nameArray).columns);
		MyPandas.writeCSV(eightBabies.slice(nameArray),"eightBabiesSliceStringArr.csv");
		String[] nameArray2 = new String[]{"Births","Births","Name"};
		eightBabies.slice(intArray);
		System.out.println(eightBabies.slice(nameArray2).headers);
		System.out.println(eightBabies.slice(nameArray2).rows);
		System.out.println(eightBabies.slice(nameArray2).columns);
		MyPandas.writeCSV(eightBabies.slice(nameArray2),"eightBabiesSliceStringArr2.csv");
		
		// Test loc (all 4 versions)
		System.out.println("-----------------------");
		System.out.println("Testing .loc (4 versions)");
		System.out.println("-----------------------");
		System.out.println(shouldBeTenBabies.rows);
		System.out.println(shouldBeTenBabies.loc(3).columns);
		MyPandas.writeCSV(shouldBeTenBabies.loc(3),"shouldBeTenBabies_locInt.csv");
		System.out.println(shouldBeTenBabies.loc("3").columns);		
		MyPandas.writeCSV(shouldBeTenBabies.loc("3"),"shouldBeTenBabies_locStr.csv");
		System.out.println(shouldBeTenBabies.loc(0,3).rows);
		MyPandas.writeCSV(shouldBeTenBabies.loc(0,3),"shouldBeTenBabies_locIntRange.csv");
		System.out.println(shouldBeTenBabies.loc("0","3").rows);	
		MyPandas.writeCSV(shouldBeTenBabies.loc("0","3"),"shouldBeTenBabies_locStringRange.csv");
		
		// Test sort (int index)
		System.out.println("-----------------------");
		System.out.println("Testing .sort (int index)");
		System.out.println("-----------------------");
		System.out.println(shouldBeTenBabies.sort(0).headers);
		System.out.println(shouldBeTenBabies.sort(0).rows);
		MyPandas.writeCSV(shouldBeTenBabies.sort(0),"shouldBeTenBabies_SortIndex.csv");
		
		// Test sort (String name)
		System.out.println("-----------------------");
		System.out.println("Testing .sort (String name)");
		System.out.println("-----------------------");
		System.out.println(shouldBeTenBabies.sort("Name").headers);
		System.out.println(shouldBeTenBabies.sort("Name").rows);
		MyPandas.writeCSV(shouldBeTenBabies.sort("Name"),"shouldBeTenBabies_SortName.csv");

		// Test filter (String name)
		System.out.println("-----------------------");
		System.out.println("Testing .filter (String name)");
		System.out.println("-----------------------");
		System.out.println(shouldBeTenBabies.filter("Gender", "=", "M").columns);
		System.out.println(shouldBeTenBabies.filter("Year", ">", 1975).columns);
		System.out.println(shouldBeTenBabies.filter("Gender", "<", "M").columns);
		System.out.println(shouldBeTenBabies.filter("Year", "<=", 2010).columns);
		MyPandas.writeCSV(shouldBeTenBabies.filter("Gender", "=", "M"),"shouldBeTenBabies_Filtered1.csv");
		MyPandas.writeCSV(shouldBeTenBabies.filter("Year", ">", 1975),"shouldBeTenBabies_Filtered2.csv");
		
		// Test getMin & getMax
		System.out.println(shouldBeTenBabies.getMin(0));
		System.out.println(shouldBeTenBabies.getMin("Year"));
		System.out.println(shouldBeTenBabies.getMax(3));
		System.out.println(shouldBeTenBabies.getMax("State"));
		
	}
}
