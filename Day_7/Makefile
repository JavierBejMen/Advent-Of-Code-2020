OBJ = obj

TARGETS = bag_checker

CC ?= gcc
CFLAGS := ${CFLAGS}
CFLAGS += -Wall -Wextra -pedantic -O3 -std=c18
LDFLAGS := ${LDFLAGS}

TARGETS = bag_checker

.PHONY: clean
.SILENT: clean

all: clean $(TARGETS)

$(OBJ)/%.o : %.c
	$(CC) -c $< $(CFLAGS) -o $@

bag_checker : $(OBJ)/bag.o $(OBJ)/bag_checker.o $(OBJ)/rules.o
	$(CC) $^ $(LDFLAGS) -o $@


clean:
	rm $(TARGETS) || true
	rm $(OBJ)/* || true