# Makefile to compile all Java source files

# Java compiler
JAVAC = javac

# Source files
SOURCES = DetailExpenseTracker.java ExpenseTracker.java Finance.java expense.java income.java report.java user.java

# Class files
CLASSES = $(SOURCES:.java=.class)

# Default target to compile all source files
all: $(CLASSES)

# Pattern rule to compile .java to .class
%.class: %.java
	$(JAVAC) $<

# Run the main program (default: ExpenseTracker)
run: all
	java ExpenseTracker

test:
	

# Clean up compiled class files
clean:
	rm -f *.class
