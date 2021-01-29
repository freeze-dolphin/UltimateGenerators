import os
import sys

for comp in sys.argv: 
	print(comp)

argl = len(sys.argv)

if (argl >= 1): 
	print("Arguments are Detected")
	cmdC = " "
	for comp in sys.argv:
                if (comp.
		cmdC = cmdC + comp + " "
	c = "mvn" + cmdC + "package"
	print(c)
	os.system(c)
else: 
	print("Executing Pure Mode")
	os.system("mvn package")
