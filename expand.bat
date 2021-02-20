@echo off

SET _filename=%~n1
SET _extension=%~x1
if not exist %1 (
	echo The file was not found
	exit /b
)
if not %_extension% == .bw (
	echo wrong file format
	exit /b
)


java -cp "./out/production/BurrowsWheeler;./out/production/BurrowsWheeler/algs4.jar;" edu.princeton.cs.algs4.Huffman + < %1 | ^
java -cp "./out/production/BurrowsWheeler;./out/production/BurrowsWheeler/algs4.jar;" MoveToFront + | ^
java -cp "./out/production/BurrowsWheeler;./out/production/BurrowsWheeler/algs4.jar;" BurrowsWheeler + > %_filename%

echo expansion complete!
echo expanded file saved to %_filename%