import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class UserFinder {

    /**
     * Searches for the user in the data base and returns its data as an UserData object.
     * @param userName the username as String
     * @return the data of the user as UserData object, returns null if the user does not exist
     */

    public static UserData getUser(String userName) {
        // connect to the data base
        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://Adri25:adri1234@cluster0.taxsc.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("PRDKE");
        MongoCollection<Document> collection = database.getCollection("UserData");

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", userName);

        Document user = collection.find(whereQuery).first();

        if (user == null) {     // username already taken
            return null;
        }
        return new UserData(user.getString("name"), user.getString("password"), user.getString("registration date"), user.getString("Email"));
    }

    /**
     * Searches for the user in the data base.
     * @param userName the username as String
     * @return ture if the user exists and false if the user does not exist
     */
    public static boolean containsUser(String userName) {
        if (getUser(userName) != null) {
            return true;
        }
        return false;
    }

}
