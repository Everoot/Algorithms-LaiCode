# 65 Valid Number

A **valid number** can be split up into these components (in order):

1. A **decimal number** or an **integer**.
2. (Optional) An `'e'` or `'E'`, followed by an **integer**.

A **decimal number** can be split up into these components (in order):

1. (Optional) A sign character (either `'+'` or `'-'`).
2. One of the following formats:
   1. One or more digits, followed by a dot `'.'`.
   2. One or more digits, followed by a dot `'.'`, followed by one or more digits.
   3. A dot `'.'`, followed by one or more digits.

An **integer** can be split up into these components (in order):

1. (Optional) A sign character (either `'+'` or `'-'`).
2. One or more digits.

For example, all the following are valid numbers: `["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]`, while the following are not valid numbers: `["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]`.

Given a string `s`, return `true` *if* `s` *is a **valid number***.

 

**Example 1:**

```
Input: s = "0"
Output: true
```

**Example 2:**

```
Input: s = "e"
Output: false
```

**Example 3:**

```
Input: s = "."
Output: false
```

 

```java
class Solution {
    public boolean isNumber(String s) {
       String str = s.trim();
       boolean seenNumber = false;
       boolean seenSignBeforeE = false;
       boolean seenSignAfterE = false;
       boolean seenE = false;
       boolean seenPoint = false;
       boolean seenNumberAfterE = false;

       for (int i = 0; i < str.length(); i++){
           char c = str.charAt(i); // i 会动, 所以记录下是个好习惯
           if (c == '+' || c == '-'){
               if (((seenPoint || seenNumber) && !seenE) || 
                seenNumberAfterE){
                    return false;
                }//.+, 12- // 12e5+
                if (seenSignAfterE ||            // 1e+- 
                (!seenE && seenSignBeforeE)){    // +123-
                    return false;
                }

                if (seenE){
                    seenSignAfterE = true;
                }else{
                    seenSignBeforeE = true;
                }
           }else if (c >= '0' && c <= '9'){
               seenNumber = true;
               if (seenE) { 
                   seenNumberAfterE = true;
               }
           }else if (c == 'e' || c == 'E'){
               if (seenE){
                   return false; // 12e12E
               }

               if (!seenNumber){
                   return false; // e
               }

               seenE = true;
           }else if (c == '.'){
               if (seenE || seenPoint){
                   return false; // 123e., 1.2.
               }
               seenPoint = true;
           }else{
               return false;
           }
       } 

       if (seenE && !seenNumberAfterE){
           // 123 e
           return false;
       }
       return seenNumber;
    }
}
```



```java
class Solution {
    public boolean isNumber(String s) {
        String str = s.trim();
        boolean seenNumber = false;
        boolean seenE = false;
        boolean seenSign = false;
        boolean seenSignBeforeE = false;
        boolean seenSignAfterE = false;
        boolean seenPoint = false;
        boolean seenNumberAfterE = false;


        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if (c == '-' || c == '+'){
                // 234-         +6e-1 √   +6e- x
                if (seenNumber == true && seenE == false){
                    return false;
                }

                // +6e3-
                if (seenNumber == true && seenE == true && seenNumberAfterE == true){
                    return false;
                }

                // +6e3 

                if (seenPoint == true && seenE == false){ // .-1    53.5e93 
                    return false;
                }

                if (seenE == true){
                    seenSignAfterE = true;
                }else{
                    seenSignBeforeE = true;
                }
            }else if (c == 'e' || c == 'E'){
                if (seenE == true){     // 2ee
                    return false;
                }

                if (seenNumber == false){// e3
                    return false;
                }

                seenE = true;
            }else if (c == '.'){
                // ..2
                if (seenPoint == true){
                    return false;
                }

                if (seenE == true){
                    return false;
                }
                seenPoint = true;
            }else if (c >= '0' && c <= '9'){
                seenNumber = true;
                if (seenE == true){
                    seenNumberAfterE = true;
                }
            }else{ // abc 
                return false;
            }
        }

        if (seenE && !seenNumberAfterE){
            // 123 e
            return false;
        }

        return seenNumber;
        
    }
}

/*
1. an 'e' and 'E' followed by an integer

2. '+' or '-'

3. '.'

4. '1.'


1. No digit 
2. 1a     
3. 1e   after e should have numbers
4. e  before e should have number
5. 99 e    can not has '.'
6. '-' only happen one time
7. '-' or '+ ' only hapend one time at begin
 

*/

```



- Time complexity: O(N), where N is the length of `s`.

  We simply iterate over the input once. The number of operations we perform for each character in the input is *independent* of the length of the string, and therefore only requires constant time. This results in N⋅O(1)=*O*(*N*).

- Space complexity: O(1)

  Regardless of the input size, we only store 3 variables, `seenDigit`, `seenExponent`, and `seenDot`.



1. What is the current possible character?

   \>= '0'   && <= '9'    ;    '+' '-' ; 'e' 'E' ; '. '

   other return false;

   ```java
   if (c == '-' || c == '+'){
     
   }else if (c >= '0' && c <= '9'){
     
   }else if (c == 'e' || c == 'E'){
     
   }else if (c == '.'){
     
   }else{
     return false;
   }
   ```

2. not valid case ('-', '+')

   * `-1-`
   * `2334-`
   * `1e.-`
   * `.-`
   * 
