# Makefile for RentBuy Project

# Directories
SRC_DIR := src/main/java
TEST_DIR := src/test/java
OUT_DIR := out
MAIN_OUT := $(OUT_DIR)/main
TEST_OUT := $(OUT_DIR)/test
LIB_DIR := lib

# Classpath settings
CLASSPATH := .:$(LIB_DIR)/*:$(MAIN_OUT):$(TEST_OUT)

# Main class to run
MAIN_CLASS := com.rentbuy.Main

# Java compiler and options
JAVAC := javac
JAVA := java
JUNIT := org.junit.platform.console.ConsoleLauncher
JAVAC_FLAGS := -cp ".:$(LIB_DIR)/*" -d
JAVA_FLAGS := -cp $(CLASSPATH)

# Build all Java files
.PHONY: build
build:
	@mkdir -p $(MAIN_OUT)
	@mkdir -p $(TEST_OUT)
	$(JAVAC) $(JAVAC_FLAGS) $(MAIN_OUT) $(SRC_DIR)/com/rentbuy/property/*.java $(SRC_DIR)/com/rentbuy/customer/*.java $(SRC_DIR)/com/rentbuy/transaction/*.java $(SRC_DIR)/com/rentbuy/*.java
	$(JAVAC) $(JAVAC_FLAGS) $(TEST_OUT) -cp $(MAIN_OUT):$(LIB_DIR)/* $(TEST_DIR)/com/rentbuy/property/*.java $(TEST_DIR)/com/rentbuy/transaction/*.java

# Run the main application
.PHONY: run
run:
	$(JAVA) $(JAVA_FLAGS) $(MAIN_CLASS)

# Run the tests
.PHONY: test
test:
	$(JAVA) $(JAVA_FLAGS) $(JUNIT) --scan-class-path

# Clean compiled files
.PHONY: clean
clean:
	@rm -rf $(OUT_DIR)

# Combined target to clean, build, and run tests
.PHONY: all
all: clean build test
