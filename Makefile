# Top Level Makefile
# Put rules for compilation/running here

BIN=./bin/
SRC=./src/
LIB=./lib/*
CP=-cp '$(BIN);$(LIB)'
ODIR=-d $(BIN)
OSNAME = $(OS)

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
	ifeq '$(OSNAME)' 'OSX'
		CP=-cp '$(BIN):$(LIB)'
	endif
endif


#Make Rules

all:
	javac $(CP) $(ODIR) $(SRC)*.java

FindAN:
	javac $(CP) $(ODIR) $(SRC)FindAN.java

testFindAN:
	java $(CP) FindAN

HCS:
	javac $(CP) $(ODIR) $(SRC)HCS.java

testHCS:
	java $(CP) HCS

Connect:
	javac $(CP) $(ODIR) $(SRC)Connect.java

testConnect:
	java $(CP) Connect

echo_osname: 
	echo $(OSNAME)

echo_cp:
	echo $(CP)
