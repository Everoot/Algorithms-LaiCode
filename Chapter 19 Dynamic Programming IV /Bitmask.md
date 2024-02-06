# Bitmask

 A bitmask is a technique used to track multiple boolean flags using a single integer's binary representation. 

`000`: Indicates no numbers have been chosen.

`001`: Indicates number `1` has been chosen

`010`: Indicates number `2` has been chosen

`100`: Indicates number `3` has been chosen

`011`: indicates number `1` and `2` has been chosen

And so on...



```
int state;
```



`>>`: Right Shift Operation, this operation shifts the binary representation of `state` to the right. A right shift by 1 is mathematically equivalent to division by 2. 





`%2`: The modulo operation is used to determine if a number is even or odd. Any number modulo 2, if the result is 0, then the number is even; if it's 1, the number is odd. 



Together, the expression `(state >> i) % 2` means: first shift `state` to the right by `i` places, moving the bit we are interested in to the least significant position, and then use `% 2` to check if this bit is 0 or 1. If it's 0 (the result is even), it indicates that the number `i` is not chosen (available); if it's 1, it indicates that the number has been chosen (unavailable).



For example, suppose `state` is 010 (binary, indicating number 2 has been chosen) and `i` is 2:

- Right Shift Operation: 010 (binary) right-shifted by 2 places becomes 001.
- Modulo Operation: 001 (binary) modulo 2 equals 1 (binary), indicating that the 2nd bit (number 2) has already been chosen.