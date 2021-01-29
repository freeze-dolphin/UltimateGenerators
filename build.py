import os
import sys

for comp in sys.argv:
	print(comp)

argl = len(sys.argv)

if (argl >= 1):
	print("\nArguments are Detected")
	cmdC = " "
	for comp in sys.argv:
		if (comp == sys.argv[0]):
			continue
		cmdC = cmdC + comp + " "
	c = "mvn" + cmdC + "package"
	print("\n[" + c + "]\n")
	os.system(c)
else: 
	print("\nExecuting Pure Mode")
	os.system("mvn package")
