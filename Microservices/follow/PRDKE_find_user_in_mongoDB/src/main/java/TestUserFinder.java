
public class TestUserFinder {
    public static void main(String[] args) {

        // test with user which is in the db
        String username1 = "Liam";  // contained in the db
        boolean user1contained = UserFinder.containsUser(username1);
        UserData user1Data = UserFinder.getUser(username1);

        // test with user which is not in the db
        String username2 = "Stefan"; // not contained in the db
        boolean user2contained = UserFinder.containsUser(username2);
        UserData user2Data = UserFinder.getUser(username2);

        // print the results
        System.out.println();  // the mongo db outputs a lot of stuff on the console, thus, print an empty line
        System.out.println("Is \"" + username1 + "\" contained in the data base? -> " + user1contained);
        System.out.println("Userdata of \"" + username1 + "\" :");
        System.out.println(user1Data);

        System.out.println();
        System.out.println("Is \"" + username2 + "\" contained in the data base? -> " + user2contained);
        System.out.println("Userdata of \"" + username2 + "\" :");
        System.out.println(user2Data);

    }
}
