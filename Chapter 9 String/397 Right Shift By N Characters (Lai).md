# 397 Right Shift By N Characters (Lai)

Right shift a given string by n characters.

**Assumptions**

- The given string is not null.
- n >= 0.

**Examples**

- "abc", 4 -> "cab"



```java
public class Solution {
  public String rightShift(String input, int n) {
    // Write your solution here
    if (input.length() <= 0){
      return input;
    }

    char[] array = new char[input.length()];
    for (int i = 0; i < array.length; i++){
      int index = (i + n) % input.length();
      array[index] = input.charAt(i);
    }

    return new String(array);

  }
}

/*    
      0 1 2
      a b c  -> c a b -> b c a -> a b c -> c a b
      i

      (0 + 4) % 3  = 1


      c a b 


*/


```





```java
public class Solution {
  public String rightShift(String input, int n) {
    // Write your solution here
    if (input.length() <=1){
      return input;
    }
    char[] array = input.toCharArray();
    int length = array.length;
    int time = n % length;
    char[] arraynew = new char[length];
    for (int i = 0; i < time; i++){
      arraynew[i] = array[length-1-(time-1)];
      length++;
    }
    int k = 0;
    for (int j = time; j < array.length; j++){
      arraynew[j] = array[k];
      k++;
    }
    return new String(arraynew);

  }
}

```

