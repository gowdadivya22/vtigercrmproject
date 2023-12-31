/* Copyright 2000-2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef APR_ALLOCATOR_H
#define APR_ALLOCATOR_H

/**
 * @file apr_allocator.h
 * @brief APR Internal Memory Allocation
 */

#include "apr.h"
#include "apr_errno.h"
#define APR_WANT_MEMFUNC /**< For no good reason? */
#include "apr_want.h"

#ifdef __cplusplus
extern "C" {
#endif

/**
 * @defgroup apr_allocator Internal Memory Allocation
 * @ingroup APR 
 * @{
 */

/** the allocator structure */
typedef struct apr_allocator_t apr_allocator_t;
/** the structure which holds information about the allocation */
typedef struct apr_memnode_t apr_memnode_t;

/** basic memory node structure */
struct apr_memnode_t {
    apr_memnode_t *next;            /**< next memnode */
    apr_memnode_t **ref;            /**< reference to self */
    apr_uint32_t   index;           /**< size */
    apr_uint32_t   free_index;      /**< how much free */
    char          *first_avail;     /**< pointer to first free memory */
    char          *endp;            /**< pointer to end of free memory */
};

/** The base size of a memory node - aligned.  */
#define APR_MEMNODE_T_SIZE APR_ALIGN_DEFAULT(sizeof(apr_memnode_t))

/** Symbolic constants */
#define APR_ALLOCATOR_MAX_FREE_UNLIMITED 0

/**
 * Create a new allocator
 * @param allocator The allocator we have just created.
 *
 */
APR_DECLARE(apr_status_t) apr_allocator_create(apr_allocator_t **allocator);

/**
 * Destroy an allocator
 * @param allocator The allocator to be destroyed
 * @remark Any memnodes not given back to the allocator prior to destroying
 *         will _not_ be free()d.
 */
APR_DECLARE(void) apr_allocator_destroy(apr_allocator_t *allocator);

/**
 * Allocate a block of mem from the allocator
 * @param allocator The allocator to allocate from
 * @param size The size of the mem to allocate (excluding the
 *        memnode structure)
 */
APR_DECLARE(apr_memnode_t *) apr_allocator_alloc(apr_allocator_t *allocator,
                                                 apr_size_t size);

/**
 * Free a block of mem, giving it back to the allocator
 * @param allocator The allocator to give the mem back to
 * @param memnode The memory node to return
 */
APR_DECLARE(void) apr_allocator_free(apr_allocator_t *allocator,
                                     apr_memnode_t *memnode);

#include "apr_pools.h"

/**
 * Set the owner of the allocator
 * @param allocator The allocator to set the owner for
 * @param pool The pool that is to own the allocator
 * @remark Typically pool is the highest level pool using the allocator
 */
/*
 * XXX: see if we can come up with something a bit better.  Currently
 * you can make a pool an owner, but if the pool doesn't use the allocator
 * the allocator will never be destroyed.
 */
APR_DECLARE(void) apr_allocator_owner_set(apr_allocator_t *allocator,
                                          apr_pool_t *pool);

/** @deprecated @see apr_allocator_owner_set */
APR_DECLARE(void) apr_allocator_set_owner(apr_allocator_t *allocator,
                                          apr_pool_t *pool);

/**
 * Get the current owner of the allocator
 * @param allocator The allocator to get the owner from
 */
APR_DECLARE(apr_pool_t *) apr_allocator_owner_get(apr_allocator_t *allocator);

/** @deprecated @see apr_allocator_owner_get */
APR_DECLARE(apr_pool_t *) apr_allocator_get_owner(
                                  apr_allocator_t *allocator);

/**
 * Set the current threshold at which the allocator should start
 * giving blocks back to the system.
 * @param allocator The allocator the set the threshold on
 * @param size The threshold.  0 == unlimited.
 */
APR_DECLARE(void) apr_allocator_max_free_set(apr_allocator_t *allocator,
                                             apr_size_t size);

/** @deprecated @see apr_allocator_max_free_set */
APR_DECLARE(void) apr_allocator_set_max_free(apr_allocator_t *allocator,
                                             apr_size_t size);

#include "apr_thread_mutex.h"

#if APR_HAS_THREADS
/**
 * Set a mutex for the allocator to use
 * @param allocator The allocator to set the mutex for
 * @param mutex The mutex
 */
APR_DECLARE(void) apr_allocator_mutex_set(apr_allocator_t *allocator,
                                          apr_thread_mutex_t *mutex);

/** @deprecated @see apr_allocator_mutex_set */
APR_DECLARE(void) apr_allocator_set_mutex(apr_allocator_t *allocator,
                                          apr_thread_mutex_t *mutex);

/**
 * Get the mutex currently set for the allocator
 * @param allocator The allocator
 */
APR_DECLARE(apr_thread_mutex_t *) apr_allocator_mutex_get(
                                      apr_allocator_t *allocator);

/** @deprecated @see apr_allocator_mutex_get */
APR_DECLARE(apr_thread_mutex_t *) apr_allocator_get_mutex(
                                      apr_allocator_t *allocator);

#endif /* APR_HAS_THREADS */

/** @} */

#ifdef __cplusplus
}
#endif

#endif /* !APR_ALLOCATOR_H */
