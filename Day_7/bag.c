#include "bag.h"

#include<stdlib.h>



struct _bag {
    unsigned short uid; // Unique Identifier of the bag.
    size_t relation_size;
    relation *relations; // Array of relations.
};

struct _relation {
    unsigned short amount; // Number of bags supported.
    unsigned short bag_uid; // UID of bag supported.
};

void bag_initialize(bag *const b, const unsigned short uid) {
    if (b != NULL) {
        b->uid = uid;
        b->relation_size = 0;
    }
}

void bag_add_relation(
    bag *const b, 
    const unsigned short amount,
    const unsigned short bag_uid
) {
    if (b != NULL) {
        if (b->relation_size == 0) {
            b->relations = malloc(sizeof(b->relations));
            b->relations[0].amount = amount;
            b->relations[0].bag_uid = bag_uid;
            b->relation_size++;
        } else {
            b->relations = realloc(b->relations, b->relation_size*sizeof(relation));
            b->relation_size++;
        }
    }
}

void bag_free(bag *const b) {
    if (b != NULL) {
        if (b->relation_size != 0 && b->relations != NULL) free(b->relations);
    }
}

