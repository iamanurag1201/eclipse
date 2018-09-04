package com.dxc.MongoTest;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Adapter {

	public static Document toDBObject(Student std) {
		Document dbObj = new Document();
		dbObj.append("name", std.getName());
		dbObj.append("roll_no", std.getRoll_no());
		dbObj.append("dept", std.getDept());
		dbObj.append("marks", std.getMarks());
		return dbObj;
	}

}
