java_info1105_ass2 - Cian Byrne and Reuben Lema
# Description #

In this assignment, you'll be implementing a hash map which you'll use to create a password management system. In
Section A, you'll implement and compare different implementations of hash maps and in Section B, you'll implement a
simple password manager.


# Submission Details #

This assignment is due by 9am on Tuesday 13th October. No late submissions accepted (unless covered by an approved Special Consideration request).
If your assignment is incomplete, please submit your work before the deadline anyway even if you do not expect it to pass any of the automatic tests. Please do this even if you intend to apply for Special Consideration. If you know in advance that you will not be able to complete the assignment on time, then ask your tutor for advice, and try to focus on completing a subset of the tasks.

## To PASTA: ##
* Source code in ZIP file
## To eLearning: ##
* Your report, which should be a typed document (.pdf or .txt prefered)
* A signed and completed cover sheet

# Overview of Assignment #
This assignment is worth 12% of your final grade. 


## Marks Overview Table ##
Part of your mark is based on the overall quality of your code. Your code will be assessed on the following criteria. This is not an exhaustive list, but a guideline to give an idea of the sort of qualities we will be looking for:

* Commenting
* Code Quality
* Efficiency and Maintainability

| Section |  PASTA | Manual | Report |
|---------|:------:|:------:|:------:|
| Linear Probing | 1 | - | - |
| Impact of hash function | 1 | - | 1 |
| Double hasing | 1 | - | 1 |
| Chaining | 1 | - | 1 |
| Password Manager | 1 | - | 2 |
| *Total* | 5 | 2 | 5 |

## Part A: Hash Map Implementations ##

```
#!java

public class HashMapNode<K extends Comparable<K>, V> {
 // construction
 public HashMapNode(K key, V value)

 // get methods
 public K getKey()
 public V getValue()
 // set method
 public void setValue(V newValue)
}

public class ChainingHashMapNode<K extends Comparable<K>, V> {
 // construction
 public ChainingHashMapNode(K key, V value)

 // get methods
 public K getKey()
 public V getValue()
 public ChainingHashMapNode<K, V> getNext()
 // set methods
 public void setValue(V newValue)
 public void setNext(ChainingHashMapNode<K, V> next)
}

```


## Method 1: Linear Probing [2 marks] ##

```
#!java

public class HashMap<K extends Comparable<K>, V> {
 // construct a HashMap with 4000 places and given hash parameters
 public HashMap(int multiplier, int modulus)
 // construct a HashMap with given capacity and given hash parameters
 public HashMap(int hashMapSize, int multiplier, int modulus)
 // hashing
 public int hash(K key)
 // size (return the number of nodes currently stored in the map)
 public int size()
 public boolean isEmpty()
 // interface methods
 public List<K> keys()
 public V put(K key, V value)
 public V get(K key)
 public V remove(K key)
}

```


## Method 2: Double Hashing ##

```
#!java

public class DoubleHashMap<K extends Comparable<K>, V> {
 // updated construction
 // construct a HashMap with 4000 places and given hash parameters
 public DoubleHashMap(int multiplier, int modulus, int secondaryModulus)
 // construct a HashMap with given capacity and given hash parameters
 public DoubleHashMap(int hashMapSize, int multiplier, int modulus, int
secondaryModulus)
 ....
 public int secondaryHash(K key)
}

```


## Method 3: Chaining ##

```
#!java

public class ChainingHashMap<K extends Comparable<K>, V> {

 // construct a HashMap with 4000 places and given hash parameters
 public ChainingHashMap(int multiplier, int modulus)
 // construct a HashMap with given capacity and given hash parameters
 public ChainingHashMap(int hashMapSize, int multiplier, int modulus)
 // hashing
 public int hash(K key)
 // size (return the number of nodes currently stored in the map)
 public int size()
 public boolean isEmpty()
 // interface
 public int[] getFullestBuckets()
 public List<K> keys()
 public V put(K key, V value)
 public V get(K key)
 public V remove(K key)
}

```

## Part B: Password Manager ##

```
#!java

public class SimplePasswordManager {
 // construct a SimplePasswordManager with 4000 places and default hash parameters
 // multiplier = 1 and modulus = 4271
 public SimplePasswordManager()
 // construct a SimplePasswordManager with the supplied parameters
 public SimplePasswordManager(int size, int multiplier, int modulus)
 // hashing
 public Long hashPassword(String password)
 // interface methods
 // return an array of the usernames of the users currently stored
 public List<String> listUsers()
 public String authenticate(String username, String password)
 public String addNewUser(String username, String password)
 public String deleteUser(String username, String password)
 public String resetPassword(String username, String oldPassword, String newPassword)
}

```


## Appendix A: Reading the bot IP addresses data set (datasetA.txt) ##

```
#!java

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
....
 public void exploreData(String pathToFile) throws FileNotFoundException, IOException {
 // instantiate hash maps
 BufferedReader br = new BufferedReader(new FileReader(pathToFile));
 try {
 String line = br.readLine();
 while (line != null) {
 String[] pieces = line.trim().split("\\s+");
 if (pieces.length == 4){
 // TODO: put data into hash maps
 }
 line = br.readLine();
 }
 } finally {
 br.close();
 }
 // TODO: print collision statistics
 }

```


## Appendix B: Reading the Password data set (datasetB.txt) ##

```
#!java

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
....
 public void printHashCollisions(String pathToFile) throws FileNotFoundException,
 IOException {
 HashMap<Long, List<String>> map = new HashMap<Long, List<String>>(50000, 1,
56897);
 SimplePasswordManager spm = new SimplePasswordManager();
 BufferedReader br = new BufferedReader(new FileReader(pathToFile));
 try {
 String line = br.readLine();
 while (line != null) {
 String password = line.trim();
 Long passwordHash = spm.hash(password);
 // TODO: if passwordHash is in a, add password to its list value
 // else, instantiate a new ArrayList and add password to it
 line = br.readLine();
 }
 } finally {
 br.close();
 }
 List<Long> hashes = map.keys();
 for (Long hash : hashes){
 List<String> passwords = map.get(hash);
 if (passwords.size() > 1){
 // all passwords in this list have the same hash representation
 }
 }
 }


```

# Version #

    0.1 - 06/09/2015 - Initial release - Cian Byrne