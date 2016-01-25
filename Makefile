BIN=./bin/
SRC=./src/
LIB=./lib/*
CP=-cp '$(BIN);$(LIB)'
ODIR=-d $(BIN)

all:
	javac $(CP) $(ODIR) $(SRC)*.java
