// Lab18avst.java
// The Student Records Algorithm Program
// This is the student, starting version of the Lab18a lab assignment.


import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class lab18avst
{
	public static void main(String[] args) throws IOException
	{
		
		List studentArray = new List(100);
		studentArray.getList();
		studentArray.display("UNSORTED LIST OF STUDENTS");
		studentArray.pause();

		studentArray.gpaSort();
		studentArray.display("STUDENTS SORTED IN DESCENDING ORDER BY GPA");
		studentArray.pause();

		studentArray.ageSort();
		studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY AGE");
		studentArray.pause();

		studentArray.idSort();
		studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY ID#");
		studentArray.pause();
		
 		int studentID = getID();
		
		studentArray.search(studentID);

		int Index = studentArray.search(studentID);

		if (Index == -1)
		    System.out.println("There is no student with an ID# of "+studentID+".\n");
		else
		{
			studentArray.displayStudent(Index);   // displays the information for the found student
			studentArray.delete(Index);           // remove the same student from the array
			studentArray.pause();
			studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY ID# WITHOUT STUDENT# "+studentID);
			studentArray.pause();
		}
	}

	public static int getID()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter the 6-digit ID of the student.  { 100000 - 999999 }  -->  ");
		return input.nextInt();
	}
}


class Person
{
	public String name;
	public int id;
	public int age;
	public double gpa;

	Person(String n, int ID, int a,double g)
	{
		name = n;
		id = ID;
		age = a;
		gpa = g;
	}
}


class List
{
	private Person student[];	// stores array elements
	private int capacity;		// actual array capacity
	private int size;			// number of elements in the array
	int index = 0;

	public List(int c)
	{
		capacity = c;
		size = 0;
		student = new Person[capacity];
	}

	public void getList() throws IOException
	{
		FileReader inFile = new FileReader("students.dat");
		BufferedReader inStream = new BufferedReader(inFile);
		String s1,s2,s3,s4;
		int age, id;
		double gpa;
		int index = 0;
		while( ((s1 = inStream.readLine()) != null) &&
			   ((s2 = inStream.readLine()) != null) &&
			   ((s3 = inStream.readLine()) != null) &&
			   ((s4 = inStream.readLine()) != null) )
		{
			String name = s1;
			id = Integer.parseInt(s2);
			age = Integer.parseInt(s3);
			gpa = Double.parseDouble(s4);

			student[index] = new Person(name,id,age,gpa);
			index++;
		}
		inStream.close();
		size = index;
	}

	private String spaces(String name)
    {
    	int tab = 24 - name.length();
    	String temp = "";
    	for (int j = 1; j <= tab; j++)
    	    temp += " ";
    	return temp;
    }

	public void display(String listInfo)
	{
		DecimalFormat output = new DecimalFormat("0.000");
		System.out.println("\nDISPLAYING "+ listInfo);
		System.out.println("\nStudent ID#     Student Name            Age         GPA");
		System.out.println("============================================================");

		for (int k = 0; k < size; k++)
			System.out.println(student[k].id + "          "+student[k].name + spaces(student[k].name) + student[k].age + "          " + output.format(student[k].gpa));
	}

	public void pause()
	{
		Scanner input = new Scanner(System.in);
		String dummy;
		System.out.println("\nPress <Enter> to continue.");
		dummy = input.nextLine();
	}

	public void displayStudent(int Index)
	{
		
		System.out.println("Student Record for ID# " + student[Index].id);
		System.out.println();
		System.out.println("Name : " + student[Index].name);
		System.out.println("Age : " + student[Index].age);
		System.out.println("GPA : " + student[Index].gpa);
		

	}

	private void swap(int x, int y)
	{
		
		Person temp = student[x];
		student[x] = student[y];
		student[y] = temp;

	}

	public void gpaSort()
	{

		boolean sorted;
	       int p = 1;
	       do
	       {
			sorted = true;
			for (int q = 0; q < size-p; q++)
		       	if (student[q].gpa < student[q+1].gpa)
		       	{			
					swap(q,q+1);
					sorted = false;
		       	}
			p++;
	       }
	       while (!sorted);
		
	}

	public void ageSort()
	{

		boolean sorted;
	       int p = 1;
	       do
	       {
			sorted = true;
			for (int q = 0; q < size-p; q++)
		       	if (student[q].age > student[q+1].age)
		       	{			
					swap(q,q+1);
					sorted = false;
		       	}
			p++;
	       }
	       while (!sorted);
		
	}

	public void idSort()
	{
		
		boolean sorted;
	       int p = 1;
	       do
	       {
			sorted = true;
			for (int q = 0; q < size-p; q++)
		       	if (student[q].id > student[q+1].id)
		       	{			
					swap(q,q+1);
					sorted = false;
		       	}
			p++;
	       }
	       while (!sorted);

	}

	public int search(int studentID)
	{

		
		if(studentID == student[index].id){
			
			return index;
			
		}else if(studentID != student[index].id){
			
			index++;
			search(studentID);
			
	    }else{
	    	
	    	index = -1;
	    	
	    }
		
		return index;
		
		
	}

	public void delete(int Index)
	{
		// Precondition:  "index" stores the index of a student object that exists in the "student" array.
		// Postcondition: The student object at index "index" is removed from the "student" array.
		//                All other objects in the "student" array are unaffected.

	}
}

