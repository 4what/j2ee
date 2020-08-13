package sample;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDB {


	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);


		/* 3.x */
		MongoDatabase database = mongoClient.getDatabase("db");

		MongoCollection<Document> collection = database.getCollection("collection");

		Document document = new Document("key", "value");

		collection.insertOne(document);

/*
		List<Document> documents = new ArrayList<>();
		documents.add(document);

		collection.insertMany(documents);
*/

		//collection.updateOne(Filters.eq("key", "value"), Updates.set("key", System.currentTimeMillis()));

		//collection.deleteOne(Filters.eq("key", "value"));

		System.out.println("count: " + collection.countDocuments());

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				System.out.println("item :" + cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}
	}
}
