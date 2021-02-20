# BurrowsWheeler

Burrows Wheeler compressing algorithm.

### Compression prosedures
Burrows Wheeler -> MoveToFront -> Huffman

### Expansion prosedures
Huffman -> MoveToFront -> Burrows Wheeler

## Batch scripts usage

### Compression
```
compress.bat ./test/abraa.txt    #SIZE = 214KB
```
#### output
```
abraa.txt.bw                     #SIZE = 45KB
```

### Expansion
```
expand.bat ./test/abraa.txt.bw
```
#### output
```
abraa.txt
```


Assignment from: https://coursera.cs.princeton.edu/algs4/assignments/burrows/specification.php
