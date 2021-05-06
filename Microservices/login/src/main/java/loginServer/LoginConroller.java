package loginServer;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController public class LoginConroller {

    public LoginConroller() {
        super();
    }

    @PostMapping("/login/{1}/{2}")
    public ResponseEntity<UserData> login(@PathVariable("1") final String userName,
                                          @PathVariable("2") final String password) {

        // connect to the data base
        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://Adri25:adri1234@cluster0.taxsc.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("PRDKE");
        MongoCollection<Document> collection = database.getCollection("UserData");

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", userName);

        Document user = collection.find(whereQuery).first();

        if (user == null || !user.getString("password").equals(password)) {     // username not found or invalid password
            return getBadRequestResponseEntity("Invalid Username or Password.");
        }

        final UserData userData = new UserData(user.getString("name"), user.getString("password"), user.getString("registration date"), user.getString("Email"));
        return ResponseEntity.ok(userData);
    }

    private ResponseEntity getBadRequestResponseEntity(final String errorMessage) {
        return new ResponseEntity(
                HttpStatus.BAD_REQUEST + " - " + errorMessage, HttpStatus.BAD_REQUEST);
    }

}
