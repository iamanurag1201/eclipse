package com.dxc.MongoTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class Test_Student {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("student_test");
		MongoCollection<Document> collection = db.getCollection("record");

		for (;;) {
			System.out.println("1.Add Student \n" + "2.Delete a student based on roll_no \n"
					+ "3.Search Student By rollno\n" + "4.Search Student Based on their marks\n"
					+ "5.Find student with the highest no\n" + "6:find all the student that have failed \n"
					+ "7:Find student having marks between range 60-80\n" + "8 Find student By department name\n"
					+ "9.Department with highest marks\n" + "10.department with total no student ");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				sc.nextLine();
				System.out.println("Enter name :");
				String name = sc.nextLine();
				// sc.nextLine();
				System.out.println("Enter Roll_No :");
				// sc.nextLine();
				String roll_no = sc.nextLine();

				System.out.println("Enter Department");
				String dept = sc.nextLine();

				System.out.println("Enter Marks");
				double marks = sc.nextDouble();

				Student std = new Student(name, roll_no, dept, marks);

				Document restDB = Adapter.toDBObject(std);

				collection.insertOne(restDB);

				System.out.println("Object inserted successfully");

				break;
			case 2:

				System.out.println("Enter the rollno to delete");
				sc.nextLine();
				String rolll = sc.nextLine();
				Document d = new Document();

				d.append("roll_no", rolll);
				collection.deleteOne(d);
				System.out.println("You have successfully delete the student having rollno " + rolll);

				break;
			case 3:

				System.out.println("Student using rollno ");
				sc.nextLine();
				String searchroll = sc.nextLine();
				FindIterable<Document> itr15 = collection.find(Filters.eq("roll_no", searchroll));
				Iterator it15 = itr15.iterator();
				while (it15.hasNext()) {
					System.out.println(it15.next());
				}

				break;
			case 4:

				System.out.println("Student using marks ");
				sc.nextLine();
				Double searchmarks = sc.nextDouble();
				FindIterable<Document> itr16 = collection.find(Filters.eq("marks", searchmarks));
				Iterator it16 = itr16.iterator();
				while (it16.hasNext()) {
					System.out.println(it16.next());
				}

				break;

			case 5:

				Document d4 = new Document();
				d4.append("marks", -1);
				FindIterable<Document> itr2 = collection.find().sort(d4).limit(1);
				Iterator it2 = itr2.iterator();
				while (it2.hasNext()) {
					System.out.println(it2.next());
				}

				break;

			case 6:

				System.out.println("Student  that failed   ");

				FindIterable<Document> iter9 = collection.find().filter(Filters.lte("marks", 35))
						.sort(Sorts.orderBy(Sorts.descending("marks")));
				Iterator it9 = iter9.iterator();
				while (it9.hasNext()) {
					System.out.println(it9.next());

				}
				break;

			case 7:

				System.out.println("Student  that have marks 60-99  ");

				FindIterable<Document> fitr10 = collection
						.find(Filters.and(Filters.gte("marks", 60), Filters.lte("marks", 99)));
				Iterator it10 = fitr10.iterator();
				while (it10.hasNext()) {
					System.out.println(it10.next());
				}
				break;

			case 8:

				System.out.println("Student using dept ");
				sc.nextLine();
				String searchDep = sc.nextLine();
				FindIterable<Document> itr1 = collection.find(Filters.eq("dept", searchDep));
				Iterator it1 = itr1.iterator();
				while (it1.hasNext()) {
					System.out.println(it1.next());
				}

				break;

			case 9:

				ArrayList al8 = new ArrayList();
				al8.add(Aggregates.group("$dept", Accumulators.max("maximum", "$marks")));
				Document d8 = new Document().append("maximum", -1);
				al8.add(Aggregates.sort(d8));
				al8.add(Aggregates.limit(1));
				Iterator it8 = collection.aggregate(al8).iterator();
				while (it8.hasNext()) {
					System.out.println(it8.next());
				}

				break;
			case 10:

				ArrayList al5 = new ArrayList();
				al5.add(Aggregates.group("$dept", Accumulators.sum("Count", 1)));
				Iterator it5 = collection.aggregate(al5).iterator();
				while (it5.hasNext()) {
					System.out.println(it5.next());
				}

				break;
			case 11:

				FindIterable<Document> fitr = collection.find();
				Iterator it = fitr.iterator();
				while (it.hasNext()) {
					System.out.println(it.next());
				}

				break;
			default:

				System.out.println("Please enter right choice");
				break;
			}
		}
	}
}