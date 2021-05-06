package registrationServer;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import loginServer.UserData;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController public class RegistrationConroller {

    public RegistrationConroller() {
        super();
    }

    @PostMapping("/registration/{1}/{2}/{3}")
    public ResponseEntity<UserData> registration(@PathVariable("1") final String userName,
                                          @PathVariable("2") final String password,
                                          @PathVariable("3") final String email) {

        // connect to the data base
        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://Adri25:adri1234@cluster0.taxsc.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("PRDKE");
        MongoCollection<Document> collection = database.getCollection("UserData");

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", userName);

        Document user = collection.find(whereQuery).first();

        if (user != null) {     // username already taken
            return getBadRequestResponseEntity("Username already taken.");
        }

        // insert new user into the data base
        Document newUser = new Document("name", userName)
                .append("password", password)
                .append("registration date", new Date().toString())
                .append("Email", email);

        collection.insertOne(newUser);

        // read the just inserted data from the data base and return it to the client
        whereQuery = new BasicDBObject();
        whereQuery.put("name", userName);
        user = collection.find(whereQuery).first();
        if (user == null) {
            return getBadRequestResponseEntity("Something went wrong.");
        }

        final UserData userData = new UserData(user.getString("name"), user.getString("password"), user.getString("registration date"), user.getString("Email"));
        return ResponseEntity.ok(userData);
    }

    private ResponseEntity getBadRequestResponseEntity(final String errorMessage) {
        return new ResponseEntity(
                HttpStatus.BAD_REQUEST + " - " + errorMessage, HttpStatus.BAD_REQUEST);
    }

}
