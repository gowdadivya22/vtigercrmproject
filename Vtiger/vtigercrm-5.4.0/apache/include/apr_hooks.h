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

#ifndef APR_HOOKS_H
#define APR_HOOKS_H

#include "apu.h"
/* For apr_array_header_t */
#include "apr_tables.h"

/**
 * @file apr_hooks.h
 * @brief Apache hook functions
 */

#ifdef __cplusplus
extern "C" {
#endif
/**
 * @defgroup APR_Util_Hook Hook Functions
 * @ingroup APR_Util
 * @{
 */
/** macro to return the prototype of the hook function */    
#define APR_IMPLEMENT_HOOK_GET_PROTO(ns,link,name) \
link##_DECLARE(apr_array_header_t *) ns##_hook_get_##name(void)

/** macro to declare the hook correctly */    
#define APR_DECLARE_EXTERNAL_HOOK(ns,link,ret,name,args) \
typedef ret ns##_HOOK_##name##_t args; \
link##_DECLARE(void) ns##_hook_##name(ns##_HOOK_##name##_t *pf, \
                                      const char * const *aszPre, \
                                      const char * const *aszSucc, int nOrder); \
link##_DECLARE(ret) ns##_run_##name args; \
APR_IMPLEMENT_HOOK_GET_PROTO(ns,link,name); \
typedef struct ns##_LINK_##name##_t \
    { \
    ns##_HOOK_##name##_t *pFunc; \
    const char *szName; \
    const char * const *aszPredecessors; \
    const char * const *aszSuccessors; \
    int nOrder; \
    } ns##_LINK_##name##_t;

/** macro to declare the hook structure */    
#define APR_HOOK_STRUCT(members) \
static struct { members } _hooks;

/** macro to link the hook structure */
#define APR_HOOK_LINK(name) \
    apr_array_header_t *link_##name;

