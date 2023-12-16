package flyxpert.flyxpert;

import java.util.ArrayList;
import java.util.HashMap;

/*
assumptions :
- usernames are unique (check if exist if signing up)
-
 */
public class UserTrie {

        private static UserTrie userTrie;

        public static synchronized UserTrie getInstance() {
                if (userTrie == null) {
                        userTrie = new UserTrie();
                }
                return userTrie;
        }

        private class TrieNode {
                ArrayList<Integer> indicesOfBookings = new ArrayList<>();
                char c;
                int counter;
                boolean isEndOfName;
                private HashMap<Character, TrieNode> children;

                String password;
                public TrieNode() {
                        children = new HashMap<>();
                        isEndOfName = false;
                        password = new String();
                }
        }

        TrieNode root;

        public UserTrie() {
                root = new TrieNode();
        }
        public void insertUser(String name, String password) {
                TrieNode current = new TrieNode();
                current = root;
                int size = name.length();
                for (int i = 0; i < size; ++i) {
                        char c = name.charAt(i);
                        TrieNode child = current.children.get(c);
                        if (child == null) {
                                child = new TrieNode();
                                current.children.put(c, child);
                        }
                        current = child;
                }
                current.isEndOfName = true;
                current.password = password;
        }

        public void insertBooking(String username, int bookingIndex) {
                TrieNode current = search(username);
                current.indicesOfBookings.add(bookingIndex);
        }

        public TrieNode search(String name) {
                TrieNode current = new TrieNode();
                current = root;
                int size = name.length();
                for (int i = 0; i < size; ++i) {
                        char c = name.charAt(i);
                        TrieNode child = new TrieNode();
                        if (current == null)
                                return null;
                        child = current.children.get(c);
                        current = child;
                }
                return current;
        }

        public ArrayList<Integer> getBookings(String userName) {
                TrieNode user = search(userName);
                if (user == null)
                        return null;
                return user.indicesOfBookings;
        }

        public String getPassword(String userName) {
                TrieNode user = search(userName);
                if (user == null)
                        return null;
                return user.password;
        }
}