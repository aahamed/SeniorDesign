### Top Level Makefile ###

BIN = ./bin/
LIB = ./lib/*
SRC = ./src/
TEST = ./test/
GP = ./gnuplot/
ODIR = -d $(BIN)
OS = $(shell uname)
XLINT = -Xlint:unchecked

# Determine 'CP' based on OS #

ifeq '$(OS)' 'Windows_NT'
	# For Windows
	CP = -cp '$(BIN);$(LIB)'
else ifeq '$(OS)' 'Darwin'
	# For OS X
	CP = -cp '$(BIN):$(LIB)'
else ifeq '$(OS)' 'Linux'
	# For Linux
	CP = -cp '$(BIN):$(LIB)'
else
	echo Unknown OS. Add condition for your OS.
endif

### Make Rules ###

# General #

all:
	javac $(CP) $(ODIR) $(SRC)*.java

help:
	java $(CP) MainBody --help

run:
	java $(CP) MainGUI

graph:
	java $(CP) TransformScale

clean:
	rm -rf $(BIN)*.class

# TODO: Everything Else #

GlobalConstants: $(SRC)GlobalConstants.java
	javac $(CP) $(ODIR) $(SRC)GlobalConstants.java

HCS: Coordinate GlobalConstants
	javac $(CP) $(ODIR) $(SRC)HCS.java

testHCS:
	java $(CP) HCS

Coordinate: $(SRC)Coordinate.java
	javac $(CP) $(ODIR) $(SRC)Coordinate.java

testCoord:
	java $(CP) Coordinate

ConnectOut: $(SRC)ConnectOut.java
	javac $(CP) $(ODIR) $(SRC)ConnectOut.java

Connect: ConnectOut HCS GlobalConstants Coordinate
	javac $(CP) $(ODIR) $(SRC)Connect.java

#testConnect:
#	java $(CP) Connect

FindANOut: $(SRC)FindANOut.java
	javac $(CP) $(ODIR) $(SRC)FindANOut.java

FindAN: Coordinate FindANOut
	javac $(CP) $(ODIR) $(SRC)FindAN.java

#testFindAN:
#	java $(CP) FindAN

PrimMST: $(SRC)PrimMST.java
	javac $(CP) $(ODIR) $(SRC)PrimMST.java

List_ops:
	javac $(CP) $(ODIR) $(SRC)List_ops.java

InitMaxOut:
	javac $(CP) $(ODIR) $(SRC)InitMaxOut.java

InitMax: Coordinate GlobalConstants HCS PrimMST
	javac $(CP) $(ODIR) $(SRC)InitMax.java

#testInitMax:
#	java $(CP) InitMax

LocateM2:
	javac $(CP) $(ODIR) $(SRC)LocateM2.java

testMain:
	java $(CP) MainBody -m -k -i ./input/min.txt

testMain2:
	java $(CP) MainBody -g -k -i ./input/in.txt

testMain3:
	java $(CP) MainBody -m -p -i ./input/min.txt

testJunit: $(TEST)TestJunit.java $(TEST)TestRunner.java
	javac $(CP) $(ODIR) $(TEST)TestJunit.java $(TEST)TestRunner.java

runTestJunit:
	java $(CP) TestRunner

testCoordinate: Coordinate $(TEST)TestCoordinate.java $(TEST)TestCoordinateRunner.java
	javac $(CP) $(ODIR) $(TEST)TestCoordinate.java $(TEST)TestCoordinateRunner.java

runTestCoordinate:
	java $(CP) TestCoordinateRunner

testConnect: Connect $(TEST)TestConnect.java $(TEST)TestConnectRunner.java
	javac $(CP) $(ODIR) $(TEST)TestConnect.java $(TEST)TestConnectRunner.java

runTestConnect:
	java $(CP) TestConnectRunner

testFindAN: FindAN $(TEST)TestFindAN.java $(TEST)TestFindANRunner.java
	javac $(CP) $(ODIR) $(TEST)TestFindAN.java $(TEST)TestFindANRunner.java $(XLINT)

runTestFindAN:
	java $(CP) TestFindANRunner

testInitMax:
	javac $(CP) $(ODIR) $(TEST)TestInitMax.java $(TEST)TestInitMaxRunner.java $(XLINT)

runTestInitMax:
	java $(CP) TestInitMaxRunner