/** macro to implement the hook */
#define APR_IMPLEMENT_EXTERNAL_HOOK_BASE(ns,link,name) \
link##_DECLARE(void) ns##_hook_##name(ns##_HOOK_##name##_t *pf,const char * const *aszPre, \
                                      const char * const *aszSucc,int nOrder) \
    { \
    ns##_LINK_##name##_t *pHook; \
    if(!_hooks.link_##name) \
	{ \
	_hooks.link_##name=apr_array_make(apr_hook_global_pool,1,sizeof(ns##_LINK_##name##_t)); \
	apr_hook_sort_register(#name,&_hooks.link_##name); \
	} \
    pHook=apr_array_push(_hooks.link_##name); \
    pHook->pFunc=pf; \
    pHook->aszPredecessors=aszPre; \
    pHook->aszSuccessors=aszSucc; \
    pHook->nOrder=nOrder; \
    pHook->szName=apr_hook_debug_current; \
    if(apr_hook_debug_enabled) \
	apr_hook_debug_show(#name,aszPre,aszSucc); \
    } \
    APR_IMPLEMENT_HOOK_GET_PROTO(ns,link,name) \
    { \
        return _hooks.link_##name; \
    }

/**
 * Implement a hook that has no return code, and therefore runs all of the
 * registered functions
 * @param ns The namespace prefix of the hook functions
 * @param link The linkage declaration prefix of the hook
 * @param name The name of the hook
 * @param args_decl The declaration of the arguments for the hook
 * @param args_use The names for the arguments for the hook
 * @note The link prefix FOO corresponds to FOO_DECLARE() macros, which
 * provide export linkage from the module that IMPLEMENTs the hook, and
 * import linkage from external modules that link to the hook's module.
 */
#define APR_IMPLEMENT_EXTERNAL_HOOK_VOID(ns,link,name,args_decl,args_use) \
APR_IMPLEMENT_EXTERNAL_HOOK_BASE(ns,link,name) \
link##_DECLARE(void) ns##_run_##name args_decl \
    { \
    ns##_LINK_##name##_t *pHook; \
    int n; \
\
    if(!_hooks.link_##name) \
	return; \
\
    pHook=(ns##_LINK_##name##_t *)_hooks.link_##name->elts; \
    for(n=0 ; n < _hooks.link_##name->nelts ; ++n) \
	pHook[n].pFunc args_use; \
    }

/* FIXME: note that this returns ok when nothing is run. I suspect it should
   really return decline, but that breaks Apache currently - Ben
*/
/**
 * Implement a hook that runs until one of the functions returns something
 * other than OK or DECLINE
 * @param ns The namespace prefix of the hook functions
 * @param link The linkage declaration prefix of the hook
 * @param ret Type to return
 * @param name The name of the hook
 * @param args_decl The declaration of the arguments for the hook
 * @param args_use The names for the arguments for the hook
 * @param ok Success value
 * @param decline Decline value
 * @note The link prefix FOO corresponds to FOO_DECLARE() macros, which
 * provide export linkage from the module that IMPLEMENTs the hook, and
 * import linkage from external modules that link to the hook's module.
 */
#define APR_IMPLEMENT_EXTERNAL_HOOK_RUN_ALL(ns,link,ret,name,args_decl,args_use,ok,decline) \
APR_IMPLEMENT_EXTERNAL_HOOK_BASE(ns,link,name) \
link##_DECLARE(ret) ns##_run_##name args_decl \
    { \
    ns##_LINK_##name##_t *pHook; \
    int n; \
    ret rv; \
\
    if(!_hooks.link_##name) \
	return ok; \
\
    pHook=(ns##_LINK_##name##_t *)_hooks.link_##name->elts; \
    for(n=0 ; n < _hooks.link_##name->nelts ; ++n) \
	{ \
	rv=pHook[n].pFunc args_use; \
\
	if(rv != ok && rv != decline) \
	    return rv; \
	} \
    return ok; \
    }


/**
 * Implement a hook that runs until the first function returns something
 * other than the value of decline
 * @param ns The namespace prefix of the hook functions
 * @param link The linkage declaration prefix of the hook
 * @param name The name of the hook
 * @param ret Type to return
 * @param args_decl The declaration of the arguments for the hook
 * @param args_use The names for the arguments for the hook
 * @param decline Decline value
 * @note The link prefix FOO corresponds to FOO_DECLARE() macros, which
 * provide export linkage from the module that IMPLEMENTs the hook, and
 * import linkage from external modules that link to the hook's module.
 */
#define APR_IMPLEMENT_EXTERNAL_HOOK_RUN_FIRST(ns,link,ret,name,args_decl,args_use,decline) \
APR_IMPLEMENT_EXTERNAL_HOOK_BASE(ns,link,name) \
link##_DECLARE(ret) ns##_run_##name args_decl \
    { \
    ns##_LINK_##name##_t *pHook; \
    int n; \
    ret rv; \
\
    if(!_hooks.link_##name) \
	return decline; \
\
    pHook=(ns##_LINK_##name##_t *)_hooks.link_##name->elts; \
    for(n=0 ; n < _hooks.link_##name->nelts ; ++n) \
	{ \
	rv=pHook[n].pFunc args_use; \
\
	if(rv != decline) \
	    return rv; \
	} \
    return decline; \
    }

    /* Hook orderings */
/** run this hook first, before ANYTHING */
#define APR_HOOK_REALLY_FIRST	(-10)
/** run this hook first */
#define APR_HOOK_FIRST		0
/** run this hook somewhere */
#define APR_HOOK_MIDDLE		10
/** run this hook after every other hook which is defined*/
#define APR_HOOK_LAST		20
/** run this hook last, after EVERYTHING */
#define APR_HOOK_REALLY_LAST	30

/**
 * The global pool used to allocate any memory needed by the hooks.
 */ 
APU_DECLARE_DATA extern apr_pool_t *apr_hook_global_pool;

/** @deprecated @see apr_hook_global_pool */
APU_DECLARE_DATA extern apr_pool_t *apr_global_hook_pool;

/**
 * A global variable to determine if debugging information about the
 * hooks functions should be printed
 */ 
APU_DECLARE_DATA extern int apr_hook_debug_enabled;

/** @deprecated @see apr_hook_debug_enabled */
APU_DECLARE_DATA extern int apr_debug_module_hooks;

/**
 * The name of the module that is currently registering a function
 */ 
APU_DECLARE_DATA extern const char *apr_hook_debug_current;

/** @deprecated @see apr_hook_debug_current */
APU_DECLARE_DATA extern const char *apr_current_hooking_module;

/**
 * Register a hook function to be sorted
 * @param szHookName The name of the Hook the function is registered for
 * @param aHooks The array which stores all of the functions for this hook
 */
APU_DECLARE(void) apr_hook_sort_register(const char *szHookName, 
                                        apr_array_header_t **aHooks);
/**
 * Sort all of the registerd functions for a given hook
 */
APU_DECLARE(void) apr_hook_sort_all(void);

/** @deprecated @see apr_hook_sort_all */
APU_DECLARE(void) apr_sort_hooks(void);

/**
 * Print all of the information about the current hook.  This is used for
 * debugging purposes.
 * @param szName The name of the hook
 * @param aszPre All of the functions in the predecessor array
 * @param aszSucc All of the functions in the successor array
 */
APU_DECLARE(void) apr_hook_debug_show(const char *szName,
                                      const char * const *aszPre,
                                      const char * const *aszSucc);

/** @deprecated @see apr_hook_debug_show */
APU_DECLARE(void) apr_show_hook(const char *szName,
                                const char * const *aszPre,
                                const char * const *aszSucc);

/**
 * Remove all currently registered functions.
 */
APU_DECLARE(void) apr_hook_deregister_all(void);

/** @} */
#ifdef __cplusplus
}
#endif

#endif /* APR_HOOKS_H */
