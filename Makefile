# Top Level Makefile
# Put rules for compilation/running here

BIN=./bin/
SRC=./src/
LIB=./lib/*
TEST=./test/
CP=-cp '$(BIN);$(LIB)'
ODIR=-d $(BIN)
OSNAME = $(OS)
XLINT = -Xlint:unchecked

# Determine OS
ifeq '$(OS)' 'Windows_N'
	# set variables for windows
	OSNAME=$(OS)
	CP=-cp '$(BIN);$(LIB)'
else
	# get OS name
	OSNAME=$(shell uname)
	# set variables according to OS

	#Mac OSX - change as needed
	ifeq '$(OSNAME)' 'Darwin'
		CP=-cp '$(BIN):$(LIB)'
	endif
endif


#Make Rules

all:
	javac $(CP) $(ODIR) $(SRC)*.java

clean:
	rm -rf $(BIN)*.class

GlobalConstants:
	javac $(CP) $(ODIR) $(SRC)GlobalConstants.java

HCS: Coordinate GlobalConstants
	javac $(CP) $(ODIR) $(SRC)HCS.java

testHCS:
	java $(CP) HCS

Coordinate:
	javac $(CP) $(ODIR) $(SRC)Coordinate.java

testCoord:
	java $(CP) Coordinate

ConnectOut:
	javac $(CP) $(ODIR) $(SRC)ConnectOut.java

Connect: ConnectOut HCS GlobalConstants Coordinate 
	javac $(CP) $(ODIR) $(SRC)Connect.java

#testConnect:
#	java $(CP) Connect

FindANOut:
	javac $(CP) $(ODIR) $(SRC)FindANOut.java

FindAN: Coordinate FindANOut
	javac $(CP) $(ODIR) $(SRC)FindAN.java

testFindAN:
	java $(CP) FindAN

PrimMST:
	javac $(CP) $(ODIR) $(SRC)PrimMST.java

InitMax: Coordinate GlobalConstants HCS PrimMST
	javac $(CP) $(ODIR) $(SRC)InitMax.java

testInitMax:
	java $(CP) InitMax

echo_osname:
	echo $(OSNAME)

echo_cp:
	echo $(CP)

test:
	java $(CP) PCG

stest:
	java $(CP) PCG -i ./src/input/in_small.txt -g -p

mtest:
	java $(CP) PCG -m -i ./src/input/min.txt

testJunit:
	javac $(CP) $(ODIR) $(TEST)TestJunit.java $(TEST)TestRunner.java

runTestJunit:
	java $(CP) TestRunner

testCoordinate: Coordinate
	javac $(CP) $(ODIR) $(TEST)TestCoordinate.java $(TEST)TestCoordinateRunner.java

runTestCoordinate:
	java $(CP) TestCoordinateRunner

testConnect: Connect
	javac $(CP) $(ODIR) $(TEST)TestConnect.java $(TEST)TestConnectRunner.java

runTestConnect:
	java $(CP) TestConnectRunner
