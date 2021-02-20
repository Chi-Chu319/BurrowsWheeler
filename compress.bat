@echo off


SET filename=%1
if not exist %filename% (
	echo The file was not found
	exit /b
)


java -cp "./out/production/BurrowsWheeler;./out/production/BurrowsWheeler/algs4.jar;" BurrowsWheeler - < %filename% | ^
java -cp "./out/production/BurrowsWheeler;./out/production/BurrowsWheeler/algs4.jar;" MoveToFront - | ^
java -cp "./out/production/BurrowsWheeler;./out/production/BurrowsWheeler/algs4.jar;" edu.princeton.cs.algs4.Huffman - > %filename%.bw

echo compression complete!
echo compressed file saved to %filename%.bw