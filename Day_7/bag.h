/** @file bag.h
 * Defines bag functionality.
 */

#ifndef BAG_H
#define BAG_H

/**
 * Data Structure representing a bag.
 * Stores name and relations.
 */
typedef struct _bag bag;

/**
 * Data Structure representing a relation.
 * Stores amount and pointer to bag.
 */
typedef struct _relation relation;

/**
 * Initialise bag with given uid.
 * Overrides on already initialized bag.
 * @param b       Pointer to empty bag.
 * @param uid     Unique Identifier of bag.
 */
void bag_initialize(bag *const b, const unsigned short uid);

/**
 * Add a relation to a bag.
 * @param b         Bag in wich the relationship is added.
 * @param amount    Amount of bags supported.
 * @param bag_uid   Uid of bag supported.
 */
void bag_add_relation(
    bag *const b, 
    const unsigned short amount,
    const unsigned short bag_uid
);

/**
 * Free memory of bag.
 * @param b Bag wich memory is liberated.
 */
void bag_free(bag *const b);

#endif